package com.example.demo.line.handler;

import com.example.demo.line.entity.Event;
import com.example.demo.line.resolver.Resolver;
import com.example.demo.line.resolver.TextResolver;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Component
public class MessageHandler implements EventHandler{
    private final HashMap<String, Resolver> resolvers ;

    public MessageHandler(TextResolver textResolver){
        resolvers = new HashMap<>(){{
            put("text",textResolver);
        }};
    }

    @Override
    public void handle(Optional<Event> event) {
        resolvers.get(event.get().getMessage().getType()).reply(event);
    }
}
