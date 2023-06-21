package com.company.WeGoDent.records;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class GeoLocationInformation {
    private double longitude;
    private double latitude;

    @Override
    public String toString() {
        return "GeoLocationInformation{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}




