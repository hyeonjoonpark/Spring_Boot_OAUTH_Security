package com.example.oauth.domain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
  @GetMapping("/")
  public String mainPage() {
    return "index";
  }

  @GetMapping("/home")
  public String loginSuccess() {
    return "home";
  }
}
