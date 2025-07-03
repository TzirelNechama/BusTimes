package com.example.java_project.Converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.java_project.DTOs.StationLineDTO;
import com.example.java_project.Repositories.LineRepository;
import com.example.java_project.Repositories.StationRepository;
import com.example.java_project.Models.StationLine;

public class StationLineConverter {

    @Autowired
    public StationRepository stationRepository;
    @Autowired
    public LineRepository lineRepository;

    public StationLineDTO toDto(StationLine stationLine) {
        StationLineDTO stationLineDTO = new StationLineDTO();
        stationLineDTO.setId(stationLine.getId());
        stationLineDTO.setStation_id(stationLine.getStation().getId());
        stationLineDTO.setLine_id(stationLine.getLine().getId());
        stationLineDTO.setStationOrder(stationLine.getStationOrder());
        return stationLineDTO;
    }

    public StationLine toModel(StationLineDTO stationLineDTO) {
        StationLine stationLine = new StationLine();
        stationLine.setStationOrder(stationLineDTO.getStationOrder());
        stationLine.setStation(stationRepository.findById(stationLineDTO.getStation_id()).get());
        stationLine.setLine(lineRepository.findById(stationLineDTO.getLine_id()).get());
        return stationLine;
    }

    public List<StationLineDTO> toDTOList(List<StationLine> stationLines) {
        return stationLines.stream().map(s -> toDto(s)).collect(Collectors.toList());
    }

    public List<StationLine> toModelList(List<StationLineDTO> stationLineDTOs) {
        return stationLineDTOs.stream().map(s -> toModel(s)).collect(Collectors.toList());
    }

}
