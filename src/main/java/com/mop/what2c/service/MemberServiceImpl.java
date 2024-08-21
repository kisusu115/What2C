package com.mop.what2c.service;

import com.mop.what2c.domain.RequestMember;
import com.mop.what2c.domain.Member;
import com.mop.what2c.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    //@Autowired >> @RequiredArgsConstructor가 해줌
    private final MemberRepository memberRepository;

    @Override
    public Member join(RequestMember requestMember) {
        Member member = memberRepository.save(requestMember);
        return member;
    }

    @Override
    public Member findMemberByMno(Long m_no) {
        return memberRepository.findByMno(m_no);
    }
}