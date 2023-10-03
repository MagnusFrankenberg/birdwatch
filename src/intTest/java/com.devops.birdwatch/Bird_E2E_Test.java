package com.devops.birdwatch;

import com.devops.birdwatch.model.Bird;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Bird_E2E_Test {

    @BeforeEach
    public void setUpClass() {
        RestAssured.baseURI = "http://localhost:8080";
    }


    @Test
    public void whenPostStatus201whenGetStatus200andCorrectData() {
        Bird bird = new Bird();
        bird.setSpeices("Testfågel");
        bird.setType("TestFågeltyp");


       String responseBody = given()
                .contentType(ContentType.JSON)
                .body(bird)
                .when()
                .post("/bird/add")
                .then()
                .statusCode(201)
                .extract()
                .asString();

       assertEquals("Successfully added new Bird!", responseBody);


        given().pathParam("type", bird.getType())
                .when()
                .get("/bird/type/{type}")
                .then()
                .statusCode(200)
                .body("[0].speices", equalTo("Testfågel"))
                .body("[0].type", equalTo("TestFågeltyp"));
    }


}
