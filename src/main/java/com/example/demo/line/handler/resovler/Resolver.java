package com.example.demo.line.handler.resovler;

import com.example.demo.line.entity.Event;

import java.util.Optional;

public interface Resolver {
    void reply(Optional<Event> event);
}
