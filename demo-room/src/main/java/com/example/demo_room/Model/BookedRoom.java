package com.example.demo_room.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    @Pattern(regexp = "^[A-Za-z]*$", message = "The name must contain only letters.")
    @Size(max = 20, message = "The name must be at most 20 letters long.")
    private String EmployeeName;
    @Column(unique = true)
    @Size(min = 10, max = 10, message = "The employee ID must be exactly 10 characters long.")
    private String EmployeeId;
    @Size(min =10,max = 10,message = "Invalid phone number")
    private String Employee_ph_no;
    @Min(value=1,message = "Minimum 1 person is required to book a Room")
    private int attendees;
   @NotNull(message="please Enter the date for booking")
    @JsonFormat(pattern = "yyyy-MM-dd")
   @FutureOrPresent(message = "date cant be in the past")
    private LocalDate bookingDate;

    private LocalTime startTime;

    private LocalTime endTime;
    private String confirmationCode;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cRoom_id")
    private ConferenceRoom room;
    public BookedRoom(int roomId, BookedRoom bookingRequest) {
    }


}
