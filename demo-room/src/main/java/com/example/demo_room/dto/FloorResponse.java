package com.example.demo_room.dto;

import com.example.demo_room.Model.ConferenceRoom;
import com.example.demo_room.Model.Floor;
import com.example.demo_room.dto.CommonAPIResponse;
import lombok.Data;

import java.util.List;

@Data
public class FloorResponse extends CommonAPIResponse {
    private int id;
    private String floorId;
    private String description;
    private int totalRooms;
    private String city;
    List<ConferenceRoom> rooms;
}
