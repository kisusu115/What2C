package com.mop.what2c.service;

import com.mop.what2c.domain.Member;
import com.mop.what2c.domain.MemberDto;
import com.mop.what2c.domain.SignUpDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Test
    void join(){
        //given
        SignUpDto requestMember = new SignUpDto("kisusu115", "abcd1234!!", "kisusu115@example.com");

        //when
        Long m_no = memberService.join(requestMember, requestMember.getPassword(), Collections.singletonList("USER")).getId();
        Member foundMember = memberService.findMemberById(m_no).get();
        SignUpDto foundMemberToSignUpDto = SignUpDto.toDto(foundMember);

        //then
        Assertions.assertThat(requestMember).usingRecursiveComparison().isEqualTo(foundMemberToSignUpDto);
    }
}
