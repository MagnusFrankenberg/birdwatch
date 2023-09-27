package com.devops.birdwatch.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ObservationTemplate {

    private LocalDate observationDate;
    private String location;
    private String country;
    private String speices;
    private String email;

}
