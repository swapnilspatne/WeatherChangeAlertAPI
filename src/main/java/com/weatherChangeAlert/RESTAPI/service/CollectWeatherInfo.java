package com.weatherChangeAlert.RESTAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CollectWeatherInfo {
	
	private final RestTemplate restTemplate;
	
	@Autowired
	public CollectWeatherInfo(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}	
	
	@Autowired
	public CollectWeatherInfo() {
		restTemplate = null;
	}
	
	public void consumeAPI(String location) {
		String output = restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?q="+location+"&appid=fe4feefa8543e06d4f3c66d92c61b69c", 
				String.class);
		System.out.println("Weather OpenWeatherMap data -"+output);
	}

}
