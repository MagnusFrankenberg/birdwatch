package com.devops.birdwatch.repository;

import com.devops.birdwatch.model.BirdWatcher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BirdWatcherRepository extends JpaRepository<BirdWatcher,Long> {

    Optional<BirdWatcher> findByEmail (String email);
}
