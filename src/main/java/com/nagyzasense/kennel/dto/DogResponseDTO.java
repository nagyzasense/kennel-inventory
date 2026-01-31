package com.nagyzasense.kennel.dto;

import com.nagyzasense.kennel.model.Gender;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DogResponseDTO {
    private String name;
    private String breed;
    private Gender gender;
    private String image;
    private String relativeReference;
}
