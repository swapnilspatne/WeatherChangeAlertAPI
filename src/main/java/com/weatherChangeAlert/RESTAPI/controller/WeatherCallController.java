package com.weatherChangeAlert.RESTAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.weatherChangeAlert.RESTAPI.model.UserInputs;
import com.weatherChangeAlert.RESTAPI.service.CollectWeatherInfoService;

@Controller
public class WeatherCallController {

	@Autowired
	private CollectWeatherInfoService collectWeatherInfo;

	@GetMapping("/")
	public void getData(UserInputs newUser) {
		collectWeatherInfo.consumeAPI(newUser);
	}
}
