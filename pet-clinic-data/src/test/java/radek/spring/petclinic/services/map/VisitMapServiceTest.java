package radek.spring.petclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import radek.spring.petclinic.model.Owner;
import radek.spring.petclinic.model.Pet;
import radek.spring.petclinic.model.Visit;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VisitMapServiceTest {

    private VisitMapService visitMapService;
    private final Long visitId = 1L;
    private final Long ownerId = 11L;
    private final Long petId = 111L;
    private Owner owner = Owner.builder().id(ownerId).build();
    private Pet dog = Pet.builder().id(petId).owner(owner).build();
    private Set<Visit> visits = new HashSet<>();

    @BeforeEach
    void setUp() {
        visitMapService = new VisitMapService();
        visitMapService.save(Visit.builder().pet(dog).build());
        visits = visitMapService.findAll();
    }

    @Test
    void findAll() {
        assertEquals(1, visits.size());
    }

    @Test
    void deleteById() {
        visitMapService.deleteById(visitId);
        assertEquals(0, visitMapService.findAll().size());
    }

    @Test
    void delete() {
        visitMapService.delete(visitMapService.findById(visitId));
        assertEquals(0, visitMapService.findAll().size());
    }

    @Test
    void save() {
        Owner ownerTest = Owner.builder().id(12L).build();
        Pet dogTest = Pet.builder().id(112L).owner(ownerTest).build();
        Visit visitTest = Visit.builder().pet(dogTest).build();
        visitMapService.save(visitTest);
        assertEquals(visits.size() + 1, visitMapService.findAll().size());
    }

    @Test
    void saveWithoutParameters(){
        Visit visitTest = Visit.builder().build();
        assertThrows(RuntimeException.class, ()-> visitMapService.save(visitTest));
    }

    @Test
    void findById() {
        Visit visitTest = visitMapService.findById(visitId);
        assertEquals(visitId, visitTest.getId());
    }
    @Test
    void findByIdWithNullId() {
        Visit visitTest = visitMapService.findById(null);
        assertNull(visitTest);
    }

    @Test
    void findByIdNoExistingId() {
        Visit visitTest = visitMapService.findById(321L);
        assertNull(visitTest);
    }
}