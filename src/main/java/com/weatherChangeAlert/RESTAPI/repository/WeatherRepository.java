package com.weatherChangeAlert.RESTAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weatherChangeAlert.RESTAPI.model.WeatherData;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherData, Long> {

}
