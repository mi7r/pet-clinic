package radek.spring.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import radek.spring.petclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
