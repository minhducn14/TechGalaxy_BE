package iuh.fit.se.techgalaxy.service;


public interface TokenService {

    void blacklistToken(String token);

    boolean isTokenBlacklisted(String token);

    void cleanExpiredTokens();
}