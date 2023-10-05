package com.devops.birdwatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.devops.birdwatch.model.BirdWatcher;
import com.devops.birdwatch.repository.BirdWatcherRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BirdWatcherRepositoryIntegrationTest {

  @Autowired
  private BirdWatcherRepository birdWatcherRepository;

  @Test
  public void whenSaveBirdWatcherThenRetrieveSameBirdatcher() {
    BirdWatcher birdWatcher = new BirdWatcher();
    birdWatcher.setFirstName("Freddy");
    birdWatcher.setLastName("Fågelsson");
    birdWatcher.setEmail("freddy@birdmail.se");

    BirdWatcher savedBirdWatcher = birdWatcherRepository.save(birdWatcher);
    Optional<BirdWatcher> retrievedBirdWatcher = birdWatcherRepository.findById(
        savedBirdWatcher.getId());

    assertTrue(retrievedBirdWatcher.isPresent());
    assertEquals("Freddy", retrievedBirdWatcher.get().getFirstName());
    assertEquals("Fågelsson", retrievedBirdWatcher.get().getLastName());
    assertEquals("freddy@birdmail.se", retrievedBirdWatcher.get().getEmail());
  }
}
