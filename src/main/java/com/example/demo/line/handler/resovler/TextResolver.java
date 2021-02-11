package com.example.demo.line.handler.resovler;

import com.example.demo.line.entity.Event;
import com.example.demo.line.service.LineService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TextResolver implements Resolver{

    private final LineService lineService;

    public TextResolver(LineService lineService){
        this.lineService = lineService;
    }


    @Override
    public void reply(Optional<Event> event) {
        lineService.reply(event);
    }
}
