package com.nagyzasense.kennel.service;


import org.springframework.stereotype.Service;

import com.nagyzasense.kennel.model.Dog;
import com.nagyzasense.kennel.model.Gender;

@Service
public class DogServiceImpl implements DogService {

    @Override
    public Dog getDogById(Long id) {
        Dog dog = new Dog();
        dog.setId(id);
        dog.setName("Morzsi");
        dog.setBreed("tacskó");
        dog.setGender(Gender.MALE);
        return dog;
    }
}
