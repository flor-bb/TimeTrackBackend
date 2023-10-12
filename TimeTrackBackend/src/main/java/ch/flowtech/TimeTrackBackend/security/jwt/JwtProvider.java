package ch.flowtech.TimeTrackBackend.security.jwt;

import ch.flowtech.TimeTrackBackend.security.UserPrincipal;
import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletRequest;

public interface JwtProvider {

    String generateToken(UserPrincipal auth);

    Authentication getAuthentication(HttpServletRequest request);

    boolean isTokenValid(HttpServletRequest request);
}
