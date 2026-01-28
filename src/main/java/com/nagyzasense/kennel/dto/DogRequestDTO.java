package com.nagyzasense.kennel.dto;

import com.nagyzasense.kennel.validation.ValidGender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class DogRequestDTO {

    @NotBlank(message = "The name field is mandatory!")
    @Size(min = 1, max = 50, message = "Invalid field: name. Size must be between 1 and 50 character(s)!")
    private String name;

    @NotBlank(message = "The breed field is mandatory!")
    @Size(min = 1, max = 50, message = "Invalid field: breed. Size must be between 1 and 50 character(s)!")
    private String breed;

    @ValidGender
    private String gender;

    private String image;

}
