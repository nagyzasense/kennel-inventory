package com.nagyzasense.kennel.service;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.nagyzasense.kennel.model.Dog;
import com.nagyzasense.kennel.model.Gender;
import com.nagyzasense.kennel.repository.DogRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DogServiceTest {

    private DogService underTest;

    private DogRepository dogRepositoryMock;

    @BeforeEach
    void setUp() {
        dogRepositoryMock = Mockito.mock(DogRepository.class);
        underTest = new DogServiceImpl(dogRepositoryMock);
    }

    @Test
    void getDogById() {

        Dog dog = new Dog();
        dog.setId(1L);
        dog.setName("Morzsi");
        dog.setBreed("dachshund");
        dog.setGender(Gender.FEMALE);

        Mockito.when(dogRepositoryMock.findById(1L)).thenReturn(Optional.of(dog));

        Dog dog2 = new Dog();
        dog2.setId(1L);
        dog2.setName("Morzsi");
        dog2.setBreed("dachshund");
        dog2.setGender(Gender.FEMALE);

        assertEquals(dog2, underTest.getDogById(1L));
    }
}