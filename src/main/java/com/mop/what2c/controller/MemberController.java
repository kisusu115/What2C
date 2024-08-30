package com.mop.what2c.controller;

import com.mop.what2c.domain.SignUpDto;
import com.mop.what2c.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;

@RestController
@ResponseBody
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/sign-up")
    public String signUpProcess(@RequestBody SignUpDto signUpDto) {

        System.out.println(signUpDto.getUsername());
        memberService.joinProcess(signUpDto);

        return "ok";
    }

    @GetMapping("/user")
    public String userP() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();
        return "< User Controller >\nName : "+name+"\nRole : "+role;
    }
}
