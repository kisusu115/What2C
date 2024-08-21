package com.mop.what2c.repository;

import com.mop.what2c.domain.RequestMember;
import com.mop.what2c.domain.Member;

import java.util.Optional;

public interface MemberRepository {

    Member save(RequestMember requestMember);

    Member findByMno(Long m_no);
}
