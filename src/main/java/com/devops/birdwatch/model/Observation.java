package com.devops.birdwatch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "observation_date")
    private LocalDate observationDate;
    private String location;
    private String country;

    @ManyToOne
    @JoinColumn(name = "bird_id")
    private Bird bird;

    @ManyToOne
    @JoinColumn(name = "birdWatcher_id")
    private BirdWatcher birdWatcher;

    public Observation(LocalDate observationDate, String location, String country, Bird bird, BirdWatcher birdWatcher) {
        this.observationDate = observationDate;
        this.location = location;
        this.country = country;
        this.bird = bird;
        this.birdWatcher = birdWatcher;
    }
}
