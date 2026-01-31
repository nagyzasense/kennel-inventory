package com.nagyzasense.kennel.util;

import org.junit.jupiter.api.Test;

import com.nagyzasense.kennel.dto.DogRequestDTO;
import com.nagyzasense.kennel.dto.DogResponseDTO;
import com.nagyzasense.kennel.model.Dog;
import com.nagyzasense.kennel.model.Gender;

import static org.junit.jupiter.api.Assertions.*;

class DogModelMapperTest {

    DogModelMapper dogModelMapper = DogModelMapper.INSTANCE;

    @Test
    void testGenderToString() {
        Gender gender = Gender.MALE;
        String result = dogModelMapper.genderToString(gender);
        assertNotNull(result);
        assertEquals("MALE", result);
    }

    @Test
    void testStringToGender() {
        String input = "MALE";
        Gender result = dogModelMapper.stringToGender(input);
        assertNotNull(result);
        assertEquals(Gender.MALE, result);
    }

    @Test
    void testDogToDogResponseDTO() {
        Dog input = new Dog();
        input.setId(1L);
        input.setName("Morzsi");
        input.setBreed("dachshund");
        input.setGender(Gender.FEMALE);

        DogResponseDTO result = dogModelMapper.dogToDogResponseDTO(input);

        DogResponseDTO expected = new DogResponseDTO();
        expected.setName("Morzsi");
        expected.setBreed("dachshund");
        expected.setGender(Gender.FEMALE);
        expected.setRelativeReference("/api/dog/1");

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void testDogRequestDTOToDog() {
        DogRequestDTO input = new DogRequestDTO();
        input.setName("Morzsi");
        input.setBreed("dachshund");
        input.setGender("FEMALE");

        Dog result = dogModelMapper.dogRequestDTOtoDog(input);

        Dog expected = new Dog();
        expected.setName("Morzsi");
        expected.setBreed("dachshund");
        expected.setGender(Gender.FEMALE);

        assertNotNull(result);
        assertEquals(expected, result);
    }
}