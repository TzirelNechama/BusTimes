package com.example.java_project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_project.Models.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

}
