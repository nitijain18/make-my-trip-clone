package com.makemytrip.makemytrip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.makemytrip.makemytrip.model.Flight;
import com.makemytrip.makemytrip.model.Hotel;
import com.makemytrip.makemytrip.repository.FlightRepository;
import com.makemytrip.makemytrip.repository.HotelRepository;

//handles http request & return ans in json format
@RestController
@CrossOrigin(origins="*") //This allows requests from any frontend.
public class RootController {

  @Autowired
  private HotelRepository hotelRepository;
  @Autowired
  private FlightRepository flightRepository;


    @GetMapping("/")
      public String home(){
        return "my name is niti";
    }
    
    @GetMapping("/hotel")
      public ResponseEntity<List<Hotel>> getAllHotel(){
      List<Hotel> hotels = hotelRepository.findAll();
      return ResponseEntity.ok(hotels);
    }

    @GetMapping("/flight")
    public ResponseEntity<List<Flight>> getAllFlight(){
      List<Flight> flights = flightRepository.findAll();
      return ResponseEntity.ok(flights);
    }

}
