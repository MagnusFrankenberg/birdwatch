package com.devops.birdwatch.service;

import com.devops.birdwatch.model.Bird;
import com.devops.birdwatch.model.BirdWatcher;
import com.devops.birdwatch.model.Observation;
import com.devops.birdwatch.model.ObservationResponse;
import com.devops.birdwatch.model.ObservationTemplate;
import com.devops.birdwatch.repository.BirdRepository;
import com.devops.birdwatch.repository.BirdWatcherRepository;
import com.devops.birdwatch.repository.ObservationRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ObservationService {

    private static final Logger logger = LoggerFactory.getLogger(ObservationService.class);

    @Autowired
    ObservationRepository observationRepository;
    @Autowired
    BirdRepository birdRepository;
    @Autowired
    BirdWatcherRepository birdWatcherRepository;


    public ResponseEntity<ObservationResponse> addObservation(ObservationTemplate template) {
        try {
            Bird bird = birdRepository.findBySpeices(template.getSpeices())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bird species not found, please register the bird species"));

            BirdWatcher birdWatcher = birdWatcherRepository.findByEmail(template.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email not found, please check if registered or spelled incorrectly"));

            Observation observation = new Observation(template.getObservationDate(), template.getLocation(), template.getCountry(), bird, birdWatcher);
            Observation savedObservation = observationRepository.save(observation);

            return new ResponseEntity<>(new ObservationResponse(savedObservation, "Successfully added new Observation!"), HttpStatus.CREATED);

        } catch (ResponseStatusException rse) {
            return new ResponseEntity<>(new ObservationResponse(null, rse.getReason()), rse.getStatusCode());
        } catch (Exception e) {
            logger.error("Error adding observation", e);
            return new ResponseEntity<>(new ObservationResponse(null, "Could not add new Observation"), HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<Observation> getObservationById(Long id) {
        Optional<Observation> optionalObservation = observationRepository.findById(id);

        if (optionalObservation.isPresent()) {
            return new ResponseEntity<>(optionalObservation.get(), HttpStatus.OK);
        } else {
            logger.warn("No observation found for ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    public ResponseEntity<List<Observation>> getAllObservations() {
        try{
            return new ResponseEntity<>(observationRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            logger.error("Failed to retrieve all observations", e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }


}
