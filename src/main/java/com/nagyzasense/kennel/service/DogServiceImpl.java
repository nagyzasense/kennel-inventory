package com.nagyzasense.kennel.service;


import java.util.List;
import java.util.Optional;import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nagyzasense.kennel.dto.DogRequestDTO;
import com.nagyzasense.kennel.dto.DogResponseDTO;
import com.nagyzasense.kennel.model.Dog;
import com.nagyzasense.kennel.repository.DogRepository;
import com.nagyzasense.kennel.util.DogModelMapper;


@Service
public class DogServiceImpl implements DogService {

    @Value("${application.relative.path}")
    private String relativePathPrefix;
    private final DogRepository dogRepository;
    private final DogModelMapper dogModelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(DogServiceImpl.class);

    @Autowired
    public DogServiceImpl(DogRepository dogRepository, DogModelMapper dogModelMapper) {
        this.dogRepository = dogRepository;
        this.dogModelMapper = dogModelMapper;
    }


    @Override
    public DogResponseDTO getDogById(Long id) {
        return dogModelMapper.dogToDogResponseDTO(dogRepository.findById(id).orElse(null));
    }

    @Override
    public List<DogResponseDTO> searchDogsByBreed(String breed) {
        return dogRepository.searchDogsByBreed(breed).stream()
                .map(dogModelMapper::dogToDogResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DogResponseDTO> findAll() {
        return dogRepository.findAll().stream()
                .map(dogModelMapper::dogToDogResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String saveDog(DogRequestDTO dogDTO) {
        Dog saved = dogRepository.save(dogModelMapper.dogRequestDTOtoDog(dogDTO));
        return relativePathPrefix + saved.getId();
    }

    @Override
    public void deleteDog(Long id) {
        dogRepository.findById(id).ifPresent(dogRepository::delete);
    }
}
