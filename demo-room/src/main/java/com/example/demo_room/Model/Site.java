package com.example.demo_room.Model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Transactional
@Table(name = "site")
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String siteId;
    private String description;
    private String pinCode;
    private int totalFloors;
    private String locationName;
    @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "location_id")
    private City city;
    @OneToMany(targetEntity = Floor.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_fs_id",referencedColumnName = "siteId")
    private List<Floor> floors;

}
