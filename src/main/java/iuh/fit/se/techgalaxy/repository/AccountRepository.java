package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>, JpaSpecificationExecutor<Account> {


    Optional<Account> findByEmail(String email);

}