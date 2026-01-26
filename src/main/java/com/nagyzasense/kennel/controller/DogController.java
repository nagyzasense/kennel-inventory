package com.nagyzasense.kennel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagyzasense.kennel.model.Dog;
import com.nagyzasense.kennel.model.Gender;

@RestController
@RequestMapping("api/")
public class DogController {

    @GetMapping("dog/{id}")
    public ResponseEntity<String> getDog(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(
                "The controller works, the path variable is: " + id + ".",
                HttpStatus.OK);
    }
}
