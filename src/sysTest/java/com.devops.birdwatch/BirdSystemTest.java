package com.devops.birdwatch;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.devops.birdwatch.model.Bird;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BirdSystemTest {

  @BeforeEach
  public void setUpClass() {
    RestAssured.baseURI = "http://localhost:8080";
  }


  @Test
  public void addExpect201GetExpect200() {
    Bird bird = new Bird();
    bird.setSpeices("Testfågel");
    bird.setType("TestFågeltyp");

    Bird savedBird = given()
        .contentType(ContentType.JSON)
        .body(bird)
        .when()
        .post("/bird/add")
        .then()
        .statusCode(201)
        .extract().as(Bird.class);

    assertEquals("Testfågel", savedBird.getSpeices());
    assertEquals("TestFågeltyp", savedBird.getType());

    given().pathParam("id", savedBird.getId())
        .when()
        .get("/bird/id/{id}")
        .then()
        .statusCode(200)
        .body("speices", equalTo("Testfågel"))
        .body("type", equalTo("TestFågeltyp"));
  }


}
