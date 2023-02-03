package com.weatherChangeAlert.RESTAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weatherChangeAlert.RESTAPI.model.WeatherData;

public interface WeatherRepository extends JpaRepository<WeatherData, Long> {

}
