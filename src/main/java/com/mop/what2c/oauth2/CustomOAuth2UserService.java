package com.mop.what2c.oauth2;

import com.mop.what2c.domain.Member;
import com.mop.what2c.domain.MemberDto;
import com.mop.what2c.domain.SocialUserDto;
import com.mop.what2c.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

// memberRepository.save를 Optional로 반환했는데 memberRepository.save(optionalMember.get());가 반영이 되는지 확인 요함

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else {
            return null;
        }

        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        Optional<Member> optionalMember = memberRepository.findByUsername(username);

        if (optionalMember.isEmpty()) {
            Member member = Member.builder()
                    .username(username)
                    .email(oAuth2Response.getEmail())
                    .roles(Collections.singletonList("USER"))
                    .usertype(oAuth2Response.getProvider().toUpperCase())   //"NAVER" 또는 "GOOGLE"
                    .build();
            memberRepository.save(member);

            SocialUserDto socialUserDTO = new SocialUserDto();
            socialUserDTO.setUsername(username);
            socialUserDTO.setName(oAuth2Response.getName());
            socialUserDTO.setRoles(Collections.singletonList("USER"));

            return new CustomOAuth2User(socialUserDTO);
        }
        else {
            optionalMember.get().setEmail(oAuth2Response.getEmail());
            memberRepository.save(optionalMember.get());

            SocialUserDto socialUserDTO = new SocialUserDto();
            socialUserDTO.setUsername(optionalMember.get().getUsername());
            socialUserDTO.setName(oAuth2Response.getName());
            socialUserDTO.setRoles(Collections.singletonList("USER"));

            return new CustomOAuth2User(socialUserDTO);
        }
    }
}
