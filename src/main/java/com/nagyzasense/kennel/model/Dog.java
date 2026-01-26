package com.nagyzasense.kennel.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dog {
    private Long id;
    private String name;
    private String breed;
    private Gender gender;
    private String image;
}
