package com.example.java_project.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_project.Models.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {
    Optional<Bus> findById(int id);
}
