package com.devops.birdwatch.controller;

import com.devops.birdwatch.model.Observation;
import com.devops.birdwatch.model.ObservationResponse;
import com.devops.birdwatch.model.ObservationTemplate;
import com.devops.birdwatch.service.ObservationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("observation")
public class ObservationController {

  @Autowired
  ObservationService observationService;

  @PostMapping("add")
  public ResponseEntity<ObservationResponse> addObservation(
      @RequestBody ObservationTemplate observationTemplate) {
    return observationService.addObservation(observationTemplate);
  }

  @GetMapping("id/{id}")
  public ResponseEntity<Observation> getObservationById(@PathVariable Long id) {
    return observationService.getObservationById(id);
  }

  @GetMapping("all")
  public ResponseEntity<List<Observation>> getAllObservations() {
    return observationService.getAllObservations();
  }


}
