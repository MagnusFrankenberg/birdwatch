package com.devops.birdwatch.controller;


import com.devops.birdwatch.model.Bird;
import com.devops.birdwatch.model.BirdWatcher;
import com.devops.birdwatch.service.BirdWatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("birdwatcher")
public class BirdWatcherController {

    @Autowired
    BirdWatcherService birdWatcherService;

    @GetMapping("all")
    public ResponseEntity<List<BirdWatcher>> getAllBirdWatchers() {
        return birdWatcherService.getAllBirdWatchers();
    }

    @PostMapping("add")
    public ResponseEntity<BirdWatcher> addBirdWatcher(@RequestBody BirdWatcher birdWatcher){
        return birdWatcherService.addBirdWatcher(birdWatcher);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteBirdWatcher(@PathVariable Long id){
        return birdWatcherService.deleteBirdWatcher(id);
    }

}
