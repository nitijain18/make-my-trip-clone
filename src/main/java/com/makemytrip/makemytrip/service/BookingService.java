package com.makemytrip.makemytrip.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.makemytrip.makemytrip.model.Flight;
import com.makemytrip.makemytrip.model.Hotel;
import com.makemytrip.makemytrip.model.User;
import com.makemytrip.makemytrip.model.User.Booking;
import com.makemytrip.makemytrip.repository.FlightRepository;
import com.makemytrip.makemytrip.repository.HotelRepository;
import com.makemytrip.makemytrip.repository.UserRepository;

@Service
public class BookingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public Booking bookFlight(String userId, String flightId, int seats, double price) {
    //Instead of returning null, 
    // Spring gives you an Optional to force you to handle the absence of data safely.
    Optional<User> userOptional = userRepository.findById(userId);
    Optional<Flight> flightOptional = flightRepository.findById(flightId);

    if (userOptional.isPresent() && flightOptional.isPresent()) {

        User user = userOptional.get();
        Flight flight = flightOptional.get();

        if (flight.getAvailableSeats() >= seats) {

        flight.setAvailableSeats(flight.getAvailableSeats() - seats);
        flightRepository.save(flight);

        Booking booking = new Booking();
        booking.setType("Flight");
        booking.setBookingId(flightId);
        booking.setDate(LocalDate.now().toString());
        booking.setQuantity(seats);
        booking.setTotalPrice(price);

        user.getBookings().add(booking);
        userRepository.save(user);

        return booking;
    } 
    else {
        throw new RuntimeException("Not enough seats available");
    }
    }

        throw new RuntimeException("User or flight not found");
    }

    public Booking bookHotel(String userId, String hotelId, int rooms, double price) {

    Optional<User> userOptional = userRepository.findById(userId);
    Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);

    if (userOptional.isPresent() && hotelOptional.isPresent()) {

    User user = userOptional.get();
    Hotel hotel = hotelOptional.get();

    if (hotel.getAvailableRooms() >= rooms) {

        hotel.setAvailableRooms(hotel.getAvailableRooms() - rooms);
        hotelRepository.save(hotel);

        Booking booking = new Booking();
        booking.setType("Hotel");
        booking.setBookingId(hotelId);
        booking.setDate(LocalDate.now().toString());
        booking.setQuantity(rooms);
        booking.setTotalPrice(price);

        user.getBookings().add(booking);
        userRepository.save(user);

        return booking;
    } 
        else {
            throw new RuntimeException("Not enough rooms available");
        }
        }

        throw new RuntimeException("User or hotel not found");
    }
}
