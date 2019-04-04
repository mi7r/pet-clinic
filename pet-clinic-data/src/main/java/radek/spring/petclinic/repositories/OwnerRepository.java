package radek.spring.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import radek.spring.petclinic.model.Owner;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
