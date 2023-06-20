package com.company.WeGoDent.services.helpers;


import com.company.WeGoDent.records.GeoLocationInformation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service

public class GeocodingService {

    private static final String GEOCODE_URL = "https://api.opencagedata.com/geocode/v1/json?q={address}&key={apiKey}";
    public Point getCoordinates(String address) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("address", address);
        params.put("apiKey", "your_api_key_here");
        String response = restTemplate.getForObject(GEOCODE_URL, String.class, params);

        ObjectMapper objectMapper = new ObjectMapper();


        // Assuming you have parsed the JSON and have the latitude and longitude
        org.locationtech.jts.geom.Point location = null;
        try {
            GeoLocationInformation locInfo = objectMapper.readValue(response, GeoLocationInformation.class);
            location = new GeometryFactory().createPoint(new Coordinate(locInfo.longitude(), locInfo.latitude()));
        } catch (Exception e){
            e.printStackTrace();
        }
        return location;
    }

    public GeoLocationInformation getLongAndLat(String address){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("address", address);
        params.put("apiKey", "your_api_key_here");
        String response = restTemplate.getForObject(GEOCODE_URL, String.class, params);

        ObjectMapper objectMapper = new ObjectMapper();


        GeoLocationInformation locInfo = null;
        try {
            locInfo = objectMapper.readValue(response, GeoLocationInformation.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return locInfo;
    }


}
