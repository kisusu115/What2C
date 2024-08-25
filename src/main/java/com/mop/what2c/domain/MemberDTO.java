package com.mop.what2c.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter
@ToString
public class MemberDTO {
    private String id;
    private String pw;
    private String email;

    public MemberDTO(String id, String pw, String email){
        this.id = id;
        this.pw = pw;
        this.email = email;
    }

    public MemberDTO(Member member){
        this.id = member.getId();
        this.pw = member.getPw();
        this.email = member.getEmail();
    }
}
