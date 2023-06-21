package com.company.WeGoDent.services.helpers;


import com.company.WeGoDent.records.GeoLocationInformation;
import com.company.WeGoDent.records.Geometry;
import com.company.WeGoDent.records.OpenCageResponse;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service

public class GeocodingService {

    private static final String GEOCODE_URL = "https://api.opencagedata.com/geocode/v1/json?q={address}&key={apiKey}";
    private final GeometryFactory geometryFactory = new GeometryFactory();




    public Point getPointObject(String address) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("address", address);
        params.put("apiKey", "20ffe345c07549bc90d95f258a6a1b32");

        ResponseEntity<OpenCageResponse> response = restTemplate.getForEntity(GEOCODE_URL, OpenCageResponse.class, params);



        if (response.getBody() != null && !response.getBody().getResults().isEmpty()) {
            Geometry geometry = response.getBody().getResults().get(0).getGeometry();

            System.out.println(

                    geometryFactory.createPoint(new Coordinate(geometry.getLng(), geometry.getLat()))

            );

            return geometryFactory.createPoint(new Coordinate(geometry.getLng(), geometry.getLat()));
        } else {
            return null;
        }


    }


    public GeoLocationInformation getLatLongObject(String query) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("address", query);
        params.put("apiKey", "20ffe345c07549bc90d95f258a6a1b32");

        ResponseEntity<OpenCageResponse> response = restTemplate.getForEntity(GEOCODE_URL, OpenCageResponse.class, params);
        System.out.println(response.toString());
        if (response.getBody() != null && !response.getBody().getResults().isEmpty()) {
            Geometry geometry = response.getBody().getResults().get(0).getGeometry();
            GeoLocationInformation geoInfo = new GeoLocationInformation();
            geoInfo.setLatitude(geometry.getLat());
            geoInfo.setLongitude(geometry.getLng());
            return geoInfo;
        } else {
            return null;
        }
    }


}
