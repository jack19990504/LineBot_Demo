package com.example.demo.line.handler;

import com.example.demo.line.entity.Event;
import com.example.demo.line.handler.resovler.Resolver;
import com.example.demo.line.handler.resovler.TextResolver;
import com.example.demo.line.service.LineService;

import java.util.HashMap;
import java.util.Optional;


public class MessageHandler implements EventHandler{
    private final HashMap<String, Resolver> resolvers ;
    private final LineService lineService;

    public MessageHandler(LineService lineService){
        resolvers = new HashMap<>(){{
            put("text",new TextResolver());
        }};
        this.lineService = lineService;
    }

    @Override

    public void handle(Optional<Event> event) {
        resolvers.get(event.get().getMessage().getType()).reply(event,lineService);
    }
}
