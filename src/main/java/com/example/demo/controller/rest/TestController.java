package com.example.demo.controller.rest;

import com.example.demo.airtable.service.AirTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@CrossOrigin("*")
@RequestMapping("/test")
@Controller
public class TestController {

    {
        log.info("init :\t" + this.getClass().getSimpleName());
    }
    private final AirTableService airTableService;

    public TestController(AirTableService airTableService){
        this.airTableService = airTableService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Object> testResponse(@PathVariable("id") String id){

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        var isSucceed = airTableService.createLog("test","on Build",now.format(formatter),"Yes");
        return ResponseEntity.ok(isSucceed);
    }

    @ResponseBody
    @GetMapping()
    public ResponseEntity<String> test(){

        return ResponseEntity.ok("member");
    }
}
