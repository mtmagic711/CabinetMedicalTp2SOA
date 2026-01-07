package ma.fsr.soa.patientserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// 1. Force Spring to look for Entities (Database Tables) in the repo library
@EntityScan(basePackages = "ma.fsr.soa.cabinetrepo.model")
// 2. Force Spring to look for Repositories (SQL Logic) in the repo library
@EnableJpaRepositories(basePackages = "ma.fsr.soa.cabinetrepo.repository")
public class PatientServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientServiceApiApplication.class, args);
    }

}
