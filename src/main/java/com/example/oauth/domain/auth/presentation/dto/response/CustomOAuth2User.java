package com.example.oauth.domain.auth.presentation.dto.response;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {
  private final OAuth2Response oAuth2Response;
  private final String role;
  @Override
  public Map<String, Object> getAttributes() {
    return null;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> collection = new ArrayList<>();

    collection.add((GrantedAuthority) () -> role);

    return collection;
  }

  @Override
  public String getName() {

    return oAuth2Response.getName();
  }

  public String getUserName() {
    return oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
  }
}
