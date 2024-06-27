package com.example.demo_room.dto;

import com.example.demo_room.Model.City;
import com.example.demo_room.Model.ConferenceRoom;
import com.example.demo_room.Model.Site;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CityResponse extends CommonAPIResponse{
    private long id;
    private String name;
    private String state;
    private String totalSites;
    private List<Site> sites;
    private List<ConferenceRoom> rooms;
    private int statusCode;
//    private List<City> cities;

}






