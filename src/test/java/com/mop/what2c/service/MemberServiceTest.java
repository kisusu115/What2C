package com.mop.what2c.service;

import com.mop.what2c.domain.Member;
import com.mop.what2c.domain.MemberDTO;
import com.mop.what2c.repository.DBMemberRepository;
import com.mop.what2c.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Test
    void join(){
        //given
        MemberDTO requestMember = new MemberDTO("kisusu115", "abcd1234!!", "kisusu115@example.com");

        //when
        Long m_no = memberService.join(requestMember).getM_no();
        Member foundMember = memberService.findMemberByMno(m_no).get();
        MemberDTO foundMemberDto = new MemberDTO(foundMember);

        //then
        Assertions.assertThat(requestMember).usingRecursiveComparison().isEqualTo(foundMemberDto);
    }
}
