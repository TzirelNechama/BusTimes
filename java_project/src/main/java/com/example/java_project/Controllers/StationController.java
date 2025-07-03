package com.example.java_project.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_project.Services.StationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/station")
public class StationController {

    @Autowired
    public StationService stationService;

    @GetMapping("/searchByStation")
    public ResponseEntity<String> searchByStation(@RequestParam int stationNumber, String line) {
        return ResponseEntity.ok().body(stationService.searchByStation(stationNumber, line));

    }

}
