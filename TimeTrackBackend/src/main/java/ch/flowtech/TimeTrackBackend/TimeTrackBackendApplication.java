package ch.flowtech.TimeTrackBackend;

import ch.flowtech.TimeTrackBackend.repository.UserRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class TimeTrackBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeTrackBackendApplication.class, args);
	}

//	//get the bean of entityManagerFactory
//	@Bean
//	public EntityManagerFactory entityManagerFactory() {
//		return Persistence.createEntityManagerFactory("ch.flowtech.TimeTrackBackend.model");
//	}

}
