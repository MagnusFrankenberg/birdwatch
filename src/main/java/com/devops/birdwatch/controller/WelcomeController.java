package com.devops.birdwatch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

  @GetMapping("welcome")
  public String welcome() {
    return "Welcome to BirdWatch!";
    //Hej, Hej från master
  }
}
