package com.weatherChangeAlert.RESTAPI.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.weatherChangeAlert.RESTAPI.model.UserInputs;
import com.weatherChangeAlert.RESTAPI.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserInputServiceTest {

	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserInputService userInputService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(userInputService);
	}

	@Test
	public void saveUserInputTest() {
		UserInputs newUser = Mockito.mock(UserInputs.class);
		Mockito.when(userRepository.save(newUser)).thenReturn(newUser);
		userInputService.saveUserInput(newUser);
		Mockito.verify(userRepository,times(1)).save(any());
	}
}
