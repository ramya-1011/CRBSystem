package com.example.demo_room.Service;

import com.example.demo_room.Model.City;
import com.example.demo_room.dto.CityResponse;
import com.example.demo_room.dto.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICityService {
    CityResponse addNewLocation(City city);
    Response getAllCitiesWithDetails();
    List<String> getAllCityList();
    Response getCityDetailsByName(String CityName);
    Response updateCity(String cityName,City city);
    Response deleteCity(int cityId);
    CityResponse getById(int id);
}
