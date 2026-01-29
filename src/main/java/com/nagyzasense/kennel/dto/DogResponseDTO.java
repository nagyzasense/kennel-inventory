package com.nagyzasense.kennel.dto;

import com.nagyzasense.kennel.model.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DogResponseDTO {
    private String name;
    private String breed;
    private Gender gender;
    private String image;
}
