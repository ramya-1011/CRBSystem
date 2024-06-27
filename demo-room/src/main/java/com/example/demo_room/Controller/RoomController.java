package com.example.demo_room.Controller;

import com.example.demo_room.Model.City;
import com.example.demo_room.Model.ConferenceRoom;
import com.example.demo_room.Repository.CityRepo;
import com.example.demo_room.Repository.RoomRepo;
import com.example.demo_room.Service.IRoomService;
import com.example.demo_room.dto.Response;
import com.example.demo_room.dto.RoomResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
@CrossOrigin("*")
public class RoomController {
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private final IRoomService roomService;
    @PostMapping("/add-room")
public  ResponseEntity< Response> addNewRoom(
        @RequestParam("id") long id,
        @RequestParam("type")  String type,
        @RequestParam("city") String city,
        @RequestParam("building") String building,
        @RequestParam("floor") int floor,
        @RequestParam("capacity") int capacity,
        @RequestParam("description") String description) {
    Response savedRoom = roomService.addNewRoom(  id,   city,floor, building, type,  capacity,  description);
    return  ResponseEntity.status(savedRoom.getStatusCode()).body(savedRoom);

}
    @GetMapping("/types")
    public List<String> getRoomTypes() {
        return roomService.getAllRoomTypes();
    }
    @GetMapping("/types-city")
    public List<String> getCityList() {
        return roomService.getCityList();
    }

    @GetMapping("/allRooms")
    public ResponseEntity<Response> getAllRooms(){
        Response response = roomService.getAllRooms();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/byCity/{city}")
    public List<ConferenceRoom> getByCity(@PathVariable String city){
        return roomRepo.findRoomByCity(city);
    }
    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<Response> deleteRoom(@PathVariable Long roomId) {
        Response response = roomService.deleteRoom(roomId);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }

    @GetMapping("/room-by-id/{roomId}")
    public ResponseEntity<Response> getRoomById(@PathVariable Long roomId) {
        Response response = roomService.getRoomById(roomId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/all-available-rooms")
    public  List<ConferenceRoom> getAvailableRooms() {

        return  roomRepo.findAllAvailableRooms();
    }
    @GetMapping("/rooms-by-city/{city}")
    public List<ConferenceRoom> getRoomByCity(@PathVariable String city) {

        return roomRepo.findRoomByCity(city);
    }

    @PutMapping("/update/{roomId}")

    public ResponseEntity<Response> updateRoom(@PathVariable Long roomId,
                                               @RequestParam(value = "city", required = false) String city,
                                               @RequestParam(value = "floor", required = false) int floor,
                                               @RequestParam(value = "type", required = false) String type,
                                               @RequestParam(value = "capacity", required = false) int capacity,
                                               @RequestParam(value = "building", required = false) String building,
                                               @RequestParam(value = "description", required = false) String description

    ) {
        Response response = roomService.updateRoom( roomId,city, floor, type, capacity,building, description);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }




}
