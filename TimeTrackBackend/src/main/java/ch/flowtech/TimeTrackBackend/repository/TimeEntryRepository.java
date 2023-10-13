package ch.flowtech.TimeTrackBackend.repository;

import ch.flowtech.TimeTrackBackend.model.TimeEntry;
import ch.flowtech.TimeTrackBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TimeEntryRepository extends JpaRepository<TimeEntry, Long> {

    List<TimeEntry> findByUserAndTimestampBetween(User user, LocalDateTime startTimestamp, LocalDateTime endTimestamp);

}
