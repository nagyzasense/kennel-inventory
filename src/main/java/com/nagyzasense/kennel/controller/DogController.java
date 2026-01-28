package com.nagyzasense.kennel.controller;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagyzasense.kennel.dto.DogRequestDTO;
import com.nagyzasense.kennel.dto.DogResponseDTO;
import com.nagyzasense.kennel.exception.DogNotFoundException;
import com.nagyzasense.kennel.service.DogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/")
public class DogController {

    private final DogService dogService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DogController.class);

    @Autowired
    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping(path = "dog/{id}", produces = "application/json")
    public ResponseEntity<DogResponseDTO> getDog(@PathVariable(name = "id") Long id) throws DogNotFoundException {
        DogResponseDTO dog = dogService.getDogById(id);
        if (ObjectUtils.isEmpty(dog)) {
            throw new DogNotFoundException("The dog not found!");
        }
        return new ResponseEntity<>(dog, HttpStatus.OK);
    }

    @GetMapping(path = "dog/search", produces = "application/json")
    public ResponseEntity<List<DogResponseDTO>> searchDogsByBreed(@RequestParam(name = "breed") String breed) {
        LOGGER.info("Breed: " + breed);
        return new ResponseEntity<>(dogService.searchDogsByBreed(breed), HttpStatus.OK);
    }

    @GetMapping(path = "dogs", produces = "application/json")
    public ResponseEntity<List<DogResponseDTO>> findAll() {
        return new ResponseEntity<>(dogService.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = "dog", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addDog(@RequestBody @Valid DogRequestDTO dog) {
        return new ResponseEntity<>(dogService.saveDog(dog), HttpStatus.OK);
    }

    @DeleteMapping(path = "dog/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteDog(@PathVariable(name = "id") Long id) {
        dogService.deleteDog(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
