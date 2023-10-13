package ch.flowtech.TimeTrackBackend.service;

import ch.flowtech.TimeTrackBackend.model.TimeEntry;
import ch.flowtech.TimeTrackBackend.model.User;
import jakarta.transaction.Transactional;

import java.time.Duration;
import java.time.LocalDate;

public interface TimeEntryService {
    @Transactional
    TimeEntry clockIn(User user);

    @Transactional
    TimeEntry clockOut(User user);

    Duration calculateWorkedHours(User user, LocalDate date);
}
