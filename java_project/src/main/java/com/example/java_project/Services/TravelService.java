package com.example.java_project.Services;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.java_project.Converters.TravelConverter;
import com.example.java_project.DTOs.TravelDTO;
import com.example.java_project.Models.Travel;
import com.example.java_project.Repositories.TravelRepository;

@Service
public class TravelService {
    @Autowired
    public TravelRepository travelRepository;
    @Autowired
    public TravelConverter travelConverter;

    public boolean addTravel(TravelDTO travelDTO) {
        Travel travel = travelConverter.toModel(travelDTO);
        if (!travelRepository.exists(Example.of(travel))) {
            travelRepository.save(travel);
            return true;
        }
        return false;
    }

    public List<Travel> times(String myTime) {
        LocalTime time = LocalTime.parse(myTime);
        return travelRepository.findAllByDepartureTime(time).get();
    }
}
