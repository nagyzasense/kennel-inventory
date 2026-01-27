package com.nagyzasense.kennel.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagyzasense.kennel.model.Dog;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
}
