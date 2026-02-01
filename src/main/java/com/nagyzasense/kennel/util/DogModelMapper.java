package com.nagyzasense.kennel.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;

import com.nagyzasense.kennel.dto.DogRequestDTO;
import com.nagyzasense.kennel.dto.DogResponseDTO;
import com.nagyzasense.kennel.model.Dog;
import com.nagyzasense.kennel.model.Gender;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DogModelMapper {

    DogModelMapper INSTANCE = Mappers.getMapper(DogModelMapper.class);

    @Mapping(target = "id", ignore = true)
    Dog dogRequestDTOtoDog(DogRequestDTO dto);

    @Mapping(target = "relativeReference", expression = "java(\"/api/dog/\" + dog.getId())")
    DogResponseDTO dogToDogResponseDTO(Dog dog);

    @ValueMapping(target = "MALE", source = "MALE")
    @ValueMapping(target = "FEMALE", source = "FEMALE")
    String genderToString(Gender source);

    @ValueMapping(target = "MALE", source = "MALE")
    @ValueMapping(target = "FEMALE", source = "FEMALE")
    @ValueMapping(source = MappingConstants.ANY_UNMAPPED, target = MappingConstants.NULL)
    Gender stringToGender(String source);
}
