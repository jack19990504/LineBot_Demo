package com.example.demo.line.handler.resovler;

import com.example.demo.line.entity.Event;
import com.example.demo.line.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TextResolver implements Resolver{



    @Override
    public void reply(Optional<Event> event,LineService lineService) {
        lineService.message_text_Simple_Reply(event);
    }
}
