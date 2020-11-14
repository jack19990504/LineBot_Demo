package com.example.demo.controller.rest;

import com.example.demo.weather.Service.WeatherService;
import com.example.demo.weather.entity.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;
    @Autowired
    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @GetMapping()
    public ResponseEntity<String> getWeather(){

        return ResponseEntity.ok().body(weatherService.getMessage());
    }
}
