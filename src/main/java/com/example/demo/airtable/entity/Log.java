package com.example.demo.airtable.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Log extends Fields{
    private String user;
    private String text;
    private String time;
    private String returnSucceed;
}
