package com.example.java_project.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.java_project.Converters.DriverConverter;
import com.example.java_project.DTOs.DriverDTO;
import com.example.java_project.Models.Driver;
import com.example.java_project.Repositories.DriverRepository;

@Service
public class DriverService {
    @Autowired
    public DriverRepository driverRepository;

    @Autowired
    public DriverConverter driverConverter;

    public boolean addDriver(DriverDTO driverDTO) {
        Driver driver = driverConverter.toModel(driverDTO);
        if (!driverRepository.exists(Example.of(driver))) {
            driverRepository.save(driver);
            return true;
        }
        return false;
    }

    public List<DriverDTO> getAllDrivers() {
        return driverConverter.toDTOList(driverRepository.findAll());
    }
}
