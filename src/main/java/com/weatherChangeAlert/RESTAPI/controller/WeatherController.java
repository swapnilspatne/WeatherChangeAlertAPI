package com.weatherChangeAlert.RESTAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weatherChangeAlert.RESTAPI.model.WeatherData;
import com.weatherChangeAlert.RESTAPI.repository.WeatherRepository;

@RestController
public class WeatherController {

	@Autowired
	private WeatherRepository weatherRepository;
	
	@PostMapping("/weatherDetails")
	WeatherData newData(@RequestBody WeatherData newData) {
		
		if(newData.getSource().equals("openweathermap.org"))
		{
			newData.setTempareture(newData.getTempareture() - 273.15);
		}
		
		return weatherRepository.save(newData);
	}
	
	@GetMapping("locationList")
	List<WeatherData> getAllWeatherData(){
		return weatherRepository.findAll();
	}
	
}
