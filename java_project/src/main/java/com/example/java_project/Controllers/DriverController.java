package com.example.java_project.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_project.DTOs.DriverDTO;
import com.example.java_project.Services.DriverService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    public DriverService driverService;

    @PostMapping("/add")
    public ResponseEntity<Void> addDriver(@RequestBody DriverDTO driverDTO) {
        if (driverService.addDriver(driverDTO)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<DriverDTO>> getAll() {
        return ResponseEntity.ok().body(driverService.getAllDrivers());
    }

}
