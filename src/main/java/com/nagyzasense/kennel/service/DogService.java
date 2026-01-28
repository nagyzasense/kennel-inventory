package com.nagyzasense.kennel.service;

import java.util.List;

import com.nagyzasense.kennel.dto.DogRequestDTO;
import com.nagyzasense.kennel.dto.DogResponseDTO;

public interface DogService {

    DogResponseDTO getDogById(Long id);

    List<DogResponseDTO> searchDogsByBreed(String breed);

    List<DogResponseDTO> findAll();

    String saveDog(DogRequestDTO dogDTO);

    void deleteDog(Long id);
}
