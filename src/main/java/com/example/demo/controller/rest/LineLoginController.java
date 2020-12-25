package com.example.demo.controller.rest;

import com.example.demo.line.login.entity.AccessToken;
import com.example.demo.line.login.entity.LineUser;
import com.example.demo.line.login.entity.LineUserDetail;
import com.example.demo.line.login.service.LineLoginService;
import com.example.demo.mybatis.entity.User;
import com.example.demo.mybatis.mapper.UserMapper;
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
    private final UserMapper userMapper;

    public LineLoginController(LineLoginService lineLoginService, UserMapper userMapper) {
        this.lineLoginService = lineLoginService;
        this.userMapper = userMapper;
    }


    /*
     * step 1
     * https://access.line.me/oauth2/v2.1/authorize?response_type=code&client_id=1654391521&redirect_uri=https%3A%2F%2F0187e4ef3e93.ngrok.io%2Fline%2Flogin&state=12345abcde&scope=openid%20profile
     * redirect to this api
     */
    // get code
    @GetMapping("/login")
    public void getUserInfo(@RequestParam(defaultValue = "code") String code , @RequestParam(defaultValue = "state") String state)
    {
        System.out.println("code :" + code);
        System.out.println("state :" + state);

        if(!code.equals("code") && !state.equals("state"))
        {
            AccessToken accessToken = lineLoginService.getUserAccessToken(code);
            LineUserDetail lineUserDetail = lineLoginService.getUserDetail(accessToken.getId_token());

            System.out.println("Line Login Success!");

            System.out.println("User Name : " + lineUserDetail.getName());
            System.out.println("UserId : " + lineUserDetail.getSub());
            System.out.println("User Picture : " + lineUserDetail.getPicture());

            LineUser lineUser = lineLoginService.getUser(accessToken.getAccess_token());

            System.out.println("User Name : " + lineUser.getDisplayName());
            System.out.println("UserId : " + lineUser.getUserId());
        }

    }

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

    @GetMapping("/user")
    public void printUser() {
        User user = userMapper.findByState("123");
        System.out.println("id : " + user.getUserId() + "\tName : " + user.getUserName());
    }
}
