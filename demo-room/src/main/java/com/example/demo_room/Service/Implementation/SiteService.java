package com.example.demo_room.Service.Implementation;

import com.example.demo_room.Exception.MyException;
import com.example.demo_room.Model.Site;
import com.example.demo_room.Repository.SiteRepo;
import com.example.demo_room.Service.Interface.ISiteService;
import com.example.demo_room.Utils.Utils;
import com.example.demo_room.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SiteService implements ISiteService {
    @Autowired
    private final SiteRepo siteRepo;
    @Autowired
    private Response responseDTO;
    @Override
    public SiteResponse addNewSite(Site site) {
        SiteResponse response =new SiteResponse();
        SiteDTO siteDTO=new SiteDTO();
        try{
            Site site1=new Site();
            site1.setSiteId(site.getSiteId());
            site1.setId(site.getId());
            site1.setDescription(site.getDescription());
            site1.setPinCode(site.getPinCode());
            site1.setTotalFloors(site.getTotalFloors());
            site1.setLocationName(site.getLocationName());
            site1.setFloors(site.getFloors());
            Site savedSite = siteRepo.save(site1);
            response =Utils.mapSiteEntityToSiteResponse(savedSite);
            response.setStatusCode(200);
            response.setResponseMessage("success");

            siteDTO.setSiteResponse(response);

            responseDTO.setMessage("successful");

        }catch (Exception e) {
            response.setStatusCode(500);
            responseDTO.setMessage("Error saving a city " + e.getMessage());
        }
        return response;
    }
    @Override
    public SiteResponse deleteSite(int id) {
        SiteResponse response=new SiteResponse();
        try {
            siteRepo.findById(id).orElseThrow(() -> new MyException("site not found"));
            siteRepo.deleteById(id);
            response.setStatusCode(200);
            response.setResponseMessage("successful");
        }catch (MyException e) {
            response.setStatusCode(404);
            response.setResponseMessage("error site not found");
        }
        catch (Exception e) {

            response.setStatusCode(500);
            response.setResponseMessage("error site not found");
            responseDTO.setMessage("Error saving a city " + e.getMessage());
        }
        return response;
    }
    @Override
        public Site updateSite(int id, Site siteRequest) {
            Site site = siteRepo.findById(id).orElse(null);
            if (site != null) {
                if (siteRequest.getId() != 0) site.setId(  siteRequest.getId());
                if (siteRequest.getSiteId() != null) site.setSiteId( siteRequest.getSiteId());
                if (siteRequest.getLocationName() != null) site.setLocationName(  siteRequest.getLocationName());
                if (siteRequest.getFloors() != null) site.setFloors(  siteRequest.getFloors());
                if (siteRequest.getTotalFloors() != 0) site.setTotalFloors(  siteRequest.getTotalFloors());
                if (siteRequest.getPinCode() != null) site.setPinCode(  siteRequest.getPinCode());
                if (siteRequest.getId() != 0) site.setId(  siteRequest.getId());

                return siteRepo.save(site);
            }
            return null;
        }


    @Override
    public SiteDTO getById(int id) {
            SiteResponse siteResponse=new SiteResponse();
            Response response=new Response();
            SiteDTO siteDTO=new SiteDTO();
            try {
                 Site site = siteRepo.findById(id).orElseThrow(() -> new MyException("Site Not Found"));
                SiteResponse  siteDto = Utils.mapSiteEntityToSiteResponse(site);
                response.setStatusCode(200);
                responseDTO.setMessage("successful");
                response.setSiteResponse(siteDto);
                siteDTO.setSiteResponse(siteDto);
            }catch (MyException e) {
                response.setStatusCode(404);
                responseDTO.setMessage(e.getMessage());
            } catch (Exception e) {
                response.setStatusCode(500);
                responseDTO.setMessage("Error saving a room " + e.getMessage());
            }
            return  siteDTO;

        }


}
