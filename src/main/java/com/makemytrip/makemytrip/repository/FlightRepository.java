package com.makemytrip.makemytrip.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.makemytrip.makemytrip.model.Flight;

public interface FlightRepository extends MongoRepository<Flight, String>{

}
