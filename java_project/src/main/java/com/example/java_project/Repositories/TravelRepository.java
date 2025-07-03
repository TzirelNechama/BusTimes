package com.example.java_project.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_project.Models.Line;
import com.example.java_project.Models.Travel;
import java.time.LocalTime;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Integer> {
    Optional<List<Travel>> findAllByLine(Line line);

    Optional<List<Travel>> findAllByDepartureTime(LocalTime departureTime);

}
