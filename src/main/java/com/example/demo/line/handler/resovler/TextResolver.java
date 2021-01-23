package com.example.demo.line.handler.resovler;

import com.example.demo.line.entity.Event;
import com.example.demo.line.service.LineService;

import java.util.Optional;

public class TextResolver implements Resolver{



    @Override
    public void reply(Optional<Event> event,LineService lineService) {
        lineService.reply(event);
    }
}
