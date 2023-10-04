package com.devops.birdwatch;

import com.devops.birdwatch.model.Bird;
import com.devops.birdwatch.model.BirdWatcher;
import com.devops.birdwatch.model.Observation;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;

public class Observation_E2E_Test {

    @BeforeEach
    public void setUpClass() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    public void E2EObservationTest(){

        //Creating a bird
        Bird bird = new Bird();
        bird.setSpeices("FågelArt1");
        bird.setType("FågelTyp1");

         given()
                .contentType(ContentType.JSON)
                .body(bird)
                .when()
                .post("/bird/add")
                .then()
                .statusCode(201);


        //Creating a birdWatcher
        BirdWatcher birdWatcher = new BirdWatcher();
        birdWatcher.setFirstName("Förnamn1");
        birdWatcher.setLastName("Efternamn1");
        birdWatcher.setEmail("epost@adress1.se");

        given()
                .contentType(ContentType.JSON)
                .body(birdWatcher)
                .when()
                .post("/birdWatcher/add")
                .then()
                .statusCode(201);


        //Create an Observation
        Observation observation = new Observation();
        observation.setObservationDate(LocalDate.parse("2022-11-07"));
        observation.setLocation("Location1");
        observation.setCountry("Country1");





    }


}
