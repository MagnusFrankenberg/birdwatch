package com.devops.birdwatch.service;

import com.devops.birdwatch.model.Bird;
import com.devops.birdwatch.model.BirdWatcher;
import com.devops.birdwatch.model.Observation;
import com.devops.birdwatch.model.ObservationTemplate;
import com.devops.birdwatch.repository.BirdRepository;
import com.devops.birdwatch.repository.BirdWatcherRepository;
import com.devops.birdwatch.repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ObservationService {

    @Autowired
    ObservationRepository observationRepository;
    @Autowired
    BirdRepository birdRepository;
    @Autowired
    BirdWatcherRepository birdWatcherRepository;

    public ResponseEntity<String> addObservation(ObservationTemplate template) {
        Optional<Bird> birdOptional;
        Optional<BirdWatcher> birdWatcherOptional;
        Observation observation;


        try {
            birdOptional = birdRepository.findBySpeices(template.getSpeices());
            if (!birdOptional.isPresent()) {
                return new ResponseEntity<>("Bird speices not found, please register bird speices", HttpStatus.BAD_REQUEST);
            }
            birdWatcherOptional = birdWatcherRepository.findByEmail(template.getEmail());
            if (!birdWatcherOptional.isPresent()) {
                return new ResponseEntity<>("Email not found, please check if registered or spelled incorrectly", HttpStatus.BAD_REQUEST);
            }

            observation = new Observation(template.getObservationDate(), template.getLocation(), template.getCountry(),
                    birdOptional.get(), birdWatcherOptional.get());
            observationRepository.save(observation);

            return new ResponseEntity<>("Successfully added new Observation!", HttpStatus.CREATED);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Could not add new Observation", HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<List<Observation>> getAllObservations() {
        try{
            return new ResponseEntity<>(observationRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
}
