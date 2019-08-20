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
    public void run(String... args) {

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

        PetType hamster = new PetType();
        hamster.setName("Hamster");
        PetType saveHamsterType = petTypeService.save(hamster);

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

        Owner bilboBaggins = new Owner();
        bilboBaggins.setFirstName("Bilbo");
        bilboBaggins.setLastName("Baggins");
        bilboBaggins.setAddress("123 Street");
        bilboBaggins.setCity("Miami");
        bilboBaggins.setTelephone("123123123");


        Pet trex = new Pet();
        trex.setPetType(saveDogType);
        trex.setOwner(bilboBaggins);
        trex.setBirthDate(LocalDate.now());
        trex.setName("Trex");
        bilboBaggins.getPets().add(trex);


        ownerService.save(bilboBaggins);

        Owner quentinTarantino = new Owner();
        quentinTarantino.setFirstName("Quentin");
        quentinTarantino.setLastName("Tarantino");
        quentinTarantino.setAddress("312 Street");
        quentinTarantino.setCity("Mui Ne");
        quentinTarantino.setTelephone("999888777");

        Pet klakier = new Pet();
        klakier.setName("Klakier");
        klakier.setOwner(quentinTarantino);
        klakier.setBirthDate(LocalDate.now());
        klakier.setPetType(saveCatType);
        quentinTarantino.getPets().add(klakier);

        ownerService.save(quentinTarantino);

        Visit catVisit = new Visit();
        catVisit.setPet(klakier);
        catVisit.setDate(LocalDate.now().minusYears(1).minusMonths(2));
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);

        System.out.println("Loaded owners...");

        Vet jamieFoxx = new Vet();
        jamieFoxx.setFirstName("Jamie");
        jamieFoxx.setLastName("Foxx");
        jamieFoxx.getSpecialities().add(savedRadiology);

        vetService.save(jamieFoxx);

        Vet kerryWashington = new Vet();
        kerryWashington.setFirstName("Kerry");
        kerryWashington.setLastName("Washington");
        kerryWashington.getSpecialities().add(savedSurgery);

        vetService.save(kerryWashington);

        System.out.println("Loaded vets...");
    }
}
