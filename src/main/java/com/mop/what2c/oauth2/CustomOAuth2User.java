package com.mop.what2c.oauth2;

import com.mop.what2c.domain.SocialUserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private final SocialUserDto socialUserDto;

    public CustomOAuth2User(SocialUserDto socialUserDto) {
        this.socialUserDto = socialUserDto;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return socialUserDto.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getName() {
        return socialUserDto.getName();
    }

    public String getUsername() {
        return socialUserDto.getUsername();
    }

    public String getEmail() {
        return socialUserDto.getEmail();
    }

    public String getUsertype(){
        return socialUserDto.getUsertype();
    }
}
