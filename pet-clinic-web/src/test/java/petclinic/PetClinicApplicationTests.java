package petclinic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import radek.spring.petclinic.PetClinicApplication;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PetClinicApplication.class)
public class PetClinicApplicationTests {

    @Test
    public void contextLoads() {
    }

}
