package com.weatherChangeAlert.RESTAPI.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.weatherChangeAlert.RESTAPI.model.OpenWeatherMapResponse;
import com.weatherChangeAlert.RESTAPI.model.UserInputs;
import com.weatherChangeAlert.RESTAPI.model.WeatherData;
import com.weatherChangeAlert.RESTAPI.model.WeatherStack;
import com.weatherChangeAlert.RESTAPI.repository.UserRepository;
import com.weatherChangeAlert.RESTAPI.repository.WeatherRepository;

@Service
public class CollectWeatherInfoService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WeatherRepository weatherRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public void consumeAPI(UserInputs newUser) {
		double average = getAverageTemp(newUser.getLocation());		
		checkCriteria(average, newUser);		
	}


	private double getAverageTemp(String location) {
		OpenWeatherMapResponse owmr = restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?q="+location+"&appid=fe4feefa8543e06d4f3c66d92c61b69c", 
				OpenWeatherMapResponse.class);		
		saveResponseData(owmr);				
		
		WeatherStack ws = restTemplate.getForObject("http://api.weatherstack.com/current?access_key=94e17671a88f6d713bc6a97380b95877&query="+location, 
				WeatherStack.class);		
		saveResponseData(ws);
		
		double average = calculateMean(owmr, ws);
		return average;
	}
	
	private boolean checkCriteria(double average, UserInputs newUser) {
//		System.err.println("Average:"+average+", Criteria:"+newUser.getCriteria()+", Tempareture:"+newUser.getTempareture());
		
		if(newUser.getCriteria().equals("0") && newUser.getTempareture() > average)
		{
//			System.err.println("Tempareture went below "+newUser.getTempareture());
			return true;
		}
		else if(newUser.getCriteria().equals("1") && newUser.getTempareture() < average)
		{
//			System.err.println("Tempareture went above "+newUser.getTempareture());
			return true;
		}
		else return false;
	}


	private double calculateMean(OpenWeatherMapResponse owmr, WeatherStack ws) {
		double owmrTempareture = owmr.getMain().getTemp()-273.15;
		double wsTemperature = ws.getCurrent().getTemperature();
		List<Double> tempretures = Arrays.asList(owmrTempareture, wsTemperature);
		
		return tempretures.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
	}


	private void saveResponseData(OpenWeatherMapResponse owmr) {
		WeatherData weatherData = new WeatherData();
		weatherData.setLatitude(owmr.getCoord().getLat());
		weatherData.setLocation(owmr.getName());
		weatherData.setLongitude(owmr.getCoord().getLon());
		weatherData.setSource("openWeatherMap");
		weatherData.setTempareture(owmr.getMain().getTemp()-273.15);
		weatherData.setDateTime(LocalDateTime.ofInstant(Instant.now(), TimeZone.getDefault().toZoneId()));
		weatherRepository.save(weatherData);
	}
	
	private void saveResponseData(WeatherStack ws) {
		WeatherData weatherData = new WeatherData();
		weatherData.setLatitude(Double.parseDouble(ws.getLocation().getLat()));
		weatherData.setLocation(ws.getLocation().getName());
		weatherData.setLongitude(Double.parseDouble(ws.getLocation().getLon()));
		weatherData.setSource("WeatherStack");
		weatherData.setTempareture(ws.getCurrent().getTemperature());
		weatherData.setDateTime(LocalDateTime.ofInstant(Instant.now(), TimeZone.getDefault().toZoneId()));
		weatherRepository.save(weatherData);
	}
	
	@Scheduled(cron = "0 */3 * * * *")
	private void sendNotification() {
		List<UserInputs> allUserInputs = userRepository.findAll().stream().filter(e -> !e.isNotificationSent()).collect(Collectors.toList());
		List<String> locations = allUserInputs.stream().map(UserInputs::getLocation).distinct().collect(Collectors.toList());
		
		locations.forEach(location -> {
			double averageTemp = getAverageTemp(location);
			List<UserInputs> updatedUserInputs = allUserInputs.stream().filter(e -> e.getLocation().equals(location)).
					filter(e -> checkCriteria(averageTemp, e)).
			map(e -> {pushNotification(e);
			e.setNotificationSent(true);
			return e;				
			}).collect(Collectors.toList());
			userRepository.saveAll(updatedUserInputs);
		});	
		
	}

	private void pushNotification(UserInputs e) {
		if(e.getCriteria() != null && e.getCriteria().equals("0"))
			System.err.println("Sending Notification to "+e.getName()+" on email: "+e.getEmail()+" :: Tempareture went below "+ e.getTempareture());
		else 
		System.err.println("Sending Notification to "+e.getName()+" on email: "+e.getEmail()+" :: Tempareture went above "+ e.getTempareture());
	}
}
