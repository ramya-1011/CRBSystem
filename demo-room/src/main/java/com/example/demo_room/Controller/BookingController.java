package com.example.demo_room.Controller;

import com.example.demo_room.Model.BookedRoom;
import com.example.demo_room.Model.ConferenceRoom;
import com.example.demo_room.Repository.BookingRepo;
import com.example.demo_room.Service.BookingService;
import com.example.demo_room.Service.IBookingService;
import com.example.demo_room.dto.BookedRoomResponse;
import com.example.demo_room.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/booking")
@CrossOrigin("*")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private BookingRepo bookingRepo;
    @PostMapping("/book-room/{roomId}" )
    public ResponseEntity<?> saveBookings(@PathVariable int roomId,
                                                                    @RequestBody BookedRoom room) {
        try{
            BookedRoom createdBooking=bookingService.addBooking(roomId,room);
            return ResponseEntity.ok(createdBooking);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @GetMapping("/all")
    public ResponseEntity<Response> getAllBookings() {
        Response response = bookingService.getAllBookings();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/get-by-confirmation-code/{confirmationCode}")
    public ResponseEntity<Response> getBookingByConfirmationCode(@PathVariable String confirmationCode) {
        Response response = bookingService.findBookingByConfirmationCode(confirmationCode);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @DeleteMapping("/cancel/{bookingId}")
    public ResponseEntity<Response> cancelBooking(@PathVariable Long bookingId) {
        Response response = bookingService.cancelBooking(bookingId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/byEmployee/{employeeName}")
    public List<BookedRoom> getByEmployeeDetails(@PathVariable String employeeName){
        return bookingRepo.findByEmployeeDetails(employeeName);
    }

}
