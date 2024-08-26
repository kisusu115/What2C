package com.mop.what2c.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 회원가입 시 전달받는 정보
public class SignUpDto {
    private String username;
    private String password;
    private String email;

    static public SignUpDto toDto(Member member) {
        return SignUpDto.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .email(member.getEmail())
                .build();
    }

    public Member toEntity(String encodedPassword, List<String> roles) {

        return Member.builder()
                .username(username)
                .password(encodedPassword)
                .email(email)
                .roles(roles)
                .build();
    }
}
