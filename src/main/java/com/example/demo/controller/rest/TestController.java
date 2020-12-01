package com.example.demo.controller.rest;

import com.example.demo.annotation.SendSlackMessage;
import com.example.demo.mybatis.entity.Member;
import com.example.demo.mybatis.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/test")
@Controller
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(LineController.class);
    {
        LOG.warn("init :" + this.getClass().getSimpleName());
    }
    private MemberService memberService;

    @Autowired
    public TestController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    @SendSlackMessage(message = "text")
    public ResponseEntity<String> testResponse(@PathVariable("id") String id){

        return ResponseEntity.ok(id);
    }

    @ResponseBody
    @GetMapping()
    public ResponseEntity<String> test(){

        return ResponseEntity.ok("member");
    }
}
