package com.weatherChangeAlert.RESTAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weatherChangeAlert.RESTAPI.model.UserInputs;
import com.weatherChangeAlert.RESTAPI.repository.UserRepository;

@RestController
@RequestMapping("/weatherApp")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private WeatherCallController weatherCallController;
	
	@PostMapping("/userDetails")
	@CrossOrigin
	UserInputs newUser(@RequestBody UserInputs newUser) {
		weatherCallController.getData(newUser.getLocation());
		return userRepository.save(newUser);
	}
	
	@GetMapping("userList")
	List<UserInputs> getAllUsers(){
		return userRepository.findAll();
	}	
}
