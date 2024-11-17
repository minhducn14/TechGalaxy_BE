package iuh.fit.se.techgalaxy.service.impl;



import iuh.fit.se.techgalaxy.entities.BlacklistedToken;
import iuh.fit.se.techgalaxy.repository.BlacklistedTokenRepository;
import iuh.fit.se.techgalaxy.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
@Service
public class TokenServiceImpl implements TokenService {

    private final BlacklistedTokenRepository blacklistedTokenRepository;
    @Autowired
    public TokenServiceImpl(BlacklistedTokenRepository blacklistedTokenRepository) {
        this.blacklistedTokenRepository = blacklistedTokenRepository;
    }
    @Override
    @Transactional
    public void blacklistToken(String token) {
        if (blacklistedTokenRepository.existsByToken(token)) {
            System.out.println("Token already blacklisted: " + token);
            return;
        }

        BlacklistedToken blacklistedToken = new BlacklistedToken();
        blacklistedToken.setToken(token);
        blacklistedToken.setExpiryDate(Instant.now());
        blacklistedTokenRepository.save(blacklistedToken);
    }

    @Override
    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokenRepository.existsByToken(token);
    }

    @Override
    @Scheduled(cron = "0 0 3 1/2 * ?")
    @Transactional
    public void cleanExpiredTokens() {
        blacklistedTokenRepository.deleteByExpiryDateBefore(Instant.now());
    }
}