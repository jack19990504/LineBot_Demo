package com.example.demo.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin("*")
@RequestMapping("/test")
@Controller
public class TestController {

    {
        log.warn("init :\t" + this.getClass().getSimpleName());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> testResponse(@PathVariable("id") String id){

        return ResponseEntity.ok(id);
    }

    @ResponseBody
    @GetMapping()
    public ResponseEntity<String> test(){

        return ResponseEntity.ok("member");
    }
}
