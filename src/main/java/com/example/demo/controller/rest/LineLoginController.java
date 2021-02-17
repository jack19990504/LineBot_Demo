package com.example.demo.controller.rest;

import com.example.demo.line.login.entity.AccessToken;
import com.example.demo.line.login.entity.LineUser;
import com.example.demo.line.login.entity.LineUserDetail;
import com.example.demo.line.login.service.LineLoginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin("*")
@RequestMapping("/line/login")
@Controller
public class LineLoginController {

    @Value("${spring.application.name}")
    String appName;

    private final LineLoginService lineLoginService;

    public LineLoginController(LineLoginService lineLoginService) {
        this.lineLoginService = lineLoginService;
    }


    /*
     * step 1
     * https://access.line.me/oauth2/v2.1/authorize?response_type=code&client_id=1654391521&redirect_uri=https%3A%2F%2F0187e4ef3e93.ngrok.io%2Fline%2Flogin&state=12345abcde&scope=openid%20profile
     * redirect to this api
     */
    // get code
    @GetMapping("/login/success")
    public String getUserInfo2(@RequestParam(defaultValue = "code") String code, @RequestParam(defaultValue = "state") String state, Model model) {
        System.out.println("/login/success");
        System.out.println("code :" + code);
        System.out.println("state :" + state);
        if (!code.equals("code") && !state.equals("state")) {

            AccessToken accessToken = lineLoginService.getUserAccessToken(code);
            LineUserDetail lineUserDetail = lineLoginService.getUserDetail(accessToken.getId_token());

            System.out.println("Line Login Success!");

            System.out.println("User Name : " + lineUserDetail.getName());
            System.out.println("UserId : " + lineUserDetail.getSub());
            System.out.println("User Picture : " + lineUserDetail.getPicture());

            LineUser lineUser = lineLoginService.getUser(accessToken.getAccess_token());

            System.out.println("User Name : " + lineUser.getDisplayName());
            System.out.println("UserId : " + lineUser.getUserId());
            model.addAttribute("appName", appName);
            model.addAttribute("userName", lineUser.getDisplayName());
            model.addAttribute("image", lineUser.getPictureUrl());

        }
        return "home";

    }

}
