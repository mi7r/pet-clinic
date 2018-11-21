package radek.spring.petclinic.services;

import radek.spring.petclinic.model.Owner;
import radek.spring.petclinic.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);

    Pet save(Owner pet);

    Set<Pet> findAll();
}
