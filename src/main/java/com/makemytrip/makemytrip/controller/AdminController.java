package com.makemytrip.makemytrip.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.makemytrip.makemytrip.model.Flight;
import com.makemytrip.makemytrip.model.Hotel;
import com.makemytrip.makemytrip.model.User;
import com.makemytrip.makemytrip.repository.FlightRepository;
import com.makemytrip.makemytrip.repository.HotelRepository;
import com.makemytrip.makemytrip.repository.UserRepository;


@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="*")
public class AdminController {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
    List<User> user=userRepository.findAll();
    return ResponseEntity.ok(user);
    }

     @PostMapping("/flight")
    public Flight addflight(@RequestBody Flight flight){
        return flightRepository.save(flight);
    }

    @PostMapping("/hotel")
    public Hotel addHotel(@RequestBody Hotel hotel){
        return hotelRepository.save(hotel);
    }
    @PutMapping("/flight/{id}")
    public ResponseEntity<Flight> editFlight(@PathVariable String id, @RequestBody Flight updatedFlight){
    Optional<Flight> flightOptional=flightRepository.findById(id);
    if(flightOptional.isPresent()){
        Flight flight = flightOptional.get();
        flight.setFlightName(updatedFlight.getFlightName());
        flight.setFrom(updatedFlight.getFrom());
        flight.setTo(updatedFlight.getTo());
        flight.setDepartureTime(updatedFlight.getDepartureTime());
        flight.setArrivalTime(updatedFlight.getArrivalTime());
        flight.setPrice(updatedFlight.getPrice());
        flight.setAvailableSeats(updatedFlight.getAvailableSeats());
        flightRepository.save(flight);
        return  ResponseEntity.ok(flight);
    }
    return ResponseEntity.notFound().build();
}
@PutMapping("hotel/{id}")
public ResponseEntity<Hotel> editHotel (@PathVariable String id, @RequestBody Hotel updatedHotel){
    Optional<Hotel> hotelOptional=hotelRepository.findById(id);
    if(hotelOptional.isPresent()){
        Hotel hotel = hotelOptional.get();
        hotel.setHotelName(updatedHotel.getHotelName());
        hotel.setLocation(updatedHotel.getLocation());
        hotel.setAvailableRooms(updatedHotel.getAvailableRooms());
        hotel.setPricePerNight(updatedHotel.getPricePerNight());
        hotel.setAmenities((updatedHotel.getAmenities()));
        hotelRepository.save(hotel);
        return ResponseEntity.ok(hotel);
        }
    return ResponseEntity.notFound().build();
}

}
