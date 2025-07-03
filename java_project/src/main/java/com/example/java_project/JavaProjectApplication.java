package com.example.java_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.java_project.Converters.BusConverter;
import com.example.java_project.Converters.DriverConverter;
import com.example.java_project.Converters.LineConverter;
import com.example.java_project.Converters.StationConverter;
import com.example.java_project.Converters.StationLineConverter;
import com.example.java_project.Converters.TravelConverter;

@SpringBootApplication
public class JavaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaProjectApplication.class, args);
	}

	@Bean
	public TravelConverter getTravelConverter() {
		return new TravelConverter();
	}

	@Bean
	public BusConverter getBusConverter() {
		return new BusConverter();
	}

	@Bean
	public DriverConverter getDriverConverter() {
		return new DriverConverter();
	}

	@Bean
	public StationConverter getStationConverter() {
		return new StationConverter();
	}

	@Bean
	public StationLineConverter getStationLineConverter() {
		return new StationLineConverter();
	}

	@Bean
	public LineConverter getLineConverter() {
		return new LineConverter();
	}

}
