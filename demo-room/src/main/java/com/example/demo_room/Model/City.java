package com.example.demo_room.Model;

import jakarta.persistence.*;
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
    private String name;
    private String state;
    private String totalSites;
    @OneToMany(targetEntity = Site.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id",referencedColumnName = "name")
    private List<Site> sites;
    @OneToMany(targetEntity = ConferenceRoom.class,cascade = CascadeType.ALL)
  //  @JoinColumn(name = "cc_fk",referencedColumnName = "name")
    private List<ConferenceRoom> rooms;
}
