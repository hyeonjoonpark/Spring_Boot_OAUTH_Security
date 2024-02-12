package com.example.oauth.domain.auth.presentation.dto.response;


public interface OAuth2Response {
  String getProvider();
  String getProviderId();
  String getEmail();
  String getName();
}
