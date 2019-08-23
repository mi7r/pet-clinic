package radek.spring.petclinic.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import radek.spring.petclinic.model.Speciality;
import radek.spring.petclinic.model.Vet;
import radek.spring.petclinic.repositories.VetRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VetsSDJpaServiceTest {

    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetsSDJpaService service;
    private Vet returnVet;

    @BeforeEach
    void setUp() {
        returnVet = Vet.builder().build();
    }

    @Test
    void findAll() {
        Set<Vet> returnVetSet = new HashSet<>();
        returnVetSet.add(Vet.builder().build());
        returnVetSet.add(Vet.builder().build());

        when(vetRepository.findAll()).thenReturn(returnVetSet);

        Set<Vet> vets = service.findAll();
        assertNotNull(vets);
        assertEquals(2,vets.size());
    }

    @Test
    void findById() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(returnVet));

        Vet vet = service.findById(1L);
        assertNotNull(vet);
    }

    @Test
    void findByIdNoExistingId() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.empty());

        Vet vet = service.findById(1L);

        assertNull(vet);
    }

    @Test
    void save() {
        Vet vetToSave = Vet.builder().build();

        when(vetRepository.save(any())).thenReturn(vetToSave);

        Vet savedVet = service.save(vetToSave);

        assertNotNull(savedVet);

    }

    @Test
    void delete() {
        service.delete(returnVet);
        verify(vetRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(vetRepository).deleteById(anyLong());
    }
}