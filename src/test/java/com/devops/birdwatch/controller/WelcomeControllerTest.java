package com.devops.birdwatch.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class WelcomeControllerTest {

  WelcomeController welcomeController = new WelcomeController();

  @Test
  void welcome() {
    assertEquals("Welcome to BirdWatch!", welcomeController.welcome());
  }
}