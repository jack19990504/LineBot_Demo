package com.example.demo.slack.entity;

public enum ActionType {
    OK("ok"),ERROR("error");

    private String type;
    ActionType(String type) {
        this.type = type;
    }
}
