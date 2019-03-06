package radek.spring.petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import radek.spring.petclinic.model.*;
import radek.spring.petclinic.services.*;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCatType = petTypeService.save(cat);

        System.out.println("Pet Type loaded...");

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialtyService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialtyService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Ryszard");
        owner1.setLastName("Kalisz");
        owner1.setAddress("123 Street");
        owner1.setCity("Gdynia");
        owner1.setCity("123456789");


        Pet richsDog = new Pet();
        richsDog.setPetType(saveDogType);
        richsDog.setOwner(owner1);
        richsDog.setBirthDate(LocalDate.now());
        richsDog.setName("Loco");
        owner1.getPets().add(richsDog);


        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Krysztof");
        owner2.setLastName("Jerzyna");
        owner2.setAddress("312 Street");
        owner2.setCity("Gdanks");
        owner2.setCity("123123456");

        Pet krisCat = new Pet();
        krisCat.setName("Klakier");
        krisCat.setOwner(owner2);
        krisCat.setBirthDate(LocalDate.now());
        krisCat.setPetType(saveCatType);
        owner2.getPets().add(krisCat);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(krisCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Jacek");
        vet1.setLastName("Placek");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Kasia");
        vet2.setLastName("Bee");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }
}
