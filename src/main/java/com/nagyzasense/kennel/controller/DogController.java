package com.nagyzasense.kennel.controller;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagyzasense.kennel.model.Dog;
import com.nagyzasense.kennel.service.DogService;

@RestController
@RequestMapping("api/")
public class DogController {

    private final DogService dogService;

    @Autowired
    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping("dog/{id}")
    public ResponseEntity<Dog> getDog(@PathVariable(name = "id") Long id) {
        Dog dog = dogService.getDogById(id);
        if (ObjectUtils.isEmpty(dog)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dog, HttpStatus.OK);
    }
}
