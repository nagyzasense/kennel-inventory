package com.nagyzasense.kennel.dto;

import com.nagyzasense.kennel.model.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DogResponseDTO {
    private String name;
    private String breed;
    private Gender gender;
    private String image;
}
