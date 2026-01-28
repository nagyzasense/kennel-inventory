package com.nagyzasense.kennel.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.nagyzasense.kennel.dto.DogRequestDTO;
import com.nagyzasense.kennel.dto.DogResponseDTO;
import com.nagyzasense.kennel.model.Dog;
import com.nagyzasense.kennel.model.Gender;
import com.nagyzasense.kennel.repository.DogRepository;
import com.nagyzasense.kennel.util.DogModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DogServiceTest {

    @Mock
    private DogRepository dogRepositoryMock;

    @Mock
    private DogModelMapper dogModelMapper;

    @InjectMocks
    private DogServiceImpl underTest;

    private Dog dog;
    private DogResponseDTO dogResponseDTO;
    private DogRequestDTO dogRequestDTO;

    @BeforeEach
    void setUp() {
        dog = new Dog();
        dog.setId(1L);
        dog.setName("Morzsi");
        dog.setBreed("dachshund");
        dog.setGender(Gender.FEMALE);

        dogResponseDTO = new DogResponseDTO("Morzsi", "dachshund", Gender.FEMALE, null);
        dogRequestDTO = new DogRequestDTO("Morzsi", "dachshund", "FEMALE", null);

        ReflectionTestUtils.setField(underTest, "relativePathPrefix", "relativePathPrefix");
    }

    @Test
    void testGetDogById() {

        when(dogRepositoryMock.findById(1L)).thenReturn(Optional.of(dog));
        when(dogModelMapper.dogToDogResponseDTO(dog)).thenReturn(dogResponseDTO);

        DogResponseDTO expected = new DogResponseDTO();
        expected.setName("Morzsi");
        expected.setBreed("dachshund");
        expected.setGender(Gender.FEMALE);

        DogResponseDTO result = underTest.getDogById(1L);

        verify(dogRepositoryMock).findById(1L);
        verify(dogModelMapper).dogToDogResponseDTO(dog);
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void testGetDogByIdWhenNoDataForId() {
        when(dogRepositoryMock.findById(1L)).thenReturn(Optional.empty());
        when(dogModelMapper.dogToDogResponseDTO(null)).thenReturn(null);

        DogResponseDTO result = underTest.getDogById(1L);

        assertNull(result);
    }

    @Test
    void testFindAll() {
        when(dogRepositoryMock.findAll()).thenReturn(List.of(dog));
        when(dogModelMapper.dogToDogResponseDTO(dog)).thenReturn(dogResponseDTO);

        DogResponseDTO localDog = new DogResponseDTO();
        localDog.setName("Morzsi");
        localDog.setBreed("dachshund");
        localDog.setGender(Gender.FEMALE);
        List<DogResponseDTO> expected = List.of(localDog);

        List<DogResponseDTO> result = underTest.findAll();

        verify(dogRepositoryMock).findAll();
        verify(dogModelMapper).dogToDogResponseDTO(dog);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(expected.get(0), result.get(0));
        assertEquals(expected, result);
    }

    @Test
    void testSaveDog() {
        when(dogRepositoryMock.save(dog)).thenReturn(dog);
        when(dogModelMapper.dogRequestDTOtoDog(dogRequestDTO)).thenReturn(dog);

        String result = underTest.saveDog(dogRequestDTO);

        verify(dogRepositoryMock).save(dog);
        verify(dogModelMapper).dogRequestDTOtoDog(dogRequestDTO);
        assertNotNull(result);
        assertEquals("relativePathPrefix1", result);
    }

    @Test
    void verifyDeleteDog() {
        when(dogRepositoryMock.findById(1L)).thenReturn(Optional.of(dog));

        underTest.deleteDog(1L);

        verify(dogRepositoryMock).findById(1L);
        verify(dogRepositoryMock).delete(dog);
    }
}