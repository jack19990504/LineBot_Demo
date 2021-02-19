package com.example.demo.line.service;

import com.example.demo.line.util.LineMessageAPIUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LineProfileService {


    {
        log.info("init :\t" + this.getClass().getSimpleName());
    }

    private final LineMessageAPIUtil lineMessageAPIUtil;

    public LineProfileService(LineMessageAPIUtil lineMessageAPIUtil){
        this.lineMessageAPIUtil = lineMessageAPIUtil;
    }

    public String getUserName(String userId){

        var lineUserProfile = lineMessageAPIUtil.getUserProfile(userId);

        return lineUserProfile.getDisplayName();
    }

}
