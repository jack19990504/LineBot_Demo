package com.example.demo.weather.entity;

import java.util.List;

public class Location {

    private String locationName;

    private String geocode;

    private String lat;

    private String lon;

    private List<WeatherElement> weatherElement;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getGeocode() {
        return geocode;
    }

    public void setGeocode(String geocode) {
        this.geocode = geocode;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public List<WeatherElement> getWeatherElement() {
        return weatherElement;
    }

    public void setWeatherElement(List<WeatherElement> weatherElement) {
        this.weatherElement = weatherElement;
    }
}
