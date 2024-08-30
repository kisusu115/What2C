package com.mop.what2c.service;

import com.mop.what2c.domain.Member;
import com.mop.what2c.domain.SignUpDto;
import com.mop.what2c.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(SignUpDto signUpDto) {

        String username = signUpDto.getUsername();
        String password = signUpDto.getPassword();
        String email = signUpDto.getEmail();
        System.out.println(username + password + email);

        Boolean isExist = memberRepository.existsByUsername(username);

        if (isExist) {
            return;
        }

        Member member = Member.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .usertype("BASIC")
                .email(email)
                .role("ROLE_USER")
                .build();

        memberRepository.save(member);
    }
}