package com.devops.birdwatch.repository;

import com.devops.birdwatch.model.Bird;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BirdRepository extends JpaRepository<Bird, Long> {

    List<Bird> findByType(String type);
    Optional<Bird> findBySpeices(String speices);
}
