package radek.spring.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import radek.spring.petclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
