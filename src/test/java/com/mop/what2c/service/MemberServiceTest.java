package com.mop.what2c.service;

import com.mop.what2c.AutoWebConfig;
import com.mop.what2c.domain.Member;
import com.mop.what2c.domain.SignUpDto;
import com.mop.what2c.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void joinProcess() {
        //given
        SignUpDto signUpDto = new SignUpDto("testname", "password123!", " testmail@example.com");

        //when
        memberService.joinProcess(signUpDto);
        Member findMember = memberRepository.findByUsername(signUpDto.getUsername());

        //then.
        Assertions.assertThat(findMember.getUsername()).isEqualTo(signUpDto.getUsername());
        Assertions.assertThat(findMember.getEmail()).isEqualTo(signUpDto.getEmail());
    }
}
