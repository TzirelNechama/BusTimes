package com.example.java_project.DTOs;

import java.util.List;

import lombok.Data;

@Data
public class BusDTO {
    private int id;
    private String licensePlate;
    private int seats;
    private List<Integer> travel_idList;

}
