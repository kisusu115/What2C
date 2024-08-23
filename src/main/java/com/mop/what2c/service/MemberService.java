package com.mop.what2c.service;

import com.mop.what2c.domain.MemberDTO;
import com.mop.what2c.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Member join(MemberDTO memberDTO);

    Optional<Member> findMemberByMno(Long m_no);

    Optional<Member> findMemberById(String id);

    List<Member> findAllMembers();

    Optional<Member> changeMemberByMno(Long m_no, MemberDTO memberDTO);

    void deleteMemberByMno(Long m_no);

    Long tryLogin(String id, String pw);
}
