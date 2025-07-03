package com.example.java_project.Converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.java_project.DTOs.LineDTO;
import com.example.java_project.Models.Line;
import com.example.java_project.Repositories.StationRepository;

public class LineConverter {

    @Autowired
    private StationRepository stationRepository;

    public LineDTO toDTO(Line line) {
        LineDTO lineDTO = new LineDTO();
        lineDTO.setId(line.getId());
        lineDTO.setDestenation(line.getDestenation());
        lineDTO.setNumber(line.getNumber());
        lineDTO.setOrigin(line.getOrigin());
        lineDTO.setStation_idList(
                line.getStations().stream().map(l -> l.getId()).collect(Collectors.toList()));
        return lineDTO;
    }

    public Line toModel(LineDTO lineDTO) {
        Line line = new Line();
        line.setDestenation(lineDTO.getDestenation());
        line.setNumber(lineDTO.getNumber());
        line.setOrigin(lineDTO.getOrigin());
        line.setStations(stationRepository.findAllById(lineDTO.getStation_idList()));
        return line;
    }

    public List<LineDTO> toDTOList(List<Line> lines) {
        return lines.stream().map(b -> toDTO(b)).collect(Collectors.toList());
    }

    public List<Line> toModeList(List<LineDTO> lineDTOs) {
        return lineDTOs.stream().map(b -> toModel(b)).collect(Collectors.toList());
    }
}
