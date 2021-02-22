package com.example.demo.line.resolver;

import com.example.demo.line.entity.Event;

import java.util.Optional;

public interface Resolver {
    void solve(Optional<Event> event);
}
