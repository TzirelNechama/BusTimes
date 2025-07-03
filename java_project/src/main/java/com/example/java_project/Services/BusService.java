package com.example.java_project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.java_project.Converters.BusConverter;
import com.example.java_project.DTOs.BusDTO;
import com.example.java_project.Models.Bus;
import com.example.java_project.Repositories.BusRepository;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    public BusConverter busConverter;

    public boolean addBus(BusDTO busDTO) {
        Bus bus = busConverter.toModel(busDTO);
        if (!busRepository.exists(Example.of(bus))) {
            busRepository.save(bus);
            return true;
        }
        return false;
    }
}
