package com.example.demo.controller.rest;

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
    public ResponseEntity<Member> testResponse(@PathVariable("id") String id){
        Member member = memberService.getMemberTest(id);

        return ResponseEntity.ok(member);
    }

    @ResponseBody
    @GetMapping()
    public ResponseEntity<String> test(){

        return ResponseEntity.ok("member");
    }
}
