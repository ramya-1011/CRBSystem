package com.example.demo_room.Controller;

import com.example.demo_room.Exception.MyException;
import com.example.demo_room.Model.City;
import com.example.demo_room.Repository.SiteRepo;
import com.example.demo_room.Service.SiteService;
import com.example.demo_room.Utils.ErrorResponse;
import com.example.demo_room.dto.Response;
import com.example.demo_room.dto.SiteDTO;
import com.example.demo_room.dto.SiteResponse;
import lombok.AllArgsConstructor;
import com.example.demo_room.Model.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/site")
@CrossOrigin("*")
public class SiteController {
    @Autowired
    private SiteRepo siteRepo;
    @Autowired
    private SiteService siteService;


    @GetMapping("/byId/{id}")
    public ResponseEntity<?> getSiteById(@PathVariable int id) {
        SiteDTO site = siteService.getById(id);
        return new ResponseEntity<>(site, HttpStatus.OK);
    }

    @PostMapping("/add-site")
    public  ResponseEntity<SiteResponse> addNewSite(@RequestBody Site site) {
         SiteResponse savedSite = siteService.addNewSite(site);
        return ResponseEntity.status(savedSite.getStatusCode()).body(savedSite);
    }
    @GetMapping("/sitesList")
    public List<Site> getAllSites(){
        return siteRepo.findAll();
    }
    @GetMapping("/byName/{locationName}")
    public List<Site> getByLocation(@PathVariable String locationName){
//        Response response =siteService.getByLocation(locationName);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
        return siteRepo.getBySiteName(locationName);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SiteResponse> deleteSite(@PathVariable int id){
        SiteResponse response =siteService.deleteSite(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
@PutMapping("/update/{id}")
    public ResponseEntity<Site> updateSite(@PathVariable int id,@RequestBody Site site){
    Site updatedSite = siteService.updateSite(id, site);
    if (updatedSite == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(updatedSite);


}
}
