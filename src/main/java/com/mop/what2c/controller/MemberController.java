package com.mop.what2c.controller;

import com.mop.what2c.domain.MemberDto;
import com.mop.what2c.domain.SignInDto;
import com.mop.what2c.domain.SignUpDto;
import com.mop.what2c.jwt.JwtToken;
import com.mop.what2c.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/member/sign-up")
    public ResponseEntity<MemberDto> signUp(@RequestBody SignUpDto signUpDto) {
        MemberDto savedMemberDto = memberService.signUp(signUpDto);
        return ResponseEntity.ok(savedMemberDto);
    }

    @PostMapping("/member/sign-in")
    public ResponseEntity<JwtToken> signIn(@RequestBody SignInDto signInDto) {
        try {
            String username = signInDto.getUsername();
            String password = signInDto.getPassword();
            JwtToken jwtToken = memberService.signIn(username, password);
            // 인증 성공 시 JWT 토큰을 포함하여 응답
            return ResponseEntity.ok(jwtToken);
        } catch (IllegalArgumentException | AuthenticationException e) {
            // 예외 발생 시 적절한 응답을 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    /*@PostMapping("/member/login")
    public String Login(@RequestBody Map<String, String> payload, HttpServletResponse response) throws IOException {
        String id = payload.get("id");
        String password = payload.get("password");
        Long foundMno = memberService.tryLogin(id, password);
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
    }*/
}
