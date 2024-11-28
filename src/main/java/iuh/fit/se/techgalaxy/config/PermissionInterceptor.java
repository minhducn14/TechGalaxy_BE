package iuh.fit.se.techgalaxy.config;

import iuh.fit.se.techgalaxy.entities.Account;
import iuh.fit.se.techgalaxy.entities.Permission;
import iuh.fit.se.techgalaxy.entities.Role;
import iuh.fit.se.techgalaxy.exception.AppException;
import iuh.fit.se.techgalaxy.exception.ErrorCode;
import iuh.fit.se.techgalaxy.service.impl.AccountServiceImpl;
import iuh.fit.se.techgalaxy.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import java.util.List;

@RequiredArgsConstructor
public class PermissionInterceptor implements HandlerInterceptor {
    @Autowired
    private final AccountServiceImpl accountService;

    @Override
    @Transactional
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response, Object handler)
            throws Exception {

        String path = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String requestURI = request.getRequestURI();
        String httpMethod = request.getMethod();
        System.out.println(">>> RUN preHandle");
        System.out.println(">>> path= " + path);
        System.out.println(">>> httpMethod= " + httpMethod);
        System.out.println(">>> requestURI= " + requestURI);

        String email = SecurityUtil.getCurrentUserLogin().isPresent() == true
                ? SecurityUtil.getCurrentUserLogin().get()
                : "";
        if (email != null && !email.isEmpty()) {
            Account account = accountService.getAccountByEmail(email).get();
            if (account != null) {
                List<Role> roles = account.getRoles();
                if (roles != null) {
                    for (Role role : roles) {
                        System.out.println(">>> role= " + role.getName());
                        List<Permission> permissions = role.getPermissions();
                        boolean isAllow = permissions.stream().anyMatch(item -> item.getApiPath().equals(path)
                                && item.getMethod().equals(httpMethod));

                        if (isAllow == false) {
                            throw new AppException(ErrorCode.AUTHENTICATION_ERROR);
                        }
                    }
                } else {
                    throw new AppException(ErrorCode.AUTHENTICATION_ERROR);
                }
            } else {
                throw new AppException(ErrorCode.AUTHENTICATION_ERROR);
            }

        }
        return true;
    }
}
