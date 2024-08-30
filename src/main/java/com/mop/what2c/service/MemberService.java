package com.mop.what2c.service;

import com.mop.what2c.domain.Member;
import com.mop.what2c.domain.SignUpDto;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    public void joinProcess(SignUpDto signUpDto);
}
