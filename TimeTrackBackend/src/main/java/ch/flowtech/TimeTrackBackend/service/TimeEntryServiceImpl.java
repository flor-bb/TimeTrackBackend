package ch.flowtech.TimeTrackBackend.service;

import ch.flowtech.TimeTrackBackend.model.TimeEntry;
import ch.flowtech.TimeTrackBackend.model.User;
import ch.flowtech.TimeTrackBackend.repository.TimeEntryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class TimeEntryServiceImpl implements TimeEntryService {

    private final TimeEntryRepository timeEntryRepository;

    private final UserService userService;

    @Autowired
    public TimeEntryServiceImpl(TimeEntryRepository timeEntryRepository, UserService userService) {
        this.timeEntryRepository = timeEntryRepository;
        this.userService = userService;
    }

    @Transactional
    @Override
    public TimeEntry clockIn(User user) {
        if (user.isClockedIn()) {
            return null;
        }
        TimeEntry entry = new TimeEntry();
        user.setClockedIn(true);
        userService.saveUser(user);
        entry.setUser(user);
        entry.setTimestamp(LocalDateTime.now());
        entry.setEntryType(TimeEntry.EntryType.CLOCK_IN);
        return timeEntryRepository.save(entry);
    }

    @Transactional
    @Override
    public TimeEntry clockOut(User user) {
        if (!user.isClockedIn()) {
            return null;
        }
        TimeEntry entry = new TimeEntry();
        user.setClockedIn(false);
        userService.saveUser(user);
        entry.setUser(user);
        entry.setTimestamp(LocalDateTime.now());
        entry.setEntryType(TimeEntry.EntryType.CLOCK_OUT);
        return timeEntryRepository.save(entry);
    }

    @Override
    public Duration calculateWorkedHours(User user, LocalDate date) {
        List<TimeEntry> entries = timeEntryRepository.findByUserAndTimestampBetween(
                user,
                date.atStartOfDay(),
                date.plusDays(1).atStartOfDay()
        );

        // Sort entries by timestamp
        entries.sort(Comparator.comparing(TimeEntry::getTimestamp));

        Duration totalDuration = Duration.ZERO;
        LocalDateTime clockInTime = null;

        for (TimeEntry entry : entries) {
            if (entry.getEntryType() == TimeEntry.EntryType.CLOCK_IN) {
                clockInTime = entry.getTimestamp();
            } else if (entry.getEntryType() == TimeEntry.EntryType.CLOCK_OUT && clockInTime != null) {
                Duration duration = Duration.between(clockInTime, entry.getTimestamp());
                totalDuration = totalDuration.plus(duration);
                clockInTime = null; // Reset clock in time for next cycle
            }
        }

        return totalDuration;
    }

}