package com.example.java_project.Converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.java_project.DTOs.TravelDTO;
import com.example.java_project.Models.Travel;
import com.example.java_project.Repositories.BusRepository;
import com.example.java_project.Repositories.DriverRepository;

public class TravelConverter {
    @Autowired
    public BusRepository busRepository;

    @Autowired
    public DriverRepository driverRepository;

    public TravelDTO toDto(Travel travel) {
        TravelDTO travelDTO = new TravelDTO();
        travelDTO.setId(travel.getId());
        travelDTO.setDepartureTime(travel.getDepartureTime());
        travelDTO.setBus_id(travel.getBus().getId());
        travelDTO.setDriver_id(travel.getDriver().getId());
        return travelDTO;
    }

    public Travel toModel(TravelDTO travelDTO) {
        Travel travel = new Travel();
        travel.setDepartureTime(travelDTO.getDepartureTime());
        travel.setBus(busRepository.findById(travelDTO.getBus_id()).get());
        travel.setDriver(driverRepository.findById(travelDTO.getDriver_id()).get());
        return travel;
    }

    public List<TravelDTO> toDTOList(List<Travel> travels) {
        return travels.stream().map(t -> toDto(t)).collect(Collectors.toList());
    }

    public List<Travel> toModelList(List<TravelDTO> travels) {
        return travels.stream().map(t -> toModel(t)).collect(Collectors.toList());
    }
}
