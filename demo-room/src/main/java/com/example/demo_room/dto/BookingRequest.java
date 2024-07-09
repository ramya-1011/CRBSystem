package com.example.demo_room.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class BookingRequest {
    private long roomId;
    private String EmployeeName;
    private String EmployeeId;
    private String Employee_ph_no;
    private int attendees;
    private LocalDate bookingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String confirmationCode;
}
