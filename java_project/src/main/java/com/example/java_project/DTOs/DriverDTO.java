package com.example.java_project.DTOs;

import java.util.List;

import lombok.Data;

@Data
public class DriverDTO {
    private int id;
    private String name;
    private String phone;
    private List<Integer> travel_idList;

}
