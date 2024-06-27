package com.example.demo_room.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
@Component
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name="bookings")

public class BookedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingID;
    private long roomId;

    private String EmployeeName;
    private long EmployeeId;
    private long Employee_ph_no;
    @Min(value=1,message = "Minimum 1 person is required to book a Room")
    private int attendees;
   @NotNull(message="please Enter the date for booking")
    @JsonFormat(pattern = "yyyy-MM-dd")
   @FutureOrPresent(message = "date cant be in the past")
    private LocalDate bookingDate;
    @Future(message = "you can only book for the next hours")
   @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;
    private String confirmationCode;
   // @Min(value=1,message = "Minimum 1 person is required to book a Room")
    private int numOfEmployees;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ConferenceRoom_id")
    private ConferenceRoom room;



    public BookedRoom(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public BookedRoom(int roomId, BookedRoom bookingRequest) {
    }
}
