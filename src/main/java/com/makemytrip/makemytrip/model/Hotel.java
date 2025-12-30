package com.makemytrip.makemytrip.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="hotels")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Hotel {

    @Id
    private String id;
    private String hotelName;
    private String location;
    private double pricePerNight;
    private int availableRooms;
    private String amenities;
}
