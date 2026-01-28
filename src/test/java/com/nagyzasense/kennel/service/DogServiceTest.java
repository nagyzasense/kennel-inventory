package com.nagyzasense.kennel.service;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.nagyzasense.kennel.dto.DogResponseDTO;
import com.nagyzasense.kennel.model.Dog;
import com.nagyzasense.kennel.model.Gender;
import com.nagyzasense.kennel.repository.DogRepository;
import com.nagyzasense.kennel.util.DogModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DogServiceTest {

    private DogService underTest;

    private DogRepository dogRepositoryMock;

    private DogModelMapper dogModelMapper;

    private Dog dog;

    @BeforeEach
    void setUp() {
        dogRepositoryMock = Mockito.mock(DogRepository.class);
        dogModelMapper = DogModelMapper.INSTANCE;
        underTest = new DogServiceImpl(dogRepositoryMock, dogModelMapper);
        dog = new Dog();
        dog.setId(1L);
        dog.setName("Morzsi");
        dog.setBreed("dachshund");
        dog.setGender(Gender.FEMALE);
    }

    @Test
    void getDogById() {

        Mockito.when(dogRepositoryMock.findById(1L)).thenReturn(Optional.of(dog));

        DogResponseDTO dog2 = new DogResponseDTO();
        dog2.setName("Morzsi");
        dog2.setBreed("dachshund");
        dog2.setGender(Gender.FEMALE);

        DogResponseDTO result = underTest.getDogById(1L);
        assertEquals(dog2, result);
    }
}