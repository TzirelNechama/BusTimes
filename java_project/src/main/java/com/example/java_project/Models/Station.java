package com.example.java_project.Models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int stationNumber;

    @ManyToMany
    @JoinTable(name = "Station_Line", joinColumns = @JoinColumn(name = "station_id"), inverseJoinColumns = @JoinColumn(name = "line_id"))
    private List<Line> lines;
}
