package com.example.demo.airtable.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Log extends Fields{
    private String user;
    private String text;
    private String time;
    private String returnSucceed;
}
