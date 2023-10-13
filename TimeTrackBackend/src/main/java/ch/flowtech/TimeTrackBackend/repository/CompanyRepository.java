package ch.flowtech.TimeTrackBackend.repository;

import ch.flowtech.TimeTrackBackend.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository  extends JpaRepository<Company, String> {
}
