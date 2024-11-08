package iuh.fit.se.techgalaxy.controller;


import iuh.fit.se.techgalaxy.dto.request.LoginRequest;
import iuh.fit.se.techgalaxy.dto.response.ResCreateAccountDTO;
import iuh.fit.se.techgalaxy.dto.response.LoginResponse;
import iuh.fit.se.techgalaxy.entities.Account;
import iuh.fit.se.techgalaxy.service.AccountService;

import iuh.fit.se.techgalaxy.util.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    private final SecurityUtil securityUtil;

    @Value("${jwt.refresh-token-validity-in-seconds}")
    private long refreshTokenExpiration;


    public AccountController(AuthenticationManagerBuilder authenticationManagerBuilder,
                             AccountService accountService,
                             PasswordEncoder passwordEncoder,
                             SecurityUtil securityUtil) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
        this.securityUtil = securityUtil;
    }


    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword());
        try {
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            LoginResponse res = new LoginResponse();

            Account currentUserDB = this.accountService.getAccountByEmail(loginDto.getUsername()).orElse(null);

            if (currentUserDB != null) {
                LoginResponse.AccountLogin accountLogin = new LoginResponse.AccountLogin(
                        currentUserDB.getId(),
                        currentUserDB.getEmail());

                res.setAccount(accountLogin);
            }

            // create access token
            String access_token = this.securityUtil.createAccessToken(authentication.getName(), res);
            res.setAccessToken(access_token);

            // create refresh token
            String refresh_token = this.securityUtil.createRefreshToken(loginDto.getUsername(), res);
            res.setAccount(null);

            ResponseCookie resCookies = ResponseCookie
                    .from("refresh_token", refresh_token)
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(refreshTokenExpiration)
                    .build();

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, resCookies.toString())
                    .body(res);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }


    @GetMapping("/auth/account")
    public ResponseEntity<LoginResponse.AccountGetAccount> getAccount() {
        String email = SecurityUtil.getCurrentUserLogin().isPresent()
                ? SecurityUtil.getCurrentUserLogin().get()
                : "";

        Account accountCurrnetDB = this.accountService.getAccountByEmail(email).orElse(null);
        LoginResponse.AccountLogin accountLogin = new LoginResponse.AccountLogin();
        LoginResponse.AccountGetAccount accountGetAccount = new LoginResponse.AccountGetAccount();

        if (accountCurrnetDB != null) {
            accountLogin.setId(accountCurrnetDB.getId());
            accountLogin.setEmail(accountCurrnetDB.getEmail());
            accountGetAccount.setAccount(accountLogin);
        }

        return ResponseEntity.ok().body(accountGetAccount);
    }


    @PostMapping("/auth/register")
    public ResponseEntity<ResCreateAccountDTO> register(@Valid @RequestBody Account account) {
        boolean isEmailExist = this.accountService.getAccountByEmail(account.getEmail()).isPresent();

        if (isEmailExist) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        String hashPassword = this.passwordEncoder.encode(account.getPassword());
        account.setPassword(hashPassword);

        Account account1 = this.accountService.createAccount(account);
        if (account1 == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            ResCreateAccountDTO resCreateAccountDTO = new ResCreateAccountDTO();
            resCreateAccountDTO.setId(account1.getId());
            resCreateAccountDTO.setEmail(account1.getEmail());
            resCreateAccountDTO.setPassword(account1.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(resCreateAccountDTO);
        }

    }
}