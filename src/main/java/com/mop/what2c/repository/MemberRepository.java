package com.mop.what2c.repository;

import com.mop.what2c.domain.Member;

public interface MemberRepository {

    void save(Member member);

    Member findByMno(Long m_no);
}
