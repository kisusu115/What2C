package com.mop.what2c.service;

import com.mop.what2c.domain.RequestMember;
import com.mop.what2c.domain.Member;

public interface MemberService {
    Member join(RequestMember requestMember);

    Member findMemberByMno(Long m_no);
}
