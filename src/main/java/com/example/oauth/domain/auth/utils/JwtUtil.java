package com.example.oauth.domain.auth.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
  public String createJwt(String id, String email, String secretKey, Long exprTime) {
    Claims claims = Jwts.claims();

    claims.put("id", id);
    claims.put("email", email);

    return Jwts.builder()
      .setClaims(claims)
      .signWith(SignatureAlgorithm.HS256, secretKey)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + exprTime))
      .compact();
  }
}
