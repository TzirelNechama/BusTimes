package com.example.java_project.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_project.DTOs.StationDTO;
import com.example.java_project.Services.LineService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/line")
public class LineController {

    @Autowired
    public LineService lineService;

    @GetMapping("/getAllBusesEnroute")
    public ResponseEntity<String> getAllBusesEnroute(@RequestParam String lineNumber) {
        return ResponseEntity.ok().body(lineService.allBusesEnroute(lineNumber));
    }

    @GetMapping("/getAllStationsAlongRoute")
    public ResponseEntity<List<StationDTO>> getAllStationsAlongRoute(@RequestParam String lineNumber) {
        return ResponseEntity.ok().body(lineService.allStationsAlongRoute(lineNumber));
    }

}
