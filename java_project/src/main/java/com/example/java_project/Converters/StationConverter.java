package com.example.java_project.Converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.java_project.DTOs.StationDTO;
import com.example.java_project.Models.Station;
import com.example.java_project.Repositories.LineRepository;

public class StationConverter {

    @Autowired
    public LineRepository lineRepository;

    public StationDTO toDTO(Station station) {
        StationDTO stationDTO = new StationDTO();
        stationDTO.setId(station.getId());
        stationDTO.setName(station.getName());
        stationDTO.setLine_idList(station.getLines().stream().map(s -> s.getId()).collect(Collectors.toList()));
        return stationDTO;
    }

    public Station toModel(StationDTO stationDTO) {
        Station station = new Station();
        station.setName(stationDTO.getName());
        station.setLines(lineRepository.findAllById(stationDTO.getLine_idList()));
        return station;
    }

    public List<StationDTO> toDTOList(List<Station> stations) {
        return stations.stream().map(s -> toDTO(s)).collect(Collectors.toList());
    }

    public List<Station> toModeList(List<StationDTO> stationDTOs) {
        return stationDTOs.stream().map(s -> toModel(s)).collect(Collectors.toList());
    }

}
