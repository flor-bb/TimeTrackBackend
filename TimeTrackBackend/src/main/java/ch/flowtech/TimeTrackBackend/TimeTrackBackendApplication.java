package ch.flowtech.TimeTrackBackend;

import ch.flowtech.TimeTrackBackend.model.Company;
import ch.flowtech.TimeTrackBackend.model.Role;
import ch.flowtech.TimeTrackBackend.model.User;
import ch.flowtech.TimeTrackBackend.model.WorkedHours;
import ch.flowtech.TimeTrackBackend.repository.CompanyRepository;
import ch.flowtech.TimeTrackBackend.repository.WorkedHoursRepository;
import ch.flowtech.TimeTrackBackend.service.UserServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;


@SpringBootApplication
public class TimeTrackBackendApplication {

    private final UserServiceImpl userServiceImplementation;

    private final CompanyRepository companyRepository;

    private final WorkedHoursRepository workedHoursRepository;

    @Autowired
    public TimeTrackBackendApplication(UserServiceImpl userServiceImplementation, CompanyRepository companyRepository, WorkedHoursRepository workedHoursRepository) {
        this.userServiceImplementation = userServiceImplementation;
        this.companyRepository = companyRepository;
        this.workedHoursRepository = workedHoursRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TimeTrackBackendApplication.class, args);
    }

    @PostConstruct
    public void initSampleData() {
        // Create sample company
        Company sampleCompany = new Company();
        sampleCompany.setName("Sample Corp");
        sampleCompany.setAddress("1234 Sample St.");
        sampleCompany.setCreateTime(LocalDateTime.now());
        sampleCompany.setUpdateTime(LocalDateTime.now());
        companyRepository.save(sampleCompany);

        // Create sample user
        User sampleUser = new User();
        sampleUser.setName("John Doe");
        sampleUser.setUsername("admin");
        sampleUser.setPassword("admin");
        sampleUser.setCreateTime(LocalDateTime.now());
        sampleUser.setUpdateTime(LocalDateTime.now());
        sampleUser.setCompany(sampleCompany);
        sampleUser.setClockedIn(false);
        userServiceImplementation.saveUser(sampleUser);
        userServiceImplementation.changeRole(Role.ADMIN, sampleUser.getUsername());

        // Create sample worked hours for the user
        WorkedHours workedHours = new WorkedHours();
        workedHours.setUser(sampleUser);
        workedHours.setCompany(sampleCompany);
        workedHours.setDate(LocalDate.now());
        workedHours.setHoursWorked(8.5);
        workedHours.setCreateTime(LocalDateTime.now());
        workedHours.setUpdateTime(LocalDateTime.now());
        workedHoursRepository.save(workedHours);
    }
}


