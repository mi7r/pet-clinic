package radek.spring.petclinic.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import radek.spring.petclinic.model.Pet;
import radek.spring.petclinic.model.PetType;
import radek.spring.petclinic.repositories.PetRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetSDJpaServiceTest {

    private static final String PET_NAME = "Rocky";

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetSDJpaService service;
    private Pet returnPet;

    @BeforeEach
    void setUp() {
        PetType dogPetType = PetType.builder().id(2L).name("dog").build();
        returnPet = Pet.builder().id(1L).name(PET_NAME).petType(dogPetType).build();
    }

    @Test
    void findAll() {
        Set<Pet> returnPetSet = new HashSet<>();
        returnPetSet.add(Pet.builder().id(10L).build());
        returnPetSet.add(Pet.builder().id(20L).build());

        when(petRepository.findAll()).thenReturn(returnPetSet);

        Set<Pet> pets = service.findAll();

        assertNotNull(pets);
        assertEquals(2,pets.size());
    }

    @Test
    void findById() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnPet));

        Pet pet = service.findById(1L);
        assertNotNull(pet);
    }

    @Test
    void findByIdNotFound() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.empty());

        Pet pet = service.findById(1L);
        assertNull(pet);
    }

    @Test
    void save() {
        Pet petToSave = Pet.builder().id(33L).build();

        when(petRepository.save(any())).thenReturn(returnPet);

        Pet savedPet = service.save(petToSave);

        assertNotNull(savedPet);
    }

    @Test
    void delete() {
        service.delete(returnPet);
        verify(petRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(petRepository).deleteById(anyLong());
    }
}