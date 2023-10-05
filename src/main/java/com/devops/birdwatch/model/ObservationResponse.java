package com.devops.birdwatch.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObservationResponse {

  private Observation data;
  private String message;

}
