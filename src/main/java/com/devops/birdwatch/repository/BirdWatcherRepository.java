package com.devops.birdwatch.repository;

import com.devops.birdwatch.model.BirdWatcher;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirdWatcherRepository extends JpaRepository<BirdWatcher, Long> {
  Optional<BirdWatcher> findByEmail(String email);
}
