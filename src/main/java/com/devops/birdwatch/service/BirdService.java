package com.devops.birdwatch.service;

import com.devops.birdwatch.model.Bird;
import com.devops.birdwatch.repository.BirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BirdService {
    private static final Logger logger = LoggerFactory.getLogger(ObservationService.class);
    @Autowired
    BirdRepository birdRepository;

    public ResponseEntity<List<Bird>> getAllBirds(){
        try{
            return new ResponseEntity<>(birdRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Bird>> getBirdsByType(String type) {
        try{
            return new ResponseEntity<>(birdRepository.findByType(type), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Bird> addBird(Bird bird) {
        try {
            Bird savedBird = birdRepository.save(bird);
            return new ResponseEntity<>(savedBird, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteBird(Long id) {
        try {
            if (!birdRepository.existsById(id)) {
                return new ResponseEntity<>("Could not find Bird with id " + id, HttpStatus.NOT_FOUND);
            } else {
                birdRepository.deleteById(id);
                return new ResponseEntity<>("Bird with id " + id + " deleted", HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Failed to delete", HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<Bird> getBirdById(Long id) {
        Optional<Bird> optionalBird = birdRepository.findById(id);
        if(optionalBird.isPresent()){
            return new ResponseEntity<>(optionalBird.get(),HttpStatus.OK);
        }else{
            logger.warn("No Bird found for ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

