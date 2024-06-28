package com.example.demo_room.Model;

import com.example.demo_room.dto.CommonAPIResponse;
import com.example.demo_room.dto.FloorResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Entity
@Getter
@Setter
public class Floor extends CommonAPIResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String floorId;
    private String description;
    @Min(value =0,message = "rooms cant be less than 0")
    private int totalRooms;
    private String city;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "site_id")
    private Site site;
    @OneToMany(targetEntity = ConferenceRoom.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cf_id",referencedColumnName = "floorId")
    private List<ConferenceRoom> rooms;

}
