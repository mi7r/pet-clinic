package radek.spring.petclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import radek.spring.petclinic.model.Pet;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetMapServiceTest {

    private PetMapService petMapService;

    private final Long petId = 1L;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();

        petMapService.save(Pet.builder().id(1L).build());
    }

    @Test
    void findAll() {
        Set<Pet> pets = petMapService.findAll();
        assertEquals(1, pets.size());
    }

    @Test
    void deleteByIdCorrect() {
        petMapService.deleteById(petId);
        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteByIdWrongId() {
        petMapService.deleteById(5L);
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void deleteByIdWhileIdIsNull() {
        petMapService.deleteById(null);
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void deletePet() {
        petMapService.delete(petMapService.findById(petId));
        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteNull() {
        petMapService.delete(null);
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void deleteWithWrongId() {
        Pet pet = Pet.builder().id(5L).build();

        petMapService.delete(pet);

        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void deleteWithNullId() {
        Pet pet = Pet.builder().build();

        petMapService.delete(pet);

        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void savePet() {
        Long petTestId = 3L;

        Pet pet = Pet.builder().id(petTestId).build();
        Pet savedPet = petMapService.save(pet);
        assertEquals(petTestId, savedPet.getId());
    }

    @Test
    void savePetDuplicateId() {
        Long petTestId = 1L;

        Pet pet = Pet.builder().id(petTestId).build();
        Pet savedPet = petMapService.save(pet);
        assertEquals(petTestId, savedPet.getId());
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void savePetNoId() {
        Pet savedPet = petMapService.save(Pet.builder().build());
        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        assertEquals(2, petMapService.findAll().size());
    }

    @Test
    void findByIdExistingId() {
        Pet pet = petMapService.findById(petId);
        assertEquals(petId, pet.getId());
    }

    @Test
    void findByIdWithNullId() {
        Pet pet = petMapService.findById(null);
        assertNull(pet);
    }

    @Test
    void findByIdNotExistingId() {
        Pet pet = petMapService.findById(5L);
        assertNull(pet);
    }
}