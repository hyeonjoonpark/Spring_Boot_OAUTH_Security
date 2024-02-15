package com.example.oauth.domain.auth.service;

import com.example.oauth.domain.auth.presentation.dto.response.CustomOAuth2User;
import com.example.oauth.domain.auth.presentation.dto.response.OAuth2Response;
import com.example.oauth.domain.auth.presentation.dto.response.google.GoogleResponse;
import com.example.oauth.domain.auth.utils.JwtUtil;
import com.example.oauth.domain.user.User;
import com.example.oauth.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
  private final UserRepository userRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);
    System.out.println("oAuth2User = " + oAuth2User.getAttributes());

    String registrationId = userRequest.getClientRegistration().getRegistrationId();

    OAuth2Response oAuth2Response = null;

    if (registrationId.equals("google")) {
      oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
    }

    // DB 저장
    String userName = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
    User userData = userRepository.findByUserName(userName);

    String role = null;

    if (userData == null) {
      User user = User.builder()
        .userName(userName)
        .email(oAuth2Response.getEmail())
        .role("ROLE_USER")
        .build();

      userRepository.save(user);
    } else {
      role = userData.getRole();
      userData.setEmail(oAuth2Response.getEmail());
      userRepository.save(userData);
    }

    return new CustomOAuth2User(oAuth2Response, role);
  }
}
