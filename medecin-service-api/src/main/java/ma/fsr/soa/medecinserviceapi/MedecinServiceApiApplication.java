package ma.fsr.soa.medecinserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "ma.fsr.soa.cabinetrepo.model")
@EnableJpaRepositories(basePackages = "ma.fsr.soa.cabinetrepo.repository")
public class MedecinServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedecinServiceApiApplication.class, args);
	}

}
