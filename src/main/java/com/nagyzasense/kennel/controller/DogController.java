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

import com.nagyzasense.kennel.advice.DogErrorResponse;
import com.nagyzasense.kennel.advice.ValidationErrorResponse;
import com.nagyzasense.kennel.dto.DogRequestDTO;
import com.nagyzasense.kennel.dto.DogResponseDTO;
import com.nagyzasense.kennel.dto.DogSuccessfullySavedDTO;
import com.nagyzasense.kennel.exception.DogNotFoundException;
import com.nagyzasense.kennel.service.DogService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/")
@Tag(name = "Kennel inventory", description = "API for manage flock of dogs")
public class DogController {

    private final DogService dogService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DogController.class);

    @Autowired
    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @Operation(summary = "Get dog by ID", description = "Retrieves the dog using its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Dog found",
                    content = @Content(schema = @Schema(implementation = DogResponseDTO.class))),
            @ApiResponse(responseCode = "404",
                    description = "Dog not found",
                    content = @Content(schema = @Schema(implementation = DogErrorResponse.class)))
    })
    @GetMapping(path = "dog/{id}", produces = "application/json")
    public ResponseEntity<DogResponseDTO> getDog(@PathVariable(name = "id") Long id) throws DogNotFoundException {
        DogResponseDTO dog = dogService.getDogById(id);
        if (ObjectUtils.isEmpty(dog)) {
            throw new DogNotFoundException("The dog not found!");
        }
        return new ResponseEntity<>(dog, HttpStatus.OK);
    }

    @Operation(summary = "Search dogs by breed", description = "Retrieve a list of dogs which breed name contains the search text")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Dogs retrieved successfully",
                    content = @Content(schema = @Schema(implementation = DogResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Error response if the 'breed' parameter is missing",
                    content = @Content(schema = @Schema(implementation = DogErrorResponse.class)))
    })
    @GetMapping(path = "dogs/search", produces = "application/json")
    public ResponseEntity<List<DogResponseDTO>> searchDogsByBreed(@RequestParam(name = "breed") String breed) {
        LOGGER.info("Breed: " + breed);
        return new ResponseEntity<>(dogService.searchDogsByBreed(breed), HttpStatus.OK);
    }

    @Operation(summary = "Get all dogs", description = "Retrieve a list of all dogs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Dogs retrieved successfully",
                    content = @Content(schema = @Schema(implementation = DogResponseDTO.class)))
    })
    @GetMapping(path = "dogs", produces = "application/json")
    public ResponseEntity<List<DogResponseDTO>> findAll() {
        return new ResponseEntity<>(dogService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Create new dog", description = "Add a new dog to the flock of the dogs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Dogs created successfully",
                    content = @Content(schema = @Schema(implementation = DogSuccessfullySavedDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Validation failed",
                    content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
    })
    @PostMapping(path = "dog", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DogSuccessfullySavedDTO> addDog(@RequestBody @Valid DogRequestDTO dog) {
        return new ResponseEntity<>(dogService.saveDog(dog), HttpStatus.OK);
    }

    @Operation(summary = "Delete dog by ID", description = "Delete the dog using its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Dogs retrieved successfully",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping(path = "dog/{id}")
    public ResponseEntity<Void> deleteDog(@PathVariable(name = "id") Long id) {
        dogService.deleteDog(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
