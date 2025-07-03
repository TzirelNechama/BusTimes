package com.example.java_project.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_project.Models.Line;

@Repository
public interface LineRepository extends JpaRepository<Line, Integer> {
    Optional<Line> findByNumber(String number);
}
