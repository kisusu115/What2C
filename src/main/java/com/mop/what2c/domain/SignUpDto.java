package com.mop.what2c.domain;

import lombok.*;

@Getter
@AllArgsConstructor
public class SignUpDto {
    private String username;
    private String password;
    private String email;
}
