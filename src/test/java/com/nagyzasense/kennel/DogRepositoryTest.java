package com.nagyzasense.kennel;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.nagyzasense.kennel.model.Dog;
import com.nagyzasense.kennel.repository.DogRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
public class DogRepositoryTest {

    @Autowired
    private DogRepository dogRepository;

    @Test
    void testFindById() {
        Optional<Dog> dog = dogRepository.findById(1L);
        assertTrue(dog.isPresent());
    }

    @Test
    void testFindAll() {
        List<Dog> dogs = dogRepository.findAll();
        assertNotNull(dogs);
        assertTrue(ObjectUtils.isNotEmpty(dogs));
        assertEquals(2, dogs.size());
    }

    @Test
    void testSearchDogsByBreed() {
        List<Dog> dachshund = dogRepository.searchDogsByBreed("dachshund");
        assertNotNull(dachshund);
        assertTrue(ObjectUtils.isNotEmpty(dachshund));
        assertEquals(1, dachshund.size());
    }
}
