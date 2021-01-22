package com.example.demo.annotation;

import com.example.demo.slack.entity.ActionType;
import com.example.demo.slack.entity.MessageType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SendSlack {

    String message() default "";
    ActionType actionType() default ActionType.OK;
    MessageType messageType() default MessageType.TEXT;

}
