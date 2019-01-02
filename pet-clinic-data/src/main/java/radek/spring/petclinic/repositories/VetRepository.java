package radek.spring.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import radek.spring.petclinic.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
