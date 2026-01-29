package com.nagyzasense.kennel.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nagyzasense.kennel.dto.DodCeoResponseDTO;
import com.nagyzasense.kennel.model.Dog;
import com.nagyzasense.kennel.repository.DogRepository;

@Service
public class DogImageServiceImpl implements DogImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DogImageService.class);
    private final DogRepository dogRepository;

    @Value("${image.service.base.url}")
    private String imageBaseUrl;

    @Value("${image.service.relative.path}")
    private String imagePath;

    @Autowired
    public DogImageServiceImpl(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Scheduled(cron = "${image.service.cron.expression}")
    @Override
    public void loadImages() {
        LOGGER.info("Loading images to dogs...");
        List<Dog> withoutImage = dogRepository.findAll().stream()
                .filter(dog -> dog.getImage() == null)
                .toList();
        withoutImage.forEach(this::loadImage);
    }

    private void loadImage(Dog dog) {
        WebClient client = WebClient.builder()
                .baseUrl(imageBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        client.get()
                .uri(imagePath)
                .retrieve()
                .bodyToMono(DodCeoResponseDTO.class)
                .subscribe(
                        response -> {
                            dog.setImage(response.getMessage());
                            dogRepository.save(dog);
                            LOGGER.info("Loaded link to dog " + dog.getId() + ": " + response.getMessage());
                        },
                        error -> LOGGER.error(error.getMessage())
                );
    }
}
