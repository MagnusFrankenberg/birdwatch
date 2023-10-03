package com.devops.birdwatch.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WelcomeControllerTest {

    WelcomeController welcomeController = new WelcomeController();
    @Test
    void welcome() {

        assertEquals("Welcome to BirdWatch!",welcomeController.welcome());
    }
}