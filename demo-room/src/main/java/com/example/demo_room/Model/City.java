package com.example.demo_room.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "name of city cannot be empty")
    private String name;
    @Size(max = 20, message = "The attribute must be at most 20 characters long.")
    private String state;
    @Min(value = 1,message = "minimum value should be 1")
    private String totalSites;
    @OneToMany(targetEntity = Site.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id",referencedColumnName = "name")
    private List<Site> sites;
    @OneToMany(targetEntity = ConferenceRoom.class,cascade = CascadeType.ALL)
    private List<ConferenceRoom> rooms;
}
