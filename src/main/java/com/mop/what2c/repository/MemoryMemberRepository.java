package com.mop.what2c.repository;

import com.mop.what2c.domain.Member;

import java.util.HashMap;
import java.util.Map;

//DB 사용 전 인터페이스 구현 용 메모리 기반 리포지토리 구현
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    @Override
    public void save(Member member) {
        store.put(member.getM_no(), member);
    }

    @Override
    public Member findByMno(Long m_no) {
        return store.get(m_no);
    }
}
