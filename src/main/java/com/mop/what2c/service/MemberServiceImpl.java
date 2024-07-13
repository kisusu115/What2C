package com.mop.what2c.service;

import com.mop.what2c.domain.Member;
import com.mop.what2c.repository.MemberRepository;
import com.mop.what2c.repository.MemoryMemberRepository;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long m_no) {
        return memberRepository.findByMno(m_no);
    }
}
