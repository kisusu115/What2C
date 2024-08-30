package com.mop.what2c.filter;

import com.mop.what2c.domain.Member;
import com.mop.what2c.domain.SocialUserDto;
import com.mop.what2c.jwt.JWTUtil;
import com.mop.what2c.oauth2.CustomOAuth2User;
import com.mop.what2c.userdetail.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //request에서 Authorization 헤더를 찾음
        String authorization= request.getHeader("Authorization");

        //Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {

            System.out.println("token null");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        System.out.println("authorization now");
        //Bearer 부분 제거 후 순수 토큰만 획득
        String token = authorization.split(" ")[1];
        //토큰 소멸 시간 검증
        if (jwtUtil.isExpired(token)) {

            System.out.println("token expired");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        //토큰에서 username과 role 획득
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);
        String usertype = jwtUtil.getUsertype(token);

        Authentication authToken = null;

        if(usertype.equals("BASIC")){
            //userEntity를 생성하여 값 set
            Member member = Member.builder()
                    .username(username)
                    //Context Holder에 정확한 비밀번호를 넣을 필요는 없다.
                    .password("tmpPassword")
                    .role(role)
                    .email("tmpEmail")
                    .usertype("BASIC")
                    .build();

            //UserDetails에 회원 정보 객체 담기
            CustomUserDetails customUserDetails = new CustomUserDetails(member);

            //스프링 시큐리티 인증 토큰 생성
            authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        }
        else if(usertype.equals("NAVER") || usertype.equals("GOOGLE")){
            SocialUserDto socialUserDto = new SocialUserDto();
            socialUserDto.setUsername(username);
            socialUserDto.setRole(role);
            socialUserDto.setUsertype(usertype);

            //UserDetails에 회원 정보 객체 담기
            CustomOAuth2User customOAuth2User = new CustomOAuth2User(socialUserDto);

            //스프링 시큐리티 인증 토큰 생성
            authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
        }
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
