package com.devops.birdwatch.controller;

import com.devops.birdwatch.model.Bird;
import com.devops.birdwatch.service.BirdService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BirdController.class)
public class BirdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BirdService birdService;

    @Test
    public void testGetAllBirds() throws Exception {
        Bird bird1 = new Bird();
        Bird bird2 = new Bird();
        List<Bird> birds = Arrays.asList(bird1, bird2);

        when(birdService.getAllBirds()).thenReturn(new ResponseEntity<>(birds, HttpStatus.OK));

        mockMvc.perform(get("/snake/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }


    @Test
    public void testGetBirdsByType() throws Exception {
        Bird bird1 = new Bird();
        List<Bird> birds = Arrays.asList(bird1);

        when(birdService.getBirdsByType("someType")).thenReturn(new ResponseEntity<>(birds, HttpStatus.OK));

        mockMvc.perform(get("/bird/type/someType"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testAddBird() throws Exception {
        Bird bird = new Bird();
        Bird savedBird = new Bird();
        ObjectMapper objectMapper = new ObjectMapper();

        when(birdService.addBird(bird)).thenReturn(new ResponseEntity<>(savedBird, HttpStatus.CREATED));

        mockMvc.perform(post("/bird/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bird)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(savedBird)));
    }

    @Test
    void deleteBird() throws Exception{
        Long id = 1L;
        when(birdService.deleteBird(id)).thenReturn(new ResponseEntity<>("Successfully deleted", HttpStatus.OK));

        mockMvc.perform(delete("/bird/delete/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully deleted"));
    }









}