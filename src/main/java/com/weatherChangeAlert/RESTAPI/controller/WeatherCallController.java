package com.weatherChangeAlert.RESTAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.weatherChangeAlert.RESTAPI.service.CollectWeatherInfo;

@Controller
public class WeatherCallController {

	private final CollectWeatherInfo collectWeatherInfo;

	@Autowired
	public WeatherCallController(CollectWeatherInfo collectWeatherInfo) {
		this.collectWeatherInfo = collectWeatherInfo;
	}

	@GetMapping("/")
	public void getData(String location) {
		collectWeatherInfo.consumeAPI(location);
	}
}
