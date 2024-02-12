package com.example.oauth.domain.auth.service;

import com.example.oauth.domain.auth.presentation.dto.response.OAuth2Response;
import com.example.oauth.domain.auth.presentation.dto.response.google.GoogleResponse;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService {
  @Override
  public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(request);
    System.out.println("oAuth2User = " + oAuth2User.getAttributes());

    String registrationId = request.getClientRegistration().getRegistrationId();

    OAuth2Response oAuth2Response = null;

    if(registrationId.equals("google")) {
      oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
    }

    return null;
  }
}
