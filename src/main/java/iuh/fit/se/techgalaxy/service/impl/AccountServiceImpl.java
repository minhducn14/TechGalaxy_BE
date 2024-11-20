package iuh.fit.se.techgalaxy.service.impl;

import iuh.fit.se.techgalaxy.dto.request.AccountUpdateRequest;
import iuh.fit.se.techgalaxy.dto.response.AccountUpdateResponse;
import iuh.fit.se.techgalaxy.entities.Account;
import iuh.fit.se.techgalaxy.entities.Role;
import iuh.fit.se.techgalaxy.mapper.AccountMapper;
import iuh.fit.se.techgalaxy.repository.AccountRepository;
import iuh.fit.se.techgalaxy.repository.RoleRepository;
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
    private final AccountRepository accountRepository;

    public final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> getAccountById(String id) {
        return accountRepository.findById(id);
    }

    @Override
    public AccountUpdateResponse updateAccount(AccountUpdateRequest account) {
        Account existingAccount = accountRepository.findById(account.getId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));


        if (account.getEmail() != null && !account.getEmail().isEmpty()){
            if (existingAccount.getEmail().equals(account.getEmail())) {

            } else if(accountRepository.existsByEmail(account.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
        }
        existingAccount.setEmail(account.getEmail());

        List<String> rolesIds = existingAccount.getRoles().stream().map(Role::getId).toList();
        rolesIds.forEach(roleId -> {
            System.out.println(roleId);
        });
        if (account.getRolesIds() != null) {
            List<Role> newRoles = roleRepository.findAllById(account.getRolesIds());
            newRoles.forEach(role -> {
                System.out.println(role.getName());
            });
            existingAccount.getRoles().clear();
            existingAccount.getRoles().addAll(newRoles);
        }

        Account updatedAccount = accountRepository.save(existingAccount);
        return accountMapper.toAccountResponse(updatedAccount);
    }

    @Override
    public boolean deleteAccountById(String id) {
        if (!accountRepository.existsById(id)) {
            return false;
        }
        if (accountRepository.existsById(id)) {
            Account account = accountRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Account not found"));
            account.getRoles().clear();
            accountRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Account> findAllSystemUserAccounts() {
        return accountRepository.findAllSystemUserAccounts();
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
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        account.setRefreshToken(token);
        accountRepository.save(account);
    }

    @Override
    public Account getAcountByRefreshTokenAndEmail(String token, String email) {
        return accountRepository.findByRefreshTokenAndEmail(token, email)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
    }

    @Override
    public boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }


}
