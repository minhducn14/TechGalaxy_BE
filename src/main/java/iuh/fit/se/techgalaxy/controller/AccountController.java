package iuh.fit.se.techgalaxy.controller;


import iuh.fit.se.techgalaxy.exception.AppException;
import iuh.fit.se.techgalaxy.exception.ErrorCode;
import iuh.fit.se.techgalaxy.provider.TokenProvider;
import iuh.fit.se.techgalaxy.dto.request.*;
import iuh.fit.se.techgalaxy.dto.response.*;
import iuh.fit.se.techgalaxy.entities.Account;
import iuh.fit.se.techgalaxy.entities.Customer;
import iuh.fit.se.techgalaxy.entities.Role;
import iuh.fit.se.techgalaxy.entities.enumeration.CustomerStatus;
import iuh.fit.se.techgalaxy.mapper.RoleMapper;

import iuh.fit.se.techgalaxy.repository.RoleRepository;
import iuh.fit.se.techgalaxy.service.RoleService;
import iuh.fit.se.techgalaxy.service.impl.AccountServiceImpl;
import iuh.fit.se.techgalaxy.service.impl.CustomerServiceImpl;
import iuh.fit.se.techgalaxy.service.impl.SystemUserServiceImpl;
import iuh.fit.se.techgalaxy.service.impl.TokenServiceImpl;
import iuh.fit.se.techgalaxy.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final AccountServiceImpl accountService;
    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    private final RoleMapper roleMapper;

    private final CustomerServiceImpl customerService;

    private final SystemUserServiceImpl systemUserService;

    private final SecurityUtil securityUtil;
    private  final RoleRepository roleRepository;

    private final TokenServiceImpl tokenService;
    private final TokenProvider.TokenExtractor tokenExtractor;



    @Value("${jwt.refresh-token-validity-in-seconds}")
    private long refreshTokenExpiration;


    @Autowired
    public AccountController(AuthenticationManagerBuilder authenticationManagerBuilder,
                             AccountServiceImpl accountService,
                             PasswordEncoder passwordEncoder,
                             SecurityUtil securityUtil,
                             RoleService roleService,
                             CustomerServiceImpl customerService,
                             RoleMapper roleMapper,
                             SystemUserServiceImpl systemUserService,
                             RoleRepository roleRepository,
                             TokenServiceImpl tokenService,
                             TokenProvider.TokenExtractor tokenExtractor) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
        this.securityUtil = securityUtil;
        this.roleService = roleService;
        this.customerService = customerService;
        this.roleMapper = roleMapper;
        this.systemUserService = systemUserService;
        this.roleRepository = roleRepository;
        this.tokenService = tokenService;
        this.tokenExtractor = tokenExtractor;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<DataResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword());
        try {
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            LoginResponse res = new LoginResponse();
            Account currentUserDB = accountService.getAccountByEmail(loginDto.getUsername()).orElse(null);

            if (currentUserDB != null) {
                res.setAccount(new LoginResponse.AccountLogin(
                        currentUserDB.getId(),
                        currentUserDB.getEmail()
                ));
            }

            String accessToken = securityUtil.createAccessToken(authentication.getName(), res);
            res.setAccessToken(accessToken);

            System.out.println("Access token: " + accessToken);

            String refreshToken = securityUtil.createRefreshToken(loginDto.getUsername(), res);
            this.accountService.updateToken(refreshToken,loginDto.getUsername());
//            res.setAccount(null); // Xóa thông tin account khỏi response

            ResponseCookie resCookie = ResponseCookie
                    .from("refresh_token", refreshToken)
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(refreshTokenExpiration)
                    .build();

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, resCookie.toString())
                    .body(DataResponse.<LoginResponse>builder()
                            .status(200)
                            .message("Login successful")
                            .data(Collections.singletonList(res))
                            .build());
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(DataResponse.<LoginResponse>builder()
                            .status(401)
                            .message("Invalid username or password")
                            .build());
        }
    }

    @GetMapping("/auth/account")
    public ResponseEntity<DataResponse<LoginResponse.AccountGetAccount>> getAccount() {
        System.out.println("Get account");
        System.out.println(SecurityUtil.getCurrentUserLogin().orElse(null));
        String email = SecurityUtil.getCurrentUserLogin().orElse(null);
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(DataResponse.<LoginResponse.AccountGetAccount>builder()
                            .status(401)
                            .message("No user logged in")
                            .build());
        }

        Account currentAccount = accountService.getAccountByEmail(email).orElse(null);
        if (currentAccount == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(DataResponse.<LoginResponse.AccountGetAccount>builder()
                            .status(404)
                            .message("User not found")
                            .build());
        }

        LoginResponse.AccountGetAccount accountResponse = new LoginResponse.AccountGetAccount();
        accountResponse.setAccount(new LoginResponse.AccountLogin(
                currentAccount.getId(),
                currentAccount.getEmail()
        ));

        return ResponseEntity.ok(DataResponse.<LoginResponse.AccountGetAccount>builder()
                .status(200)
                .message("Account retrieved successfully")
                .data(Collections.singletonList(accountResponse))
                .build());
    }

    @PostMapping("/auth/register")
    public ResponseEntity<DataResponse<CustommerCreateResponse>> register(@Valid @RequestBody UserRegisterRequest user) {
        if (user.getEmail() == null || user.getEmail().isEmpty() || user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(DataResponse.<CustommerCreateResponse>builder()
                            .status(400)
                            .message("Email and password are required")
                            .build());
        }


        if (accountService.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(DataResponse.<CustommerCreateResponse>builder()
                            .status(409)
                            .message("Email already exists")
                            .build());
        }
        Account account = new Account();
        account.setPassword(passwordEncoder.encode(user.getPassword()));
        account.setEmail(user.getEmail());
        account.setRefreshToken("");

        RoleResponse roleResponse = roleService.findByName("Customer");
        Role role = roleMapper.toEntity(roleResponse);
        if (role == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(DataResponse.<CustommerCreateResponse>builder()
                            .status(500)
                            .message("Role not found")
                            .build());
        }
        account.setRoles(Collections.singletonList(role));
        Account newAccount = accountService.createAccount(account);
        System.out.println(newAccount);

        if (newAccount.getId() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(DataResponse.<CustommerCreateResponse>builder()
                            .status(500)
                            .message("Account creation failed")
                            .build());
        }

        Customer newCustomer = new Customer();
        newCustomer.setAccount(newAccount);
        newCustomer.setUserStatus(CustomerStatus.ACTIVE);
        newCustomer.setName(user.getFullName());

        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setName(user.getFullName());
        customerRequest.setAccount(newAccount);
        System.out.println(customerRequest.getAccount().getId());
        customerRequest.setUserStatus(CustomerStatus.ACTIVE);
        CustomerResponse customerResponse = this.customerService.save(customerRequest);

        CustommerCreateResponse response = new CustommerCreateResponse();
        response.setName(customerResponse.getName());

        return ResponseEntity.ok(DataResponse.<CustommerCreateResponse>builder()
                .status(200)
                .message("Account created successfully")
                .data(Collections.singletonList(response))
                .build());
    }

    @PostMapping("/auth/create-account")
    public ResponseEntity<DataResponse<UserRegisterResponse>> createAccount(@Valid @RequestBody UserRegisterRequest user) {
        if (user.getEmail() == null || user.getEmail().isEmpty() || user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(DataResponse.<UserRegisterResponse>builder()
                            .status(400)
                            .message("Email and password are required")
                            .build());
        }

        if (accountService.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(DataResponse.<UserRegisterResponse>builder()
                            .status(409)
                            .message("Email already exists")
                            .build());
        }
        Account account = new Account();
        account.setPassword(passwordEncoder.encode(user.getPassword()));
        account.setEmail(user.getEmail());
        account.setRefreshToken("");

        RoleResponse roleResponse = roleService.findByName("Customer");
        Role role = roleMapper.toEntity(roleResponse);
        if (role == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(DataResponse.<UserRegisterResponse>builder()
                            .status(500)
                            .message("Role not found")
                            .build());
        }
        account.setRoles(Collections.singletonList(role));
        Account newAccount = accountService.createAccount(account);
        System.out.println(newAccount);

        if (newAccount.getId() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(DataResponse.<UserRegisterResponse>builder()
                            .status(500)
                            .message("Account creation failed")
                            .build());
        }

        UserRegisterResponse response = new UserRegisterResponse();
        response.setEmail(newAccount.getEmail());
        response.setId(newAccount.getId());

        return ResponseEntity.ok(DataResponse.<UserRegisterResponse>builder()
                .status(200)
                .message("Account created successfully")
                .data(Collections.singletonList(response))
                .build());
    }

    @PostMapping("/auth/create-system-user")
    public ResponseEntity<DataResponse<SystemUserResponseDTO>> register(@Valid @RequestBody SystemUserRequestDTO user) {
        if (user.getAccount().getEmail() == null ||user.getAccount().getEmail().isEmpty() || user.getAccount().getPassword() == null || user.getAccount().getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(DataResponse.<SystemUserResponseDTO>builder()
                            .status(400)
                            .message("Email and password are required")
                            .build());
        }

        if (accountService.existsByEmail(user.getAccount().getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(DataResponse.<SystemUserResponseDTO>builder()
                            .status(409)
                            .message("Email already exists")
                            .build());
        }

        Account account = new Account();
        account.setPassword(passwordEncoder.encode(user.getAccount().getPassword()));
        account.setEmail(user.getAccount().getEmail());
        account.setRefreshToken("");

        List<Role> roles = user.getAccount().getRoles();

        if (roles == null || roles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(DataResponse.<SystemUserResponseDTO>builder()
                            .status(500)
                            .message("Role not found")
                            .build());
        }
        account.setRoles(roles);
        Account newAccount = accountService.createAccount(account);

        SystemUserResponseDTO.AccountResponse response = new SystemUserResponseDTO.AccountResponse();
        response.setEmail(newAccount.getEmail());
        SystemUserResponseDTO systemUserResponseDTO = systemUserService.handleCreateSystemUser(user);
//        systemUserResponseDTO.setAccount(response);

        return ResponseEntity.ok(DataResponse.<SystemUserResponseDTO>builder()
                .status(200)
                .message("Account created successfully")
                .data(Collections.singletonList(systemUserResponseDTO))
                .build());
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<DataResponse<String>> logout(HttpServletRequest request) {
        String email = SecurityUtil.getCurrentUserLogin().orElse("");

        if (email.isEmpty()) {
            throw new AppException(ErrorCode.NO_LOGIN);
        }

        System.out.println("Email: " + email);

        String token = tokenExtractor.extract(request);
        if (token == null) {
            throw new AppException(ErrorCode.NOT_IN_REQUEST);
        }

        System.out.println("Token: " + token);

        // Blacklist token
        tokenService.blacklistToken(token);

        // Update token in account
        this.accountService.updateToken("", email);

        // Clear refresh token cookie
        ResponseCookie resCookie = ResponseCookie
                .from("refresh_token", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();

        // Clear security context
        SecurityContextHolder.clearContext();

        // Return response
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, resCookie.toString())
                .body(DataResponse.<String>builder()
                        .status(200)
                        .message("Logout successful")
                        .data(Collections.singletonList("Logout successful"))
                        .build());
    }


    @PostMapping("/auth/refresh-token")
    public ResponseEntity<DataResponse<LoginResponse>> refreshToken(
            @CookieValue(name = "refresh_token", defaultValue = "abc") String refresh_token) {
        if (refresh_token == null || refresh_token.isEmpty() || refresh_token.equals("abc")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(DataResponse.<LoginResponse>builder()
                            .status(401)
                            .message("No refresh token found")
                            .build());
        }

        Jwt decodedToken = this.securityUtil.checkValidRefreshToken(refresh_token);
        String email = decodedToken.getSubject();


        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(DataResponse.<LoginResponse>builder()
                            .status(401)
                            .message("Invalid refresh token")
                            .build());
        }

        Account currentAccount = this.accountService.getAcountByRefreshTokenAndEmail(refresh_token, email);
        if (currentAccount == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(DataResponse.<LoginResponse>builder()
                            .status(404)
                            .message("User not found")
                            .build());
        }

        LoginResponse res = new LoginResponse();
        res.setAccount(new LoginResponse.AccountLogin(
                currentAccount.getId(),
                currentAccount.getEmail()
        ));

        String accessToken = securityUtil.createAccessToken(email, res);
        res.setAccessToken(accessToken);

        String new_refresh_token = securityUtil.createRefreshToken(email, res);
        this.accountService.updateToken(email, new_refresh_token);
        res.setAccount(null);
        ResponseCookie resCookie = ResponseCookie
                .from("refresh_token", new_refresh_token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(refreshTokenExpiration)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, resCookie.toString())
                .body(DataResponse.<LoginResponse>builder()
                        .status(200)
                        .message("Refresh token successful")
                        .data(Collections.singletonList(res))
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Void>> deleteAccount(@PathVariable String id) {

        accountService.deleteAccountById(id);
        return ResponseEntity.ok(DataResponse.<Void>builder()
                .status(200)
                .message("Account deleted successfully")
                .build());
    }

    @PutMapping
    public ResponseEntity<DataResponse<AccountUpdateResponse>> updateAccount(@RequestBody AccountUpdateRequest accountRequest) {
        Account account = accountService.getAccountById(accountRequest.getId()).orElse(null);
        if (account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(DataResponse.<AccountUpdateResponse>builder()
                            .status(404)
                            .message("Account not found")
                            .build());
        }
        String hashPass= passwordEncoder.encode(accountRequest.getPassword());
        accountRequest.setPassword(hashPass);

        AccountUpdateResponse updatedAccount = accountService.updateAccount(accountRequest);
        updatedAccount.setPassword(null);
        updatedAccount.setId(null);
        return ResponseEntity.ok(DataResponse.<AccountUpdateResponse>builder()
                .status(200)
                .message("Account updated successfully")
                .data(Collections.singletonList(updatedAccount))
                .build());
    }

    @GetMapping
    public ResponseEntity<DataResponse<List<Account>>> getAllAccounts() {
        List<Account> accounts = accountService.findAllAccounts();
        return ResponseEntity.ok(DataResponse.<List<Account>>builder()
                .status(200)
                .message("Accounts retrieved successfully")
                .data(List.of(accounts))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Account>> getAccountById(@PathVariable String id) {
        Account account = accountService.getAccountById(id).orElse(null);
        if (account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(DataResponse.<Account>builder()
                            .status(404)
                            .message("Account not found")
                            .build());
        }
        return ResponseEntity.ok(DataResponse.<Account>builder()
                .status(200)
                .message("Account retrieved successfully")
                .data(Collections.singletonList(account))
                .build());
    }
}