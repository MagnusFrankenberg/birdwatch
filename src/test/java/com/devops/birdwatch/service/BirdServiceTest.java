package com.devops.birdwatch.service;

import com.devops.birdwatch.model.Bird;
import com.devops.birdwatch.repository.BirdRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class BirdServiceTest {

    @InjectMocks
    private BirdService birdService;

    @Mock
    private BirdRepository birdRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBirds() {
        Bird bird1 = new Bird();
        Bird bird2 = new Bird();
        when(birdRepository.findAll()).thenReturn(Arrays.asList(bird1, bird2));

        ResponseEntity<List<Bird>> response = birdService.getAllBirds();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetBirdsByType() {
        String type = "typeTest";
        Bird bird = new Bird();
        when(birdRepository.findByType(type)).thenReturn(Arrays.asList(bird));

        ResponseEntity<List<Bird>> response = birdService.getBirdsByType(type);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testAddBird() {
        Bird bird = new Bird();
        Bird savedBird = new Bird();
        when(birdRepository.save(bird)).thenReturn(savedBird);

        ResponseEntity<Bird> response = birdService.addBird(bird);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody() instanceof Bird);
    }

    @Test
    public void testDeleteBird() {
        Long id = 1L;
        when(birdRepository.existsById(id)).thenReturn(true);

        ResponseEntity<String> response = birdService.deleteBird(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(birdRepository, times(1)).deleteById(id);
    }



}
