package com.example.demo_room.Model;

import com.example.demo_room.Utils.Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Min(value = 1,message = "capacity should be 1 atleast")
    private int capacity;
    private String description;
    private String type;
    @ManyToOne
    @JoinColumn(name = "city_Id")
    private City city;
    @ManyToOne
    @JoinColumn(name = "site_Id")
    private Site site;
    @ManyToOne
    @JoinColumn(name = "floor_Id")
    private Floor floor;
    @JsonIgnore
    @OneToMany ( mappedBy = "room",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<BookedRoom> bookings ;

    public ConferenceRoom() {

    }
}
