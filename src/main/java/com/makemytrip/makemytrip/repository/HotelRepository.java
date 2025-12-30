package com.makemytrip.makemytrip.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.makemytrip.makemytrip.model.Hotel;

public interface HotelRepository extends MongoRepository<Hotel, String>{

}
