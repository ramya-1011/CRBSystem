package com.example.demo_room.dto;

import com.example.demo_room.Model.Floor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class SiteResponse extends CommonAPIResponse {
    private int id;
    private String siteId;
    private String description;
    private String pinCode;
    private int totalFloors;
    private String locationName;
    private int statusCode;
    private List<Floor> floors;
}
