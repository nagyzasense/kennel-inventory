package com.nagyzasense.kennel.service;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.nagyzasense.kennel.model.Dog;
import com.nagyzasense.kennel.repository.DogRepository;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class DogImageServiceImplTest {

    @Autowired
    private DogImageService dogImageService;

    @Autowired
    private DogRepository dogRepository;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(dogImageService, "imageBaseUrl", "https://dog.ceo");
        ReflectionTestUtils.setField(dogImageService, "imagePath", "/api/breeds/image/random");
    }

    @Test
    void testLoadImages() {
        List<Dog> all = dogRepository.findAll();
        assertNotNull(all);
        assertNotNull(all.get(0));
        assertNull(all.get(0).getImage());

        dogImageService.loadImages();

        await()
                .atMost(20, SECONDS)
                .pollInterval(Duration.ofMillis(100))
                .untilAsserted(() -> {
                    List<Dog> all2 = dogRepository.findAll();
                    assertNotNull(all2);
                    assertNotNull(all2.get(0));
                    assertNotNull(all2.get(0).getImage());
                });
    }
}