package com.example.java_project.Converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.java_project.DTOs.BusDTO;

import com.example.java_project.Models.Bus;
import com.example.java_project.Repositories.TravelRepository;

public class BusConverter {

    @Autowired
    private TravelRepository travelRepository;

    public BusDTO toDTO(Bus bus) {
        BusDTO busDTO = new BusDTO();
        busDTO.setId(bus.getId());
        busDTO.setLicensePlate(bus.getLicensePlate());
        busDTO.setSeats(bus.getSeats());
        busDTO.setTravel_idList(
                bus.getTravels().stream().map(t -> t.getId()).collect(Collectors.toList()));
        return busDTO;
    }

    public Bus toModel(BusDTO busDTO) {
        Bus bus = new Bus();
        bus.setLicensePlate(busDTO.getLicensePlate());
        bus.setSeats(busDTO.getSeats());
        bus.setTravels(travelRepository.findAllById(busDTO.getTravel_idList()));
        return bus;
    }

    public List<BusDTO> toDTOList(List<Bus> buses) {
        return buses.stream().map(b -> toDTO(b)).collect(Collectors.toList());
    }

    public List<Bus> toModeList(List<BusDTO> busDTOs) {
        return busDTOs.stream().map(b -> toModel(b)).collect(Collectors.toList());
    }
}
