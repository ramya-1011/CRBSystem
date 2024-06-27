package com.example.demo_room.Controller;

import com.example.demo_room.Exception.MyException;
import com.example.demo_room.Model.City;
import com.example.demo_room.Repository.CityRepo;
import com.example.demo_room.Repository.RoomRepo;
import com.example.demo_room.Service.CityService;
import com.example.demo_room.dto.CityResponse;
import com.example.demo_room.dto.Response;
import com.example.demo_room.dto.SiteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
@CrossOrigin("*")
public class CityController {
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private CityService cityService;
    @Autowired
    private RoomRepo roomRepo;
    @PostMapping("/add-city")
    public  ResponseEntity<CityResponse> addNewCity(@RequestBody City city) {
        CityResponse savedCity = cityService.addNewLocation(city);
       return ResponseEntity.status(savedCity.getStatusCode()).body(savedCity);
    }

    @GetMapping("/citiesList")
    public List<City> findAllCities(){
        return cityRepo.findAll();
    }
  @GetMapping("/byName/{cityName}")
    public ResponseEntity<Response> findCityByName(@PathVariable String cityName){
      Response city = cityService.getCityDetailsByName(cityName);
        return  ResponseEntity.status(city.getStatusCode()).body(city) ;
  }
  @PutMapping("update/{cityName}")
    public ResponseEntity<Response> updateCityDetails(@PathVariable String cityName, @RequestBody City city){
      Response response = cityService.updateCity(cityName,city);
      return  ResponseEntity.status(response.getStatusCode()).body(response) ;

  }
  @DeleteMapping("delete/{id}")
    public  ResponseEntity<Response> deleteCity(@PathVariable int id){
      Response response = cityService.deleteCity(id);
       cityService.deleteCity(id);
      return ResponseEntity.status(response.getStatusCode()).body(response) ;
//      return "city is deleted from db";
  }
  @GetMapping("cityList")
    public List<String> getCityList(){
        return cityRepo.findAllCityList();
  }
  @GetMapping("/byId/{id}")

      public ResponseEntity<?> getCityById(@PathVariable int id) {
          CityResponse cityResponse = cityService.getById(id);
          return new ResponseEntity<>(cityResponse, HttpStatus.OK);
      }

}
