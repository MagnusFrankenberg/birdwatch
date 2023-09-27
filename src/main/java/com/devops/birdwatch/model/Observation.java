package com.devops.birdwatch.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
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

}
