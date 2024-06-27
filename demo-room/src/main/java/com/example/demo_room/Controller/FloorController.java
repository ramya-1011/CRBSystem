package com.example.demo_room.Controller;

import com.example.demo_room.Model.Floor;
import com.example.demo_room.Model.Site;
import com.example.demo_room.Repository.FloorRepo;
import com.example.demo_room.Service.FloorService;
import com.example.demo_room.dto.CityResponse;
import com.example.demo_room.dto.FloorResponse;
import com.example.demo_room.dto.SiteResponse;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/floor")
public class FloorController {
    @Autowired
    private FloorRepo floorRepo;
    @Autowired
    private FloorService floorService;
@GetMapping("/getAll")
    public List<Floor> getAllFloors(){
    return floorRepo.findAll();
}
@GetMapping("/byId/{id}")
    public ResponseEntity<?> getFloorById(@PathVariable int id){
    Floor getFloor=floorService.getById(id);
    return new ResponseEntity<>(getFloor, HttpStatus.OK);


}
    @PostMapping("/add")
    public ResponseEntity<Floor> addFloor(@RequestBody Floor floor){

        Floor savedFloor = null;
        try {
            savedFloor = floorService.addNewLocation(floor);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(savedFloor.getResponseCode()).body(savedFloor);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<Floor> updateFloor(@PathVariable int id,@RequestBody Floor floor){
        Floor updatedFloor = floorService.updateFloor(id, floor);
        if (updatedFloor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedFloor);


    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<FloorResponse> deleteFloor(@PathVariable int id){
        FloorResponse response =floorService.deleteFloor(id);
        return ResponseEntity.status(response.getResponseCode()).body(response);
    }


    }


