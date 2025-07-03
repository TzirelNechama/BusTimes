package com.example.java_project.Services;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.java_project.Converters.StationConverter;
import com.example.java_project.Models.Line;
import com.example.java_project.Models.Station;
import com.example.java_project.Models.Travel;
import com.example.java_project.Repositories.LineRepository;
import com.example.java_project.Repositories.StationLineRepository;
import com.example.java_project.Repositories.StationRepository;
import com.example.java_project.Repositories.TravelRepository;

@Service
public class StationService {
    @Autowired
    public StationRepository stationRepository;
    @Autowired
    public LineRepository lineRepository;
    @Autowired
    public StationLineRepository stationLineRepository;
    @Autowired
    public TravelRepository travelRepository;

    @Autowired
    public StationConverter stationConverter;

    // חיפוש לפי תחנה
    public String searchByStation(int stationNumber, String line) {
        Station station = stationRepository.findByStationNumber(stationNumber).get();
        if (line.equals("*")) {
            String s = "";
            for (var each : station.getLines()) {
                int f = fastest(each, station);
                if (f != 100) {

                    s += "קו " + each.getNumber() + " יגיע לתחנה " + station.getName() + " בעוד " + f + " דקות.";
                }
            }
            if (s == "") {
                s += "אין מידע המבוקש בטווח הזמן הקרוב";
            }
            return s;
        } else {
            Line lline = null;
            try {
                lline = lineRepository.findByNumber(line).get();
            } catch (Exception e) {
                return "קו לא קיים";
            }
            int f = fastest(lline, station);
            if (f == 100)
                return "אין מידע על הקו המבוקש בטווח הזמן הקרוב";

            return "קו " + line + " יגיע לתחנה " + station.getName() + " בעוד " + f + "דקות.";

        }
    }

    public int fastest(Line line, Station station) {
        int minutesFromDepart = stationLineRepository.findByStationAndLine(station, line).get().getStationOrder();
        List<Travel> travels = line.getTravels();

        // lline.getTravels()
        int fastest = 100;
        for (var t : travels) {
            // אם האוטובוס יוצא בשעה זו
            int hoursToLeave = t.getDepartureTime().getHour() - LocalTime.now().getHour();
            int minutesToLeave = t.getDepartureTime().getMinute() - LocalTime.now().getMinute();
            if (hoursToLeave == 0) {
                // מספר הדקות מעכשיו עד שיוצא מתחנת מוצא
                if (minutesToLeave + minutesFromDepart > 0 && minutesToLeave + minutesFromDepart < fastest) {
                    fastest = minutesToLeave + minutesFromDepart;
                }
            } else if (hoursToLeave > 0) {
                if (hoursToLeave + minutesToLeave + minutesFromDepart < fastest) {
                    fastest = hoursToLeave + minutesToLeave + minutesFromDepart;
                }
            }
        }
        return fastest;
    }

}
