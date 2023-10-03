package com.devops.birdwatch;


import com.devops.birdwatch.model.Bird;
import com.devops.birdwatch.repository.BirdRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BirdRepositoryIntegrationTest {

    @Autowired
    private BirdRepository birdRepository;

    @Test
    public void whenSaveBirdThenRetrieveSameBird(){
        Bird bird = new Bird();
        bird.setSpeices("Pärluggla");
        bird.setType("Uggla");

        Bird savedBird = birdRepository.save(bird);
        Optional<Bird> retrievedBird = birdRepository.findById(savedBird.getId());

        assertTrue(retrievedBird.isPresent());
        assertEquals("Pärluggla",retrievedBird.get().getSpeices());
        assertEquals("Uggla",retrievedBird.get().getType());
    }
}

