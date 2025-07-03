package com.example.java_project.Converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.java_project.DTOs.DriverDTO;
import com.example.java_project.Models.Driver;
import com.example.java_project.Repositories.TravelRepository;

public class DriverConverter {

    @Autowired
    private TravelRepository travelRepository;

    public DriverDTO toDTO(Driver driver) {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(driver.getId());
        driverDTO.setName(driver.getName());
        driverDTO.setPhone(driver.getPhone());
        driverDTO.setTravel_idList(driver.getTravels().stream().map(d -> d.getId()).collect(Collectors.toList()));
        return driverDTO;
    }

    public Driver toModel(DriverDTO driverDTO) {
        Driver driver = new Driver();
        driver.setName(driverDTO.getName());
        driver.setPhone(driverDTO.getPhone());
        driver.setTravels(travelRepository.findAllById(driverDTO.getTravel_idList()));
        return driver;
    }

    public List<DriverDTO> toDTOList(List<Driver> drivers) {
        return drivers.stream().map(d -> toDTO(d)).collect(Collectors.toList());
    }

    public List<Driver> toModelDrivers(List<DriverDTO> driverDTOs) {
        return driverDTOs.stream().map(d -> toModel(d)).collect(Collectors.toList());
    }
}
