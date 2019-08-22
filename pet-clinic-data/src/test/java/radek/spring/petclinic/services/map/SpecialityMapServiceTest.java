package radek.spring.petclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import radek.spring.petclinic.model.Speciality;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SpecialityMapServiceTest {

    private SpecialityMapService specialityMapService;
    private static final Long SPECIALITY_ID = 1L;

    @BeforeEach
    void setUp() {
        specialityMapService = new SpecialityMapService();
        specialityMapService.save(Speciality.builder().description("speciality").build());
    }

    @Test
    void findAll() {
        Set<Speciality> specialities = specialityMapService.findAll();
        assertEquals(1, specialities.size());
    }

    @Test
    void deleteById() {
        specialityMapService.deleteById(SPECIALITY_ID);
        assertEquals(0, specialityMapService.findAll().size());
    }

    @Test
    void delete() {
        specialityMapService.delete(specialityMapService.findById(SPECIALITY_ID));
        assertEquals(0,specialityMapService.findAll().size());
    }

    @Test
    void save() {
        specialityMapService.save(Speciality.builder().description("another speciality").build());
        assertEquals(2,specialityMapService.findAll().size());
    }

    @Test
    void findById() {
        Speciality specialityTest = specialityMapService.findById(SPECIALITY_ID);
        assertEquals(SPECIALITY_ID, specialityTest.getId());
    }

    @Test
    void findByIdNullId() {
        Speciality specialityTest = specialityMapService.findById(null);
        assertNull(specialityTest);
    }

    @Test
    void findByIdNoExistingId() {
        Speciality speciality = specialityMapService.findById(321L);
        assertNull(speciality);
    }

}