package com.mop.what2c.oauth2;

import com.mop.what2c.domain.Member;
import com.mop.what2c.domain.SocialUserDto;
import com.mop.what2c.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

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

        //리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디값을 만듬
        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        Member existMember = memberRepository.findByUsername(username);

        if (existMember == null) {

            Member member = Member.builder()
                    .username(username)
                    .password("SOCIAL_USER_NO_PASSWORD")
                    .email(oAuth2Response.getEmail())
                    .role("ROLE_USER")
                    .usertype(oAuth2Response.getProvider().toUpperCase())
                    .build();

            memberRepository.save(member);

            SocialUserDto socialUserDto = new SocialUserDto();
            socialUserDto.setUsername(username);
            socialUserDto.setName(oAuth2Response.getName());
            socialUserDto.setRole("ROLE_USER");
            socialUserDto.setEmail(oAuth2Response.getEmail());
            socialUserDto.setUsertype(oAuth2Response.getProvider().toUpperCase());

            return new CustomOAuth2User(socialUserDto);
        }
        else {
            existMember.setEmail(oAuth2Response.getEmail());

            memberRepository.save(existMember);

            SocialUserDto socialUserDto = new SocialUserDto();
            socialUserDto.setUsername(existMember.getUsername());
            socialUserDto.setName(oAuth2Response.getName());
            socialUserDto.setRole(existMember.getRole());
            socialUserDto.setEmail(oAuth2Response.getEmail());
            socialUserDto.setUsertype(oAuth2Response.getProvider().toUpperCase());

            return new CustomOAuth2User(socialUserDto);
        }
    }
}