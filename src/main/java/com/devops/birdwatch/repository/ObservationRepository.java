package com.devops.birdwatch.repository;

import com.devops.birdwatch.model.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservationRepository extends JpaRepository<Observation,Long> {
}
