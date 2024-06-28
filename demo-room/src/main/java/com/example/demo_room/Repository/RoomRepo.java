package com.example.demo_room.Repository;

import com.example.demo_room.Model.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RoomRepo extends JpaRepository<ConferenceRoom,Long> {
    @Query(value = "SELECT DISTINCT cr.city FROM ConferenceRoom cr")
    List<String> findByCity();
    @Query (value="SELECT DISTINCT cr.type FROM ConferenceRoom cr")
    List<String> findByType();
@Query(value = "SELECT r FROM ConferenceRoom r WHERE r.site=:site")
     List<ConferenceRoom> findBySite(@Param("site") String site);
    @Query(value = "SELECT r FROM ConferenceRoom r WHERE r.city=:city")
    List<ConferenceRoom> findRoomByCity(@Param("city") String city);
    @Query(value ="SELECT cr from ConferenceRoom cr WHERE cr.id NOT in ( SELECT b.roomId FROM BookedRoom b)")
    List<ConferenceRoom> findAllAvailableRooms(@Param("id") int id);



}
