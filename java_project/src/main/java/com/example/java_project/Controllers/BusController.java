package com.example.java_project.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_project.DTOs.BusDTO;
import com.example.java_project.Services.BusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/bus")
public class BusController {

    @Autowired
    public BusService busServise;

    @PostMapping("/add")
    public ResponseEntity<Void> addBus(@RequestBody BusDTO busDTO) {
        if (busServise.addBus(busDTO)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.badRequest().build();
    }

}
