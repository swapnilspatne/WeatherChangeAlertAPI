package com.weatherChangeAlert.RESTAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weatherChangeAlert.RESTAPI.model.UserInputs;
import com.weatherChangeAlert.RESTAPI.repository.UserRepository;

@Service
public class UserInputService {

	@Autowired
	private UserRepository userRepository;

	public UserInputs saveUserInput(UserInputs newUser) {
		return userRepository.save(newUser);
	}
}
