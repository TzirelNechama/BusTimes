package com.example.java_project.DTOs;

import java.util.List;

import lombok.Data;

@Data
public class LineDTO {
    private int id;
    private String number;
    private String origin;
    private String destenation;
    private List<Integer> station_idList;

}
