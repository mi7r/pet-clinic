package radek.spring.petclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import radek.spring.petclinic.model.Pet;
import radek.spring.petclinic.model.PetType;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetTypeMapServiceTest {

    private PetTypeMapService petTypeMapService;

    private final Long petTypeId = 1L;

    @BeforeEach
    void setUp() {
        petTypeMapService = new PetTypeMapService();
        petTypeMapService.save(PetType.builder().id(petTypeId).build());
    }

    @Test
    void findAll() {
        Set<PetType> petTypes = petTypeMapService.findAll();
        assertEquals(1, petTypes.size());
    }

    @Test
    void deleteByIdCorrect() {
        petTypeMapService.deleteById(petTypeId);
        assertEquals(0, petTypeMapService.findAll().size());
    }

    @Test
    void deleteByIdWrongId() {
        petTypeMapService.deleteById(6L);
        assertEquals(1, petTypeMapService.findAll().size());
    }

    @Test
    void deleteByIdWhileIdIsNull() {
        petTypeMapService.deleteById(null);
        assertEquals(1, petTypeMapService.findAll().size());
    }

    @Test
    void deletePetType() {
        petTypeMapService.delete(petTypeMapService.findById(petTypeId));
        assertEquals(0, petTypeMapService.findAll().size());
    }

    @Test
    void deleteNull() {
        petTypeMapService.delete(petTypeMapService.findById(null));
        assertEquals(1, petTypeMapService.findAll().size());
    }

    @Test
    void deletePetTypeWithWrongId() {
        PetType petType = PetType.builder().id(7L).build();
        petTypeMapService.delete(petType);
        assertEquals(1, petTypeMapService.findAll().size());
    }

    @Test
    void deletePetTypeWithNullId() {
        PetType petType = PetType.builder().build();
        petTypeMapService.delete(petType);
        assertEquals(1,petTypeMapService.findAll().size());
    }

    @Test
    void savePetType() {
        Long petTypeTestId = 9L;

        PetType petType = PetType.builder().id(petTypeTestId).build();
        PetType savedPetType = petTypeMapService.save(petType);
        assertEquals(petTypeTestId, savedPetType.getId());
    }

    @Test
    void savePetTypeDuplicatedId() {
        Long petTypeTestId = 1L;
        PetType petType = PetType.builder().id(petTypeTestId).build();
        PetType savedPetType = petTypeMapService.save(petType);
        assertEquals(petTypeTestId, savedPetType.getId());
        assertEquals(1, petTypeMapService.findAll().size());
    }

    @Test
    void savePetTypeNoId() {
        PetType savedPetType = petTypeMapService.save(PetType.builder().build());
        assertNotNull(savedPetType);
        assertNotNull(savedPetType.getId());
        assertEquals(2, petTypeMapService.findAll().size());
    }

    @Test
    void findByIdExistingId() {
        PetType petType = petTypeMapService.findById(petTypeId);
        assertEquals(petTypeId, petType.getId());
    }

    @Test
    void findByIdWithNullId() {
        PetType petType = petTypeMapService.findById(null);
        assertNull(petType);
    }

    @Test
    void findByIdNoExistingId() {
        PetType petType= petTypeMapService.findById(11L);
        assertNull(petType);
    }
}