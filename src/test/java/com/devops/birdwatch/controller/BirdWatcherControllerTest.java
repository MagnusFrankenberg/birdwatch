package com.devops.birdwatch.controller;

import com.devops.birdwatch.model.BirdWatcher;
import com.devops.birdwatch.service.BirdWatcherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BirdWatcherController.class)
public class BirdWatcherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BirdWatcherService birdWatcherService;

    @Test
    public void testGetAllBirdWatchers() throws Exception {
        BirdWatcher birdWatcher1 = new BirdWatcher();
        BirdWatcher birdWatcher2 = new BirdWatcher();
        List<BirdWatcher> birdWatchers = Arrays.asList(birdWatcher1, birdWatcher2);

        when(birdWatcherService.getAllBirdWatchers()).thenReturn(new ResponseEntity<>(birdWatchers, HttpStatus.OK));

        mockMvc.perform(MockMvcRequestBuilders.get("/birdwatcher/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testAddBirdWatcher() throws Exception {
        BirdWatcher birdWatcher = new BirdWatcher();
        ObjectMapper objectMapper = new ObjectMapper();

        when(birdWatcherService.addBirdWatcher(birdWatcher)).thenReturn(new ResponseEntity<>("New BirdWatcher added successfully!", HttpStatus.CREATED));

        mockMvc.perform(MockMvcRequestBuilders.post("/birdwatcher/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(birdWatcher)))
                .andExpect(status().isCreated())
                .andExpect(content().string("New BirdWatcher added successfully!"));
    }

    @Test
    public void testDeleteBirdWatcher() throws Exception {
        Long id = 1L;

        when(birdWatcherService.deleteBirdWatcher(id)).thenReturn(new ResponseEntity<>("BirdWatcher with id 1 deleted", HttpStatus.OK));

        mockMvc.perform(MockMvcRequestBuilders.delete("/birdwatcher/delete/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string("BirdWatcher with id 1 deleted"));
    }
}
