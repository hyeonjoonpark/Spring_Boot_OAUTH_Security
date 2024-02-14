package com.example.oauth.global.config.security;

import com.example.oauth.domain.auth.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  private final CustomOAuth2UserService customOAuth2UserService;
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable)
      .cors(Customizer.withDefaults())
      .formLogin(AbstractHttpConfigurer::disable)
      .httpBasic(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(
        auth -> auth
          .requestMatchers("/", "/oauth2/**", "/api/login/**", "/login/**").permitAll()
          .anyRequest().authenticated()
      )
      .oauth2Login((oauth2) -> oauth2
        .userInfoEndpoint((userInfoEndpointConfig) ->
          userInfoEndpointConfig.userService(customOAuth2UserService))
        .defaultSuccessUrl("/home")
      );

    return http.build();
  }
}
