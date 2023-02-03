package com.weatherChangeAlert.RESTAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weatherChangeAlert.RESTAPI.model.UserInputs;

@Repository
public interface UserRepository extends JpaRepository<UserInputs, Long>{

}
