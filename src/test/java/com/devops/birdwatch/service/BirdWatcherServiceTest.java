package com.devops.birdwatch.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.devops.birdwatch.model.BirdWatcher;
import com.devops.birdwatch.repository.BirdWatcherRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BirdWatcherServiceTest {

  @InjectMocks
  private BirdWatcherService birdWatcherService;

  @Mock
  private BirdWatcherRepository birdWatcherRepository;

  @BeforeEach
  public void init() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllBirdWatchers() {
    BirdWatcher birdWatcher1 = new BirdWatcher();
    BirdWatcher birdWatcher2 = new BirdWatcher();
    when(birdWatcherRepository.findAll()).thenReturn(Arrays.asList(birdWatcher1, birdWatcher2));

    ResponseEntity<List<BirdWatcher>> response = birdWatcherService.getAllBirdWatchers();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(2, response.getBody().size());
  }

  @Test
  public void testAddBirdWatcher() {
    BirdWatcher birdWatcher = new BirdWatcher();
    birdWatcher.setEmail("test@example.com");
    when(birdWatcherRepository.findByEmail(birdWatcher.getEmail())).thenReturn(Optional.empty());

    ResponseEntity<BirdWatcher> response = birdWatcherService.addBirdWatcher(birdWatcher);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    verify(birdWatcherRepository).save(birdWatcher);
  }

  @Test
  public void testAddBirdWatcherWithEmailTaken() {
    BirdWatcher birdWatcher = new BirdWatcher();
    birdWatcher.setEmail("test@example.com");
    when(birdWatcherRepository.findByEmail(birdWatcher.getEmail())).thenReturn(
        Optional.of(birdWatcher));

    ResponseEntity<BirdWatcher> response = birdWatcherService.addBirdWatcher(birdWatcher);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  public void testDeleteBirdWatcher() {
    Long id = 1L;
    when(birdWatcherRepository.existsById(id)).thenReturn(true);

    ResponseEntity<String> response = birdWatcherService.deleteBirdWatcher(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(birdWatcherRepository).deleteById(id);
  }

  @Test
  public void testDeleteBirdWatcherNotFound() {
    Long id = 1L;
    when(birdWatcherRepository.existsById(id)).thenReturn(false);

    ResponseEntity<String> response = birdWatcherService.deleteBirdWatcher(id);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }


}
