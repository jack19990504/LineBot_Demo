package com.example.demo.line.service;

import com.example.demo.line.util.SendMessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LineProfileService {


    {
        log.info("init :\t" + this.getClass().getSimpleName());
    }

    private final SendMessageUtil sendMessageUtil;

    public LineProfileService(SendMessageUtil sendMessageUtil){
        this.sendMessageUtil = sendMessageUtil;
    }

    public String getUserName(String userId){

        var lineUserProfile = sendMessageUtil.getUserProfile(userId);

        return lineUserProfile.getDisplayName();
    }

}
