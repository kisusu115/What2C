package com.mop.what2c.service;

import com.mop.what2c.domain.Member;
import com.mop.what2c.domain.MemberDto;
import com.mop.what2c.domain.SignUpDto;
import com.mop.what2c.jwt.JwtToken;
import com.mop.what2c.jwt.JwtTokenProvider;
import com.mop.what2c.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    //@Autowired >> @RequiredArgsConstructor가 해줌
    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public Optional<Member> findMemberById(Long id) {
        return memberRepository.findById(id);
    }

    @Transactional
    @Override
    public MemberDto join(SignUpDto signUpDto, String encodedPassword, List<String> roles){
        Member member = memberRepository.save(signUpDto.toEntity(encodedPassword, roles));
        return MemberDto.toDto(member);
    }

    @Transactional
    @Override
    public MemberDto signUp(SignUpDto signUpDto) {
        if (memberRepository.existsByUsername(signUpDto.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 사용자 이름입니다.");
        }
        // Password 암호화
        String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());
        List<String> roles = new ArrayList<>();
        roles.add("USER");  // USER 권한 부여
        return MemberDto.toDto(memberRepository.save(signUpDto.toEntity(encodedPassword, roles)));
    }

    @Transactional
    @Override
    public JwtToken signIn(String username, String password) {
        Optional<Member> memberOptional = memberRepository.findByUsername(username);
        if (memberOptional.isEmpty()) throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        Member member = memberOptional.get();
        if (!"BASIC".equals(member.getUsertype())) throw new IllegalArgumentException("소셜 계정으로 로그인할 수 없습니다.");
        // 1. username + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        try {
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);
            return jwtToken;
        } catch (AuthenticationException e) {
            System.out.println("sdasadasdasdasd");
            throw new IllegalArgumentException("로그인 인증에 실패했습니다.");
        }
    }
}