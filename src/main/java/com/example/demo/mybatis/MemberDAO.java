package com.example.demo.mybatis;

import com.example.demo.mybatis.entity.Member;
import com.example.demo.mybatis.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemberDAO {

    private MemberMapper memberMapper;

    @Autowired
    public MemberDAO(MemberMapper memberMapper){
        this.memberMapper = memberMapper;
    }

    public Optional<Member> find(String id){
        return Optional.ofNullable(memberMapper.getMemberByEmail(id));
    }
}
