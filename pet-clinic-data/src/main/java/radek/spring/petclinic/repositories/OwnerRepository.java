package radek.spring.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import radek.spring.petclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

}
