package com.example.java_project.DTOs;

import java.util.List;

import lombok.Data;

@Data
public class StationDTO {
    private int id;
    private String name;
    private List<Integer> line_idList;
}
