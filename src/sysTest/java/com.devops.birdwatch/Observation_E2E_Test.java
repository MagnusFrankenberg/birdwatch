package com.devops.birdwatch;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.devops.birdwatch.model.Bird;
import com.devops.birdwatch.model.BirdWatcher;
import com.devops.birdwatch.model.ObservationTemplate;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Observation_E2E_Test {

  Bird savedBird;
  BirdWatcher savedBirdWatcher;

  @BeforeEach
  public void setUpClass() {
    RestAssured.baseURI = "http://localhost:8080";
  }

  @Test
  public void E2E_addObservationAndExpect201() {

    //Creating a bird
    Bird bird = new Bird();
    bird.setSpeices("FågelArt1");
    bird.setType("FågelTyp1");

    savedBird = given()
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
    birdWatcher.setEmail("epost@adress2.se");

    savedBirdWatcher = given()
        .contentType(ContentType.JSON)
        .body(birdWatcher)
        .when()
        .post("/birdwatcher/add")
        .then()
        .statusCode(201)
        .extract().as(BirdWatcher.class);

    //Creating an Observation
    ObservationTemplate observationTemplate = new ObservationTemplate();
    observationTemplate.setObservationDate(LocalDate.parse("2022-11-07"));
    observationTemplate.setLocation("Location1");
    observationTemplate.setCountry("Country1");
    observationTemplate.setSpeices("FågelArt1");
    observationTemplate.setEmail("epost@adress2.se");

    given()
        .contentType(ContentType.JSON)
        .body(observationTemplate)
        .when()
        .post("/observation/add")
        .then()
        .statusCode(201)
        .body(equalTo("Successfully added new Observation!"));
  }

}
