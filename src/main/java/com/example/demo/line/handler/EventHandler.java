package com.example.demo.line.handler;

import com.example.demo.line.entity.Event;

import java.util.Optional;

public interface EventHandler {

    void handle(Optional<Event> event);

}
