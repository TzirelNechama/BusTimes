package com.example.java_project.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.java_project.Converters.StationLineConverter;
import com.example.java_project.DTOs.StationLineDTO;
import com.example.java_project.Models.Line;
import com.example.java_project.Models.Station;
import com.example.java_project.Models.StationLine;
import com.example.java_project.Repositories.LineRepository;
import com.example.java_project.Repositories.StationLineRepository;
import com.example.java_project.Repositories.StationRepository;

@Service
public class StationLineService {

    @Autowired
    public StationLineRepository stationLineRepository;
    @Autowired
    public StationRepository stationRepository;
    @Autowired
    public LineRepository lineRepository;
    @Autowired
    public StationLineConverter stationLineConverter;

    public boolean addStationLine(StationLineDTO stationLineDTO) {
        StationLine stationLine = stationLineConverter.toModel(stationLineDTO);
        if (!stationLineRepository.exists(Example.of(stationLine))) {
            // עדכון מספרי תחנות במסלול
            updateStationOrder(stationLine.getLine().getId(), stationLine.getStationOrder(), 1);
            stationLineRepository.save(stationLine);
            return true;
        }
        return false;
    }

    public boolean deleteStationLineById(int lineId, int stationId) {
        // stationId).get();
        try {
            Station station = stationRepository.getById(stationId);
            Line line = lineRepository.getById(lineId);
            StationLine sl = stationLineRepository.findByStationAndLine(station, line).get();
            updateStationOrder(lineId, sl.getStationOrder(), -1);
            stationLineRepository.delete(sl);
            return true;
        } catch (Exception e) {
            return false;
        }

        // StationLineDTO stationLine = new StationLineDTO();
        // stationLine.setLine_id(lineId);
        // stationLine.setStation_id(stationId);
        // stationLineRepository.exists(Example.of(stationLineConverter.toModel(stationLine)))
        // if (sl != null) {
        // StationLine sl = stationLineRepository.getByStationIdAndLineId(lineId,
        // stationId).get();

        // עדכון מספרי תחנות במסלול
        // updateStationOrder(lineId, sl.getStationOrder());
        // stationLineRepository.delete(sl);
        // return true;
        // }
        // return false;
    }

    // פונקציה לעדכון מספרי התחנות

    public void updateStationOrder(int lineId, int stationOrder, int toAdd) {
        List<StationLine> stationLines = stationLineRepository.getAllByLineId(lineId).get();
        for (var sl : stationLines) {
            if (sl.getStationOrder() >= stationOrder)
                sl.setStationOrder(sl.getStationOrder() + toAdd);
        }
    }
}
