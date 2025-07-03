package com.example.java_project.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_project.DTOs.StationLineDTO;
import com.example.java_project.Services.StationLineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/stationLine")
public class StationLineController {

    @Autowired
    public StationLineService stationLineService;

    @PostMapping("/add")
    public ResponseEntity<Void> addStationLine(@RequestBody StationLineDTO stationLineDTO) {
        if (stationLineService.addStationLine(stationLineDTO)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{lineId}/{stationId}")
    public ResponseEntity<Void> deleteStationLine(@PathVariable int lineId, @PathVariable int stationId) {
        if (stationLineService.deleteStationLineById(lineId, stationId))
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }

}
