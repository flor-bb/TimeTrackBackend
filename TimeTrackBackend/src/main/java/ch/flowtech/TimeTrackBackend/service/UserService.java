package ch.flowtech.TimeTrackBackend.service;

import ch.flowtech.TimeTrackBackend.model.User;
import ch.flowtech.TimeTrackBackend.model.Role;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findUserById(Long id);
    User saveUser(User user);
    Optional<User> findUserByUsername(String username);
    void changeRole(Role newRole, String username);
    List<User> findAllUsers();
    void changeClockedIn(Boolean clockedIn, String username);
}
