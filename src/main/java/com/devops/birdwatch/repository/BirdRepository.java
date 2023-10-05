package com.devops.birdwatch.repository;

import com.devops.birdwatch.model.Bird;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BirdRepository extends JpaRepository<Bird, Long> {

  List<Bird> findByType(String type);

  Optional<Bird> findBySpeices(String speices);
}
