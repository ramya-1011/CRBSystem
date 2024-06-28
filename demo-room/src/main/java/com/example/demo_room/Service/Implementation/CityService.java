package com.example.demo_room.Service.Implementation;

import com.example.demo_room.Exception.MyException;
import com.example.demo_room.Model.City;
import com.example.demo_room.Repository.CityRepo;
import com.example.demo_room.Service.Interface.ICityService;
import com.example.demo_room.Utils.Constants;
import com.example.demo_room.Utils.Utils;
import com.example.demo_room.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class CityService implements ICityService {

    @Autowired
    private final CityRepo cityRepo;

    @Override
    public CityResponse addNewLocation(City city) {
       CityResponse response = new CityResponse();
        Response response1=new Response();

        try {
            City city1 = new City();
            city1.setId(city.getId());
            city1.setName(city.getName());
            city1.setState(city.getState());
            city1.setTotalSites(city.getTotalSites());
            city1.setSites(city.getSites());
            City savedCity = cityRepo.save(city1);
            response = Utils.mapCityEntityToCityResponse(savedCity);
           response1.setCityResponse(response);
           response.setResponseCode(Constants.ResponseCode.SUCCESS.value());
            response.setResponseMessage("location added Successfully");
            response.setStatusCode(200);
            response1.setMessage("successful");

        } catch (Exception e) {
            response.setResponseCode(Constants.ResponseCode.FAILED.value());
            response.setResponseMessage("Error in adding location ");
            response.setStatusCode(500);
            response1.setMessage("Error saving a city " + e.getMessage());
        }
return response;
    }

    @Override
    public  Response getAllCitiesWithDetails() {
        Response response=new Response();
        try{
            List<City> cityList =  cityRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
            List< CityResponse> cityDTOList = Utils.mapCityListEntityToCityListDTO (cityList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setCityList(cityDTOList);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error fetching a cities " + e.getMessage());
        }
        return  response;

    }

    @Override
    public List<String> getAllCityList() {
        return cityRepo.findAllCityList();
    }

    @Override
    public Response getCityDetailsByName(String cityName) {
        Response response=new Response();
        try{
            City city =  cityRepo.getByName(cityName).orElseThrow(() -> new MyException("City Not Found"));
             CityResponse cityDTO = Utils.mapCityEntityToCityResponse (city);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setCity(city);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error fetching a city " + e.getMessage());
        }
        return  response;


    }

    @Override
    public Response updateCity(String cityName, City cityRequest) {
        Response response = new Response();
        try{
            City city = cityRepo .getByName(cityName).orElseThrow(() -> new MyException("City Not Found"));
            if (cityRequest.getId() != 0) city.setId(cityRequest.getId());
            if(cityRequest.getName() !=null) city.setName(cityRequest.getName());
            if(cityRequest.getState()!=null) city.setState(cityRequest.getState());
            if(cityRequest.getTotalSites()!=null) city.setTotalSites(cityRequest.getTotalSites());
            if(cityRequest.getSites()!=null) city.setSites(cityRequest.getSites());
            cityRequest.setSites(cityRequest.getSites());
            City updatedCity = cityRepo .save( city);
            CityResponse cityDTO = Utils.mapCityEntityToCityResponse (updatedCity);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setCityResponse(cityDTO);
        }catch (MyException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a city " + e.getMessage());
        }
        return response;

    }

    @Override
    public Response deleteCity(int cityId) {
        Response response=new Response();
        try {
            cityRepo.findById(cityId).orElseThrow(() -> new MyException("city Not Found"));
            cityRepo.deleteById(cityId);
            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (MyException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a city " + e.getMessage());
        }

        return response;
    }
    @Override
    public CityResponse getById(int id)   {
CityResponse response=new CityResponse();
        Response Response=new Response();


        try {
            City city = cityRepo.findById(id).orElseThrow(() -> new MyException("Site Not Found"));
            CityResponse  cityDto = Utils.mapCityEntityToCityResponse(city);
            response.setStatusCode(200);
            Response.setMessage("successful");
            cityDto.setResponseCode(Constants.ResponseCode.SUCCESS.value());
            cityDto.setResponseMessage("found site with these details");

            Response.setCityResponse(cityDto);

        }catch (MyException e) {
            response.setStatusCode(404);
            response.setResponseMessage("no city found");
            Response.setMessage(e.getMessage());
            Response.setCityResponse(response);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setResponseMessage("error");
            Response.setCityResponse(response);
            Response.setMessage("Error saving a room " + e.getMessage());
        }
        return Response.getCityResponse();

    }
}
