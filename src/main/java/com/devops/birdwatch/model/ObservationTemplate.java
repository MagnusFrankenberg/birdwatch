package com.devops.birdwatch.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ObservationTemplate {

  private LocalDate observationDate;
  private String location;
  private String country;
  private String speices;
  private String email;

}
