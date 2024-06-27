package com.example.demo_room.Service;

import com.example.demo_room.Exception.MyException;
import com.example.demo_room.Model.Floor;
import com.example.demo_room.Repository.FloorRepo;
import com.example.demo_room.Utils.Constants;
import com.example.demo_room.Utils.Utils;
import com.example.demo_room.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FloorService {
    @Autowired
    private FloorRepo floorRepo;

    public Floor addNewLocation( Floor floorInput) throws Exception {
         Floor floor =new Floor();
        Response response1=new Response( );

        try {
             Floor floor1= new Floor();
             floor1.setId(floorInput.getId());
             floor1.setFloorId(floorInput.getFloorId());
             floor1.setTotalRooms(floorInput.getTotalRooms());
             floor1.setCity(floorInput.getCity());
             floor1.setDescription(floorInput.getDescription());
             floor1.setRooms(floorInput.getRooms());
            Floor savedFloor=floorRepo.save(floor1);
            savedFloor.setResponseCode(Constants.ResponseCode.SUCCESS.value());
            savedFloor.setResponseMessage("Floor added Successfully");
            response1.setFloor(savedFloor);
            response1.setStatusCode(200);
            response1.setMessage("successful");

        } catch (Exception e) {
            floor.setResponseCode(Constants.ResponseCode.FAILED.value());
            floor.setResponseMessage("Error in adding floor ");
            response1.setFloor(floor);
        }
        return response1.getFloor();
    }

        public Floor updateFloor(int id, Floor floorRequest) {
            Response response = new Response();

            Floor floor=floorRepo.findById(id).orElse( null);
            if(floor!=null){
                if(floorRequest.getId()!=0) floor.setId(floorRequest.getId());
                if(floorRequest.getFloorId()!=null) floor.setFloorId(floorRequest.getFloorId());
                if(floorRequest.getDescription()!=null) floor.setDescription(floor.getDescription());
                if(floorRequest.getTotalRooms()!=0) floor.setTotalRooms(floor.getTotalRooms());
                if(floorRequest.getCity()!=null)floor.setCity(floorRequest.getCity());
                if(floorRequest.getRooms()!=null)floor.setRooms(floorRequest.getRooms());
                floorRequest.setRooms(floorRequest.getRooms());
                floor.setResponseCode(Constants.ResponseCode.SUCCESS.value());
                floor.setResponseMessage("updated");
                response.setMessage("successful");
                response.setFloor(floor);
                return floorRepo.save(floor);
            }
            return null;


    }
    public Floor getById(int id) {
        Floor floorResponse=new Floor();
        Response response=new Response();

        try {
             Floor floor =  floorRepo.findById(id).orElseThrow(() -> new MyException("Site Not Found"));
            FloorResponse  floorDTO = Utils.mapFloorEntityToFloorResponse(floor);
            floor.setResponseMessage("fetched successfully");
            floor.setResponseCode(Constants.ResponseCode.SUCCESS.value());
            response.setFloorResponse(floorDTO);
            response.setFloor(floor);
        }catch (MyException e) {
            floorResponse.setResponseMessage("error ");
            floorResponse.setResponseCode(404);
        } catch (Exception e) {
            response.setStatusCode(500);
            floorResponse.setResponseMessage("error"+ e.getMessage());
        }
        return  response.getFloor();

    }
    public FloorResponse deleteFloor(int id) {
        FloorResponse response=new FloorResponse();
        try {
            floorRepo.findById(id).orElseThrow(() -> new MyException("site not found"));
            floorRepo.deleteById(id);
            response.setResponseCode(200);
            response.setResponseMessage("successful");
        }catch (MyException e) {
            response.setResponseCode(404);
            response.setResponseMessage("error site not found");
        }
        catch (Exception e) {

            response.setResponseCode(500);
            response.setResponseMessage("error site not found" + e.getMessage());

        }
        return response;
    }





}
