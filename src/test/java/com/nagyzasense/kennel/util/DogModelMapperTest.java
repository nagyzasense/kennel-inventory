package com.nagyzasense.kennel.util;

import org.junit.jupiter.api.Test;

import com.nagyzasense.kennel.model.Gender;

import static org.junit.jupiter.api.Assertions.*;

class DogModelMapperTest {

    DogModelMapper dogModelMapper = DogModelMapper.INSTANCE;

    @Test
    void genderToString() {
        Gender gender = Gender.MALE;
        String result = dogModelMapper.genderToString(gender);
        assertNotNull(result);
    }
}