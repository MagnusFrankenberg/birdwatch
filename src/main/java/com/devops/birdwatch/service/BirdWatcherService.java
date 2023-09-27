package com.devops.birdwatch.service;

import com.devops.birdwatch.model.BirdWatcher;
import com.devops.birdwatch.repository.BirdWatcherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BirdWatcherService {

    @Autowired
    BirdWatcherRepository birdWatcherRepository;


    public ResponseEntity<List<BirdWatcher>> getAllBirdWatchers() {
        try {
            return new ResponseEntity<>(birdWatcherRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addBirdWatcher(BirdWatcher birdWatcher) {
        try {
            Optional<BirdWatcher> birdWatcherOptional = birdWatcherRepository.findByEmail(birdWatcher.getEmail());
            if (birdWatcherOptional.isPresent()) {
                return new ResponseEntity<>("email already taken", HttpStatus.BAD_REQUEST);
            } else {
                birdWatcherRepository.save(birdWatcher);
                return new ResponseEntity<>("New BirdWatcher added successfully!", HttpStatus.CREATED);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Could not add new BirdWatcher", HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<String> deleteBirdWatcher(Long id) {
        try {
            if (!birdWatcherRepository.existsById(id)) {
                return new ResponseEntity<>("Could not find BirdWatcher with id " + id, HttpStatus.NOT_FOUND);
            } else {
                birdWatcherRepository.deleteById(id);
                return new ResponseEntity<>("BirdWatcher with id " + id + " deleted", HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Failed to delete", HttpStatus.BAD_REQUEST);
        }
    }
}
