package com.example.java_project.Services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.java_project.Converters.StationConverter;
import com.example.java_project.DTOs.StationDTO;
import com.example.java_project.Models.Line;
import com.example.java_project.Models.Station;
import com.example.java_project.Models.StationLine;
import com.example.java_project.Models.Travel;
import com.example.java_project.Repositories.LineRepository;
import com.example.java_project.Repositories.StationLineRepository;
import com.example.java_project.Repositories.StationRepository;
import com.example.java_project.Repositories.TravelRepository;

@Service
public class LineService {

    @Autowired
    public LineRepository lineRepository;

    @Autowired
    public TravelRepository travelRepository;

    @Autowired
    public StationLineRepository stationLineRepository;

    @Autowired
    public StationRepository stationRepository;

    @Autowired
    public StationConverter stationConverter;

    // שתי פונקציות: הפרמטר-מספר הקו (בשניהן)
    // 1)למידע על מיקומם של האוטובוסים לאורך כל ציר הנסיעה
    // הקוים הרלונטיים הם כל הקוים שזמן הנסיעה שלהם הוא בין הזמן הנוכחי מינוס מספר
    // התחנות במסלול לבין הזמן הנוכחי.
    // כדי לבדוק איפה קו קיים צריך לבדוק את הזמן הנוכחי מינוס זמן יציאת האוטובוס זה
    // מספר התחנה שבה נמצא כעת.

    public String allBusesEnroute(String lineNumber) {
        // שעה נוכחית

        LocalTime now = LocalTime.now();
        // הקו
        Line line = lineRepository.findByNumber(lineNumber).get();
        // כל הנסיעות של הקו
        List<Travel> travels = travelRepository.findAllByLine(line).get();
        // מספר התחנות לקו
        int numberOfStations = line.getStations().size();

        LocalTime firstTime = now.minusMinutes(numberOfStations - 1);

        // ((o1, o2) -> o1.getDepartureTime().isBefore(o2.getDepartureTime())
        // כל הנסיעות שזמן היציאה שלהם הוא אחרי הזמן הנוכחי מינוס מספר התחנות
        // כלומר אם זמן היציאה הוא המוקדם ביותר האפשרי האוטובוס נמצא בסוף המסלול
        List<Travel> lineTravels = travels.stream()
                .filter(t -> t.getDepartureTime().isAfter(firstTime)
                // && t.getDepartureTime().isBefore(now)
                ).toList();
        // int i = 0;
        // while (lineTravels.get(i).getDepartureTime().isBefore(firstTime) && i <
        // lineTravels.size()) {
        // i++;
        // }
        String s = "";
        // if (i == lineTravels.size())
        // return s;

        for (var l : lineTravels) {
            LocalTime minusTime = now.minusSeconds(l.getDepartureTime().toSecondOfDay());
            int stationOrder = minusTime.getHour() * 60 + minusTime.getMinute();
            s += "קו " + lineNumber + " נמצא בקרבת תחנה "
                    + stationLineRepository.findByStationOrder(stationOrder).get().getStation().getName() + "\n";
        }
        // while (lineTravels.get(i).getDepartureTime().isBefore(now) && i <
        // lineTravels.size()) {
        // int stationOrder = now.compareTo(lineTravels.get(i).getDepartureTime());
        // s += "קו " + lineNumber + " נמצא בקרבת תחנה "
        // +
        // stationLineRepository.findByStationOrder(stationOrder).get().getStation().getName();
        // }
        if (s == "") {
            s += "לא נמצאו תוצאות";
        }
        return s;
    }

    // 2)לשמיעת כל תחנות הקו

    public List<StationDTO> allStationsAlongRoute(String lineNumber) {
        Line line = lineRepository.findByNumber(lineNumber).get();
        List<StationLine> s = stationLineRepository.findAllByLine(line).get();
        s.sort((o1, o2) -> o1.getStationOrder() - o2.getStationOrder());
        List<Station> stations = new ArrayList<Station>();
        for (var each : s) {
            stations.add(stationRepository.getById(each.getId()));
        }
        return stationConverter.toDTOList(stations);
    }

    // public boolean addStationToLine(int lineNumber){

    // }

    // public boolean deleteStationInLine(){

    // }
}
