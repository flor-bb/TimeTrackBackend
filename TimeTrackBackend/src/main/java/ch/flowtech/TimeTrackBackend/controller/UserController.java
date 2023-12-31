package ch.flowtech.TimeTrackBackend.controller;


import ch.flowtech.TimeTrackBackend.model.Role;
import ch.flowtech.TimeTrackBackend.security.UserPrincipal;
import ch.flowtech.TimeTrackBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user") //pre-path
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("change/role/{role}") // api/user/change/{role}
    public ResponseEntity<?> changeRole(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Role role) {
        userService.changeRole(role, userPrincipal.getUsername());
        return ResponseEntity.ok(true);
    }

    @PutMapping("change/clocked-in/{clockedIn}") // api/user/change/{clockedIn}
    public ResponseEntity<?> changeClockedIn(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Boolean clockedIn) {
        userService.changeClockedIn(clockedIn, userPrincipal.getUsername());
        return ResponseEntity.ok(true);
    }
}
