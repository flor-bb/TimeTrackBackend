package ch.flowtech.TimeTrackBackend.service;

import ch.flowtech.TimeTrackBackend.model.User;
import ch.flowtech.TimeTrackBackend.security.UserPrincipal;
import ch.flowtech.TimeTrackBackend.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private final JwtRefreshTokenService jwtRefreshTokenService;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtProvider jwtProvider, JwtRefreshTokenService jwtRefreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.jwtRefreshTokenService = jwtRefreshTokenService;
    }

    @Override
    public User signInAndReturnJWT(User signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrincipal);

        User signInUser = userPrincipal.getUser();
        signInUser.setAccessToken(jwt);
        signInUser.setRefreshToken(jwtRefreshTokenService.createRefreshToken(signInUser.getId()).getTokenId());
        return signInUser;
    }
}