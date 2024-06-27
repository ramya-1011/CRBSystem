package com.example.demo_room.Service;

import com.example.demo_room.Model.ConferenceRoom;

import com.example.demo_room.dto.Response;
import com.example.demo_room.dto.RoomResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IRoomService {
     Response addNewRoom(long Room_id, String city, int floor, String building, String type, int capacity, String description)  ;

    List<String> getAllRoomTypes();
//   // List<String> findByRoomType(String type);


    List<String> getCityList();

    Response getAllRooms();




   Response deleteRoom(Long roomId);

    Response updateRoom( long Room_id,String city,int floor, String type,int capacity,String building, String description);

    Response getRoomById(Long roomId);


    List<ConferenceRoom> getRoomByCity(String City);

    Response getAvailableRoomsByDateAndType(LocalDate booking_date, LocalDateTime booking_time, LocalDateTime checkout_time, String roomType);

   Response getAllAvailableRooms();
}
