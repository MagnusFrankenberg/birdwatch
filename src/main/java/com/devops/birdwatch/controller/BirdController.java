package com.devops.birdwatch.controller;

import com.devops.birdwatch.model.Bird;
import com.devops.birdwatch.service.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bird")
public class BirdController {

  @Autowired
  BirdService birdService;

  @GetMapping("all")
  public ResponseEntity<List<Bird>> getAllBirds() {
    return birdService.getAllBirds();
  }

  @GetMapping("id/{id}")
  public ResponseEntity<Bird> getBirdById(@PathVariable Long id) {
    return birdService.getBirdById(id);
  }

  @GetMapping("type/{type}")
  public ResponseEntity<List<Bird>> getBirdsByType(@PathVariable String type) {
    return birdService.getBirdsByType(type);
  }

  @PostMapping("add")
  public ResponseEntity<Bird> addBird(@RequestBody Bird bird) {
    return birdService.addBird(bird);
  }

  @DeleteMapping("delete/{id}")
  public ResponseEntity<String> deleteBird(@PathVariable Long id) {
    return birdService.deleteBird(id);
  }


}
