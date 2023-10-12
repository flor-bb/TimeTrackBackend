package ch.flowtech.TimeTrackBackend.controller;

import ch.flowtech.TimeTrackBackend.model.User;
import ch.flowtech.TimeTrackBackend.service.AuthenticationService;
import ch.flowtech.TimeTrackBackend.service.JwtRefreshTokenService;
import ch.flowtech.TimeTrackBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/authentication") // pre path
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final JwtRefreshTokenService jwtRefreshTokenService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, UserService userService, JwtRefreshTokenService jwtRefreshTokenService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.jwtRefreshTokenService = jwtRefreshTokenService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("sign-up") //api/authentication/sign-up
    public ResponseEntity<?> signUp(@RequestBody User user) {

        if (userService.findUserByUsername(user.getUsername()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("sign-in")//api/authentication/sign-in
    public ResponseEntity<?> signIn(@RequestBody User user) {
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("refresh-token") //api/authentication/refresh-token
    public ResponseEntity<?> refreshToken(@RequestParam String token) {
        return ResponseEntity.ok(jwtRefreshTokenService.generateAccessTokenFromRefreshToken(token));
    }


}
