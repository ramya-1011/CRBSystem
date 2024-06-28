package com.example.demo_room.Model;

import com.example.demo_room.Utils.Utils;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "enter City")
    private String city;
    @NotNull(message = "enter Site")
    private String site;
    @NotNull(message = "enter floor")
    private int floor;
    @Min(value = 1,message = "capacity should be 1 atleast")
    private int capacity;
    private String description;
    private String type;

    @OneToMany (targetEntity = BookedRoom.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<BookedRoom> bookings=new ArrayList<>();

    public ConferenceRoom(long id, String city, String building, int floor, int capacity, String description, String type) {
        this.id = id;
        this.city = city;
        this.site = building;
        this.floor = floor;
        this.capacity = capacity;
        this.description = description;
        this.type = type;

    }


    public ConferenceRoom() {

    }
}
