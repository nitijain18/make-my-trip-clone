package com.makemytrip.makemytrip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.makemytrip.makemytrip.model.User;
import com.makemytrip.makemytrip.service.BookingService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/flight")
     public User.Booking bookFlight(@RequestParam String userId,@RequestParam String flightId,@RequestParam int seats,@RequestParam double price){
     return bookingService.bookFlight(userId, flightId, seats, price);
    }
    @PostMapping("/hotel")
     public User.Booking bookHotel(@RequestParam String userId,@RequestParam String hotelId,@RequestParam int rooms,@RequestParam double price){
        return bookingService.bookHotel(userId, hotelId, rooms, price);
     }
}
