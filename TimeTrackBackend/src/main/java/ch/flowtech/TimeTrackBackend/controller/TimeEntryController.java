package ch.flowtech.TimeTrackBackend.controller;


import ch.flowtech.TimeTrackBackend.model.TimeEntry;
import ch.flowtech.TimeTrackBackend.model.User;
import ch.flowtech.TimeTrackBackend.security.UserPrincipal;
import ch.flowtech.TimeTrackBackend.service.TimeEntryService;
import ch.flowtech.TimeTrackBackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/time-entries")
public class TimeEntryController {

    private final TimeEntryService timeEntryService;

    private final UserService userService;

    public TimeEntryController(TimeEntryService timeEntryService, UserService userService) {
        this.timeEntryService = timeEntryService;
        this.userService = userService;
    }

    @PostMapping("clock-in")
    public ResponseEntity<TimeEntry> clockIn(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Optional<User> user = userService.findUserByUsername(userPrincipal.getUsername());
        if (user.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        TimeEntry clockedInEntry = timeEntryService.clockIn(user.get());
        return new ResponseEntity<>(clockedInEntry, HttpStatus.CREATED);
    }

    @PostMapping("clock-out")
    public ResponseEntity<TimeEntry> clockOut(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Optional<User> user = userService.findUserByUsername(userPrincipal.getUsername());
        if (user.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        TimeEntry clockedOutEntry = timeEntryService.clockOut(user.get());
        return new ResponseEntity<>(clockedOutEntry, HttpStatus.CREATED);
    }

}
