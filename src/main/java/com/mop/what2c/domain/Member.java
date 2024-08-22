package com.mop.what2c.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity(name = "members")
@RequiredArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long m_no;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String email;

    public Member(MemberDTO memberDTO){
        this.id = memberDTO.getId();
        this.pw = memberDTO.getPw();
        this.email = memberDTO.getEmail();
    }
}
