package com.mop.what2c.controller;

import com.mop.what2c.domain.MemberDTO;
import com.mop.what2c.domain.Member;
import com.mop.what2c.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/member/create")
    public void createMember(@RequestBody MemberDTO memberDTO, HttpServletResponse response) {
        Member member = memberService.join(memberDTO);
        if (member != null) {
            response.setStatus(200);
        } else {
            response.setStatus(400);
        }
    }

    @GetMapping("/member/{m_no}")
    public String getOneMember(@PathVariable("m_no") Long m_no){

        Optional<Member> optionalMember = memberService.findMemberByMno(m_no);
        if(optionalMember.isPresent()){
            return optionalMember.get().toString();
        }else{
            return "Member Not Found";
        }
    }

    @PostMapping("/member/login")
    public String Login(@RequestBody Map<String, String> payload, HttpServletResponse response) throws IOException {
        String id = payload.get("id");
        String pw = payload.get("pw");
        Long foundMno = memberService.tryLogin(id, pw);
        if(foundMno == -1){
            response.sendError(401, "해당하는 아이디의 계정이 존재하지 않습니다.");
            return "해당하는 아이디의 계정이 존재하지 않습니다.";
        }else if(foundMno == 0){
            response.sendError(401, "아이디와 비밀번호가 일치하지 않습니다.");
            return "아이디와 비밀번호가 일치하지 않습니다.";
        }else{
            response.setStatus(200);
            return "환영합니다 "+id+"님.";
        }
    }
}
