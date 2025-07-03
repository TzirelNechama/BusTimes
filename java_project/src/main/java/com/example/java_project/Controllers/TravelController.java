package com.example.java_project.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_project.Converters.TravelConverter;
import com.example.java_project.DTOs.TravelDTO;
import com.example.java_project.Services.TravelService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/travel")
public class TravelController {

    @Autowired
    public TravelService travelService;

    @Autowired
    public TravelConverter travelConverter;

    @PostMapping("/add")
    public ResponseEntity<Void> postMethodName(@RequestBody TravelDTO travelDTO) {
        try {
            travelService.addTravel(travelDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            System.err.println(e);
            return ResponseEntity.badRequest().build();
        }
        // if (travelService.addTravel(travelDTO)) {
        // return ResponseEntity.status(HttpStatus.CREATED).build();
        // }
        // return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getAllByTime")
    public ResponseEntity<List<TravelDTO>> getAllByDepartureTime(String time) {
        return ResponseEntity.ok().body(travelConverter.toDTOList(travelService.times(time)));

    }
    // "20:12:00"
}
