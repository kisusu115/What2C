package com.mop.what2c.service;

import com.mop.what2c.domain.MemberDto;
import com.mop.what2c.domain.Member;
import com.mop.what2c.domain.SignUpDto;
import com.mop.what2c.jwt.JwtToken;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    public Optional<Member> findMemberById(Long id);

    public MemberDto join(SignUpDto signUpDto, String encodedPassword, List<String> roles);

    public MemberDto signUp(SignUpDto signUpDto);

    public JwtToken signIn(String username, String password);
}
