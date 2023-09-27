package com.devops.birdwatch.service;

import com.devops.birdwatch.model.Bird;
import com.devops.birdwatch.repository.BirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BirdService {

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

    public ResponseEntity<String> addBird(Bird bird) {
        try{
            birdRepository.save(bird);
            return new ResponseEntity<>("Successfully added new Bird!",HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Could not add new Bird",HttpStatus.BAD_REQUEST);
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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateBird(Bird bird, Long id) {
        if(!birdRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bird not found");
        }
        birdRepository.save(bird);
        return "Bird with id " + id + " successfully updated";
    }
}

