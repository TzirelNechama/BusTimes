package com.example.java_project.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String number;
    private String origin;
    private String destenation;
    @ManyToMany
    @JoinTable(name = "Station_Line", joinColumns = @JoinColumn(name = "line_id"), inverseJoinColumns = @JoinColumn(name = "station_id"))
    private List<Station> stations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "line", fetch = FetchType.LAZY)
    private List<Travel> travels;

}
