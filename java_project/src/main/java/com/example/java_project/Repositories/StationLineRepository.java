package com.example.java_project.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_project.Models.StationLine;
import com.example.java_project.Models.Station;
import com.example.java_project.Models.Line;

@Repository
public interface StationLineRepository extends JpaRepository<StationLine, Integer> {
    Optional<StationLine> findByStationAndLine(Station station, Line line);

    Optional<List<StationLine>> findAllByLine(Line line);

    Optional<StationLine> findByStationOrder(int stationOrder);

    // Boolean existsByStationIdAndLineId(int lineId, int stationId);

    Optional<StationLine> getByStationIdAndLineId(int lineId, int stationId);

    // Optional<Void> deleteByStationIdAndLineId(int lineId, int stationId);

    Optional<List<StationLine>> getAllByLineId(int lineId);

}