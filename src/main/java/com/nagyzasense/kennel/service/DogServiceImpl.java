package com.nagyzasense.kennel.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagyzasense.kennel.model.Dog;
import com.nagyzasense.kennel.repository.DogRepository;

@Service
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;

    @Autowired
    public DogServiceImpl(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }


    @Override
    public Dog getDogById(Long id) {
        return dogRepository.findById(id).orElse(null);
    }
}
