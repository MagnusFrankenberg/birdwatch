package com.devops.birdwatch;

import com.devops.birdwatch.model.Bird;
import com.devops.birdwatch.repository.BirdRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BirdServiceLayerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    BirdRepository birdRepository;

    @BeforeEach
    public void setUp(){
        RestAssured.port = port;
    }

    @Test
    public void whenGetBirdThenStatus200AndCorrectData(){
        Bird bird = new Bird();
        bird.setSpeices("Testfågel");
        bird.setType("TestFågeltyp");
        Bird savedBird = birdRepository.save(bird);

        given().pathParam("type",savedBird.getType())
                .when()
                .get("/bird/type/{type}")
                .then()
                .statusCode(200)
                .body("[0].speices", equalTo("Testfågel"))
                .body("[0].type", equalTo("TestFågeltyp"));

    }

}
