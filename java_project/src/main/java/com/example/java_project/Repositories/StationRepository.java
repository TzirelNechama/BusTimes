package com.example.java_project.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_project.Models.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
    Optional<Station> findByStationNumber(int stationNumber);
}
