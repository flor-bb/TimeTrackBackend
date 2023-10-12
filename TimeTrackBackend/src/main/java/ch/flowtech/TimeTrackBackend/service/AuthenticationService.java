package ch.flowtech.TimeTrackBackend.service;

import ch.flowtech.TimeTrackBackend.model.User;

public interface AuthenticationService {
    User signInAndReturnJWT(User signInRequest);
}
