package com.example.demo.line.service;

import com.example.demo.line.util.SendMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LineProfileService {

    private static final Logger LOG = LoggerFactory.getLogger(LineService.class);
    {
        LOG.info("init :\t" + this.getClass().getSimpleName());
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
