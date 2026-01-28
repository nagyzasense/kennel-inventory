package com.nagyzasense.kennel.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nagyzasense.kennel.model.Dog;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {

    @NativeQuery("SELECT * FROM dogs WHERE breed LIKE CONCAT('%',:breed, '%')")
    List<Dog> searchDogsByBreed(@Param("breed") String breed);
}
