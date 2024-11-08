package iuh.fit.se.techgalaxy.config;


import java.util.Collections;
import java.util.List;

import iuh.fit.se.techgalaxy.entities.Account;
import iuh.fit.se.techgalaxy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;



@Component("userDetailsService")
public class UserDetailsCustom implements UserDetailsService {

    private final AccountService accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserDetailsCustom(AccountService accountService) {
        this.accountService = accountService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.getAccountByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username/password không hợp lệ"));

        if (account.getRole() == null) {
            throw new UsernameNotFoundException("Người dùng không có vai trò hợp lệ.");
        }

        String roleName = "ROLE_" + account.getRole().getName().toUpperCase();
        System.out.println(">>> Role name: " + roleName);
        System.out.println(">>> Password: " + account.getPassword());
        User user = new User(
                account.getEmail(),
                account.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(roleName)));
        System.out.println(">>> User: " + user);
        return user;
    }


}
