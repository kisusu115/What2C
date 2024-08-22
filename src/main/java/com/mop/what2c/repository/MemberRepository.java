package com.mop.what2c.repository;

import com.mop.what2c.domain.MemberDTO;
import com.mop.what2c.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(MemberDTO memberDTO);

    Optional<Member> findByMno(Long m_no);

    Optional<Member> findById(String id);

    List<Member> findAll();

    Optional<Member> updateByMno(Long m_no, MemberDTO memberDTO);

    void deleteByMno(Long m_no);
}
