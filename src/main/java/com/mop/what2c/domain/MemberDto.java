package com.mop.what2c.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
// 클라이언트에게 전달 가능한 정보
public class MemberDto {

    private Long id;
    private String username;
    private String email;

    static public MemberDto toDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .email(member.getEmail())
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .username(username)
                .email(email)
                .build();
    }
}
