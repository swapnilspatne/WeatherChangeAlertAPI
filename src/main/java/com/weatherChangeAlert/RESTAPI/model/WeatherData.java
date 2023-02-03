package com.weatherChangeAlert.RESTAPI.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class WeatherData {

	@Id
	@GeneratedValue
	private Long id;
	private String location;
	private Date dateTime;
	private double tempareture;
	private String source;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getTempareture() {
		return tempareture;
	}

	public void setTempareture(double tempareture) {
		this.tempareture = tempareture;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
