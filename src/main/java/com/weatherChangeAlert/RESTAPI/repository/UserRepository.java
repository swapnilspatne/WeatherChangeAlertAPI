package com.weatherChangeAlert.RESTAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weatherChangeAlert.RESTAPI.model.UserInputs;

public interface UserRepository extends JpaRepository<UserInputs, Long>{

}
