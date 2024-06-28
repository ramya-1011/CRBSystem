package com.example.demo_room.Service.Interface;

import com.example.demo_room.Model.City;
import com.example.demo_room.Model.Site;
import com.example.demo_room.dto.CityResponse;
import com.example.demo_room.dto.Response;
import com.example.demo_room.dto.SiteDTO;
import com.example.demo_room.dto.SiteResponse;

import java.util.List;

public interface ISiteService {
    SiteResponse addNewSite(Site site);
    SiteResponse deleteSite(int id);
    Site updateSite(int id,Site site);

    SiteDTO getById(int id);
}
