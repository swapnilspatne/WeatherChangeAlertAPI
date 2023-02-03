package com.weatherChangeAlert.RESTAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weatherChangeAlert.RESTAPI.model.UserInputs;
import com.weatherChangeAlert.RESTAPI.service.CollectWeatherInfoService;
import com.weatherChangeAlert.RESTAPI.service.UserInputService;

@RestController("/weatherApp")
public class UserController {

	@Autowired
	private UserInputService userInputService;
	@Autowired
	private CollectWeatherInfoService collectWeatherInfo;

	@PostMapping("/userDetails")
	@CrossOrigin
	public UserInputs newUser(@RequestBody UserInputs newUser) {
		collectWeatherInfo.consumeAPI(newUser);
		return userInputService.saveUserInput(newUser);
	}
}
