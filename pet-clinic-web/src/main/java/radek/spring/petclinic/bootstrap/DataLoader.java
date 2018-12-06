package radek.spring.petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import radek.spring.petclinic.model.Owner;
import radek.spring.petclinic.model.PetType;
import radek.spring.petclinic.model.Vet;
import radek.spring.petclinic.services.OwnerService;
import radek.spring.petclinic.services.PetTypeService;
import radek.spring.petclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService){
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCatType = petTypeService.save(cat);

        System.out.println("Pet Type loaded...");

        Owner owner1 = new Owner();
        owner1.setFirstName("ryszard");
        owner1.setLastName("kalisz");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Krysztof");
        owner2.setLastName("Jerzyna");

        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Jacek");
        vet1.setLastName("Placek");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Kasia");
        vet2.setLastName("Bee");

        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }
}
