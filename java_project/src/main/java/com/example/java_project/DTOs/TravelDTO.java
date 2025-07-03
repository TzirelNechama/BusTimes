package com.example.java_project.DTOs;

import java.time.LocalTime;

import lombok.Data;

@Data
public class TravelDTO {
    private int id;
    private int bus_id;
    private int driver_id;
    private int line_id;
    private LocalTime departureTime;

}
