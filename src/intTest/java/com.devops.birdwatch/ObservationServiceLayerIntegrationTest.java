package com.devops.birdwatch;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.devops.birdwatch.model.Bird;
import com.devops.birdwatch.model.BirdWatcher;
import com.devops.birdwatch.model.Observation;
import com.devops.birdwatch.repository.BirdRepository;
import com.devops.birdwatch.repository.BirdWatcherRepository;
import com.devops.birdwatch.repository.ObservationRepository;
import io.restassured.RestAssured;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ObservationServiceLayerIntegrationTest {

  @LocalServerPort
  private int port;

  @Autowired
  BirdRepository birdRepository;
  @Autowired
  BirdWatcherRepository birdWatcherRepository;
  @Autowired
  ObservationRepository observationRepository;

  @BeforeEach
  public void setUp() {
    RestAssured.port = port;
  }

  @Test
  public void whenGetObservationThenStatus200AndCorrectData() {
    Bird bird = new Bird();
    bird.setSpeices("Testfågel");
    bird.setType("TestFågeltyp");
    Bird savedBird = birdRepository.save(bird);

    BirdWatcher birdWatcher = new BirdWatcher();
    birdWatcher.setFirstName("Fågel");
    birdWatcher.setLastName("Fenix");
    birdWatcher.setEmail("fenix@birdmail.com");
    BirdWatcher savedBirdWatcher = birdWatcherRepository.save(birdWatcher);

    Observation observation = new Observation();

    observation.setObservationDate(LocalDate.parse("2022-11-07"));
    observation.setLocation("Gnesta");
    observation.setCountry("Sweden");
    observation.setBird(savedBird);
    observation.setBirdWatcher(savedBirdWatcher);

    Observation savedObservation = observationRepository.save(observation);

    given().pathParam("id", savedObservation.getId())
        .when()
        .get("/observation/id/{id}")
        .then()
        .statusCode(200)
        .body("observationDate", equalTo("2022-11-07"))
        .body("location", equalTo("Gnesta"))
        .body("country", equalTo("Sweden"))
        .body("bird.speices", equalTo("Testfågel"))
        .body("bird.type", equalTo("TestFågeltyp"))
        .body("birdWatcher.firstName", equalTo("Fågel"))
        .body("birdWatcher.lastName", equalTo("Fenix"))
        .body("birdWatcher.email", equalTo("fenix@birdmail.com"));
  }

}
