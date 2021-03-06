package com.example.demo.line.service;

import com.example.demo.feign.MessageAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LineProfileService {


    {
        log.info("init :\t" + this.getClass().getSimpleName());
    }

    private final MessageAPI messageAPI;

    public LineProfileService(MessageAPI messageAPI){
        this.messageAPI = messageAPI;
    }

    public String getUserName(String userId){

        var lineUserProfile = messageAPI.lineUserProfile(userId);

        return lineUserProfile.getDisplayName();
    }

}
