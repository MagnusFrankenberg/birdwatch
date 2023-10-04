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
import static org.hamcrest.Matchers.equalTo;

public class Observation_E2E_Test {

    @BeforeEach
    public void setUpClass() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    public void E2E_addObservationAndExpect201(){

        //Creating a bird
        Bird bird = new Bird();
        bird.setSpeices("FågelArt1");
        bird.setType("FågelTyp1");

         Bird savedBird = given()
                .contentType(ContentType.JSON)
                .body(bird)
                .when()
                .post("/bird/add")
                .then()
                .statusCode(201)
                 .extract().as(Bird.class);


        //Creating a birdWatcher
        BirdWatcher birdWatcher = new BirdWatcher();
        birdWatcher.setFirstName("Förnamn1");
        birdWatcher.setLastName("Efternamn1");
        birdWatcher.setEmail("epost@adress1.se");

        BirdWatcher savedBirdWatcher = given()
                .contentType(ContentType.JSON)
                .body(birdWatcher)
                .when()
                .post("/birdWatcher/add")
                .then()
                .statusCode(201)
                .extract().as(BirdWatcher.class);


        //Creating an Observation
        Observation observation = new Observation();
        observation.setObservationDate(LocalDate.parse("2022-11-07"));
        observation.setLocation("Location1");
        observation.setCountry("Country1");
        observation.setBird(savedBird);
        observation.setBirdWatcher(savedBirdWatcher);

         given()
                .contentType(ContentType.JSON)
                .body(observation)
                .when()
                .post("/observation/add")
                .then()
                .statusCode(201)
                .body(equalTo("Successfully added new Observation!"));




    }


}
