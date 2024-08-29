package com.mop.what2c.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class SocialUserDto {

    private String name;

    private String username;

    private List<String> roles = new ArrayList<>();
}
