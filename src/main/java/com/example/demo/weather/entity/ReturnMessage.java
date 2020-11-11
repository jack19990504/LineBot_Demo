package com.example.demo.weather.entity;

public class ReturnMessage {

    private String city;

    private String raining;

    private String temperature;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRaining() {
        return raining;
    }

    public void setRaining(String raining) {
        this.raining = raining;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return getCity() + ":" +getRaining() +"\\n" +getTemperature();
    }
}
