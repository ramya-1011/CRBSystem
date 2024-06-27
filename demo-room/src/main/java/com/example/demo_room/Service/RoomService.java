package com.example.demo_room.Service;
import com.example.demo_room.Exception.MyException;
import com.example.demo_room.Model.ConferenceRoom;
import com.example.demo_room.Repository.RoomRepo;
import com.example.demo_room.Utils.Utils;
import com.example.demo_room.dto.Response;
import com.example.demo_room.dto.RoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


    @Service
    @RequiredArgsConstructor
    public class RoomService implements IRoomService {
        private final RoomRepo roomRepo;

        @Override
        public Response addNewRoom(long id, String city,
                                   int floor, String building, String type, int capacity, String description) {
            Response response = new  Response();


            try {
                ConferenceRoom room = new ConferenceRoom();
                room.setId(id);
                room.setType(type);
                room.setFloor(floor);
                room.setCity(city);
                room.setCapacity(capacity);
                room.setDescription(description);
                room.setSite(building);
                ConferenceRoom savedRoom = roomRepo.save(room);
                RoomResponse roomDTO = Utils.mapConferenceRoomEntityToRoomResponse(savedRoom);
                response.setStatusCode(200);
                response.setMessage("successful");
               response.setRoomResponse(roomDTO);

            } catch (Exception e) {
                response.setStatusCode(500);
                response.setMessage("Error saving a room " + e.getMessage());
            }
            return response;

        }

        @Override
        public List<String> getAllRoomTypes() {
            return roomRepo.findByType();
        }


        @Override
        public List<String> getCityList() {

            return roomRepo.findByCity();
        }

        @Override
        public Response getAllRooms() {
            Response response=new Response();
            try{
            List<ConferenceRoom> RoomsList = roomRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
            List<RoomResponse> RoomDTOList = Utils.mapCRoomListEntityToCRoomListDTO (RoomsList);
                response.setStatusCode(200);
                response.setMessage("successful");
            response.setRoomsList(RoomDTOList);

            } catch (Exception e) {
                response.setStatusCode(500);
                response.setMessage("Error saving a room " + e.getMessage());
            }
            return  response;

        }



        @Override
        public Response deleteRoom(Long roomId) {
                Response response=new Response();
            try {
                roomRepo.findById(roomId).orElseThrow(() -> new MyException("Room Not Found"));
                roomRepo.deleteById(roomId);
                response.setStatusCode(200);
                response.setMessage("successful");

            } catch (MyException e) {
                response.setStatusCode(404);
                response.setMessage(e.getMessage());
            } catch (Exception e) {
                response.setStatusCode(500);
                response.setMessage("Error saving a room " + e.getMessage());
            }
            return response;


        }

        @Override
        public Response updateRoom(long Room_id, String city, int floor, String type,
                                   int capacity, String building, String description) {
            Response response = new Response();
            try{
                ConferenceRoom Room = roomRepo .findById(Room_id).orElseThrow(() -> new MyException("Room Not Found"));
                if (type != null)   Room.setType(type);
                if (description != null)  Room.setDescription(description);
                if (city != null)  Room.setCity(city);
                if (floor != 0)  Room.setFloor(floor);
                if (capacity != 0)  Room.setCapacity(capacity);
                ConferenceRoom updatedRoom = roomRepo .save( Room);
                RoomResponse roomDTO = Utils.mapConferenceRoomEntityToRoomResponse (updatedRoom);
                response.setStatusCode(200);
                response.setMessage("successful");
                response.setRoomResponse(roomDTO);
            }catch (MyException e){
                response.setStatusCode(404);
                response.setMessage(e.getMessage());
            }catch (Exception e) {
                response.setStatusCode(500);
                response.setMessage("Error saving a room " + e.getMessage());
            }
            return response;
        }


        @Override
        public Response getRoomById(Long roomId) {
            Response response=new Response();
            try {
                ConferenceRoom cRoom = roomRepo.findById(roomId).orElseThrow(() -> new MyException("Room Not Found"));
                RoomResponse cRoomDTO = Utils.mapCRoomEntityToCRoomDTOPlusBookings(cRoom);
                response.setStatusCode(200);
                response.setMessage("successful");
                response.setRoomResponse(cRoomDTO);
            }catch (MyException e) {
                response.setStatusCode(404);
                response.setMessage(e.getMessage());
            } catch (Exception e) {
                response.setStatusCode(500);
                response.setMessage("Error saving a room " + e.getMessage());
            }
            return response;

        }


        @Override
        public List<ConferenceRoom> getRoomByCity(String city) {

            return roomRepo.findRoomByCity(city);
        }

        @Override
        public Response getAvailableRoomsByDateAndType(LocalDate booking_date, LocalDateTime booking_time,
                                                       LocalDateTime checkout_time, String roomType) {
            Response response=new Response();
            try {
                List<ConferenceRoom> availableRooms = roomRepo.findAvailableRoomBYDateAndType(booking_date,booking_time, checkout_time, roomType);
                List<RoomResponse> roomDTOList = Utils.mapCRoomListEntityToCRoomListDTO(availableRooms);
                response.setStatusCode(200);
                response.setMessage("successful");
                response.setRoomsList(roomDTOList);
            }catch (Exception e){
                response.setStatusCode(500);
                response.setMessage("Error saving a room " + e.getMessage());

            }
            return response;
        }


        @Override
        public Response getAllAvailableRooms() {
            Response response=new Response();
            try {
                List<ConferenceRoom> RoomList = roomRepo.findAllAvailableRooms();
                List<RoomResponse> roomDTOList = Utils.mapCRoomListEntityToCRoomListDTO(RoomList);
                response.setStatusCode(200);
                response.setMessage("successful");
                response.setRoomsList(roomDTOList);
            }catch (MyException e) {
                response.setStatusCode(404);
                response.setMessage(e.getMessage());
            } catch (Exception e) {
                response.setStatusCode(500);
                response.setMessage("Error saving a room " + e.getMessage());
            }

            return response;


        }
    }














