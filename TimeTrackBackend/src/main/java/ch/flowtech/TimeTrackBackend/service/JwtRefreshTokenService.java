package ch.flowtech.TimeTrackBackend.service;

import ch.flowtech.TimeTrackBackend.model.JwtRefreshToken;
import ch.flowtech.TimeTrackBackend.model.User;

public interface JwtRefreshTokenService {
    JwtRefreshToken createRefreshToken(Long userId);

    User generateAccessTokenFromRefreshToken(String refreshTokenId);
}
