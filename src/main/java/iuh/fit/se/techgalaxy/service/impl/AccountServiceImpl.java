package iuh.fit.se.techgalaxy.service.impl;

import iuh.fit.se.techgalaxy.entities.Account;
import iuh.fit.se.techgalaxy.repository.AccountRepository;
import iuh.fit.se.techgalaxy.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired

    private PasswordEncoder passwordEncoder;

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> getAccountById(String id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account updateAccount(Account account) {
        Account existingAccount = accountRepository.findById(account.getId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        if (account.getPassword() != null && !account.getPassword().isEmpty()) {
            existingAccount.setPassword(account.getPassword());
        }

        existingAccount.setEmail(account.getEmail());
        existingAccount.setRoles(account.getRoles());

        return accountRepository.save(existingAccount);
    }

    @Override
    public boolean deleteAccountById(String id) {
        if (!accountRepository.existsById(id)) {
            return false;
        }
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findAccountsByCriteria(Specification<Account> spec) {
        return accountRepository.findAll(spec);
    }

    @Override
    public Optional<Account> getAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }


    @Override
    public void resetPassword(String id, String newPassword) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        account.setPassword(newPassword);
        accountRepository.save(account);
    }

    @Override
    public boolean validateAccount(String email, String password) {
        Optional<Account> accountOpt = accountRepository.findByEmail(email);
        return accountOpt.map(account -> passwordEncoder.matches(password, account.getPassword())).orElse(false);
    }

    @Override
    public void updateToken(String token, String email) {
        System.out.println(email + " " + token);
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        account.setRefreshToken(token);
        accountRepository.save(account);
    }

    @Override
    public Account getAcountByRefreshTokenAndEmail(String token, String email) {
        return accountRepository.findByRefreshTokenAndEmail(token, email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }


}
