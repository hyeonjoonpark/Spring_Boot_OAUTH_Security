package com.example.oauth.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "user")
@Table
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String userName;
  @Setter
  private String email;
  private String role;

  @Builder
  public User(Long id, String userName, String email, String role) {
    this.id = id;
    this.userName = userName;
    this.email = email;
    this.role = role;
  }
}
