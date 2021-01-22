package com.example.demo.slack.entity;

public enum MessageType {
    TEXT("text"),IMAGE("image"),VIDEO("video"),AUDIO("audio"),FILE("file"),LOCATION("location"),STICKER("sticker");

    private String type;

    MessageType(String type){
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
