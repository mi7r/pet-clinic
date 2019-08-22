package radek.spring.petclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import radek.spring.petclinic.model.Speciality;
import radek.spring.petclinic.model.Vet;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VetMapServiceTest {

    private VetMapService vetMapService;

    private SpecialityMapService specialityMapService;

    private final static Long VET_ID = 1L;
    private Speciality firstSpeciality = Speciality.builder().description("first speciality").build();
    private Set<Speciality> specialities = new HashSet<>();

    @BeforeEach
    void setUp() {
        specialityMapService = new SpecialityMapService();
        vetMapService = new VetMapService(specialityMapService);
        specialities.add(firstSpeciality);
        vetMapService.save(Vet.builder().specialities(specialities).build());
    }

    @Test
    void findAll() {
        Set<Vet> vets = vetMapService.findAll();
        assertEquals(1, vets.size());
    }

    @Test
    void deleteById() {
        vetMapService.deleteById(VET_ID);
        assertEquals(0, vetMapService.findAll().size());
    }

    @Test
    void delete() {
        vetMapService.delete(vetMapService.findById(VET_ID));
        assertEquals(0, vetMapService.findAll().size());
    }

    @Test
    void save() {
        Speciality testSpeciality = Speciality.builder().description("second speciality").build();
        Set<Speciality> testSpecialities = new HashSet<>();
        testSpecialities.add(testSpeciality);

        vetMapService.save(Vet.builder().specialities(testSpecialities).build());
        assertEquals(2, vetMapService.findAll().size());
    }

    @Test
    void findById() {
        Vet vetTest = vetMapService.findById(VET_ID);
        assertEquals(VET_ID, vetTest.getId());
    }

    @Test
    void findByIdWithNullId() {
        Vet vetTest = vetMapService.findById(null);
        assertNull(vetTest);
    }

    @Test
    void findByIdWithWrongId() {
        Vet vetTest = vetMapService.findById(321L);
        assertNull(vetTest);
    }
}