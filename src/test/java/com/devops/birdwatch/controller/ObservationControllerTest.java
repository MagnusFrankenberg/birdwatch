package com.devops.birdwatch.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.devops.birdwatch.model.Observation;
import com.devops.birdwatch.model.ObservationResponse;
import com.devops.birdwatch.model.ObservationTemplate;
import com.devops.birdwatch.service.ObservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(ObservationController.class)
class ObservationControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ObservationService observationService;


  @Test
  void testGetObservationById() throws Exception {
    Observation observation = new Observation();
    Long observationId = 1L;
    observation.setId(observationId);

    when(observationService.getObservationById(observationId)).thenReturn(
        new ResponseEntity<>(observation, HttpStatus.OK));

    mockMvc.perform(get("/observation/id/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  void testAddObservation() throws Exception {
    ObservationTemplate observationTemplate = new ObservationTemplate();
    ObservationResponse observationResponse = new ObservationResponse(new Observation(), "Success");
    ObjectMapper objectMapper = new ObjectMapper();

    when(observationService.addObservation(observationTemplate)).thenReturn(
        new ResponseEntity<>(observationResponse, HttpStatus.CREATED));

    mockMvc.perform(MockMvcRequestBuilders.post("/observation/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(observationTemplate)))
        .andExpect(status().isCreated())
        .andExpect(content().json(objectMapper.writeValueAsString(observationResponse)));
  }


  @Test
  void testGetAllObservations() throws Exception {
    Observation observation1 = new Observation();
    Observation observation2 = new Observation();
    List<Observation> observations = Arrays.asList(observation1, observation2);

    when(observationService.getAllObservations()).thenReturn(
        new ResponseEntity<>(observations, HttpStatus.OK));

    mockMvc.perform(get("/observation/all"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }
}