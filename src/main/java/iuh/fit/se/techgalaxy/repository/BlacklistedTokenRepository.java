package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.BlacklistedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.Instant;

@RepositoryRestResource(collectionResourceRel = "blacklisted_tokens", path = "blacklisted_tokens", exported = false)
public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken, String> {
    boolean existsByToken(String token);

    void deleteByExpiryDateBefore(Instant now);
}
