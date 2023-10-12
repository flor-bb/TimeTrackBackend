package ch.flowtech.TimeTrackBackend.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "jwt_refresh_token")
public class JwtRefreshToken {

    @Id
    @Column(name = "token_id", nullable = false)
    private String tokenId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;


    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}
