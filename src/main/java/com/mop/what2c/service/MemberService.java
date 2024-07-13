package com.mop.what2c.service;

import com.mop.what2c.domain.Member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long m_no);
}
