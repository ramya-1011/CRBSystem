package com.example.demo_room.Repository;

import com.example.demo_room.Model.ConferenceRoom;
import jakarta.persistence.criteria.CriteriaBuilder;
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
@Query(value = "SELECT r FROM ConferenceRoom r WHERE r.city.id=:id")
     List<ConferenceRoom> findByRoomCityId(@Param("id") Integer id);
    @Query(value = "SELECT r FROM ConferenceRoom r WHERE r.site.id=:id")
    List<ConferenceRoom> findByRoomSiteId(@Param("id") Integer id);


    @Query(value = "SELECT r FROM ConferenceRoom r WHERE r.floor.id=:id")
    List<ConferenceRoom> findByFloorId(@Param("id") Integer id);

    List<ConferenceRoom> findByCityIdAndSiteIdAndFloorId(Integer cityId, Integer siteId, Integer floorId);

    List<ConferenceRoom> findByCityIdAndSiteId(Integer cityId,Integer siteId);

    @Query(value = "SELECT r FROM ConferenceRoom r WHERE r.city.id=:id")
    List<ConferenceRoom> findRoomByCity(@Param("id") long id);
    @Query(value ="SELECT cr from ConferenceRoom cr WHERE cr.id NOT in ( SELECT b.room.id FROM BookedRoom b)")
    List<ConferenceRoom> findAllAvailableRooms(@Param("id") int id);



}
