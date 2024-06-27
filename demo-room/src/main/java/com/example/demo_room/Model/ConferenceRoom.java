package com.example.demo_room.Model;

import com.example.demo_room.Utils.Utils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Component

@AllArgsConstructor
@Table(name = "rooms")

public class ConferenceRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String city;
    private String site;
    private int floor;
    private int capacity;
    private String description;
    private String type;

    private boolean isBooked;
    @OneToMany (targetEntity = BookedRoom.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
   // @JoinColumn(name = "crb_fk",referencedColumnName = "id")
    private List<BookedRoom> bookings;
    public ConferenceRoom(){
        this.bookings=new ArrayList<>();
        isBooked = false;
    }

    public ConferenceRoom(long id, String city, String building, int floor, int capacity, String description, String type) {
        this.id = id;
        this.city = city;
        this.site = building;
        this.floor = floor;
        this.capacity = capacity;
        this.description = description;
        this.type = type;
        isBooked = false;
    }
public ConferenceRoom(boolean isBooked) {
    this.isBooked = false;
    this.isBooked = isBooked;
    }

    public void addBooking(BookedRoom booking) {
        if (bookings == null) {
            bookings = new ArrayList<>();
        }
        bookings.add(booking);
        booking.setRoom(this);
        isBooked = true;
        String bookingConfirmationCode = Utils.generateRandomConfirmationCode(10);
        booking.setConfirmationCode(bookingConfirmationCode);

    }
}
