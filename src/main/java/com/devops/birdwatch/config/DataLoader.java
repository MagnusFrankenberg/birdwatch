package com.devops.birdwatch.config;

import com.devops.birdwatch.model.BirdWatcher;
import com.devops.birdwatch.model.Observation;
import com.devops.birdwatch.repository.ObservationRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.devops.birdwatch.model.Bird;
import com.devops.birdwatch.repository.BirdRepository;
import com.devops.birdwatch.repository.BirdWatcherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final BirdRepository birdRepository;
    private final BirdWatcherRepository birdWatcherRepository;
    private final ObservationRepository observationRepository;
    private final ObjectMapper objectMapper;

    public DataLoader(BirdRepository birdRepository, BirdWatcherRepository birdWatcherRepository, ObservationRepository observationRepository, ObjectMapper objectMapper) {
        this.birdRepository = birdRepository;
        this.birdWatcherRepository = birdWatcherRepository;
        this.observationRepository = observationRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String...args) throws Exception{
        try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/birds.json")){
            birdRepository.saveAll(objectMapper.readValue(inputStream, new TypeReference<List<Bird>>(){}));
        }
        try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/birdwatchers.json")){
            birdWatcherRepository.saveAll(objectMapper.readValue(inputStream, new TypeReference<List<BirdWatcher>>(){}));
        }
        try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/observations.json")){
            observationRepository.saveAll(objectMapper.readValue(inputStream, new TypeReference<List<Observation>>(){}));
        }

    }
}
