package ch.flowtech.TimeTrackBackend.repository;

import ch.flowtech.TimeTrackBackend.model.WorkedHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkedHoursRepository  extends JpaRepository<WorkedHours, String> {

}
