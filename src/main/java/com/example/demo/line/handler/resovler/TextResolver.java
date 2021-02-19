package com.example.demo.line.handler.resovler;

import com.example.demo.line.entity.Event;
import com.example.demo.line.service.LineMessageAPIService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TextResolver implements Resolver{

    private final LineMessageAPIService lineMessageAPIService;

    public TextResolver(LineMessageAPIService lineMessageAPIService){
        this.lineMessageAPIService = lineMessageAPIService;
    }


    @Override
    public void reply(Optional<Event> event) {
        lineMessageAPIService.reply(event);
    }
}
