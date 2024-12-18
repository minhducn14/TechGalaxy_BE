package iuh.fit.se.techgalaxy.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PermissionInterceptorConfiguration implements WebMvcConfigurer {

    PermissionInterceptor permissionInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] whiteList = {
                "/",
                "/api/accounts/auth/register", "/api/accounts/auth/login",
                "/api/accounts/auth/logout", "/api/accounts/auth/account",
                "/storage/**",
                "/v3/api-docs/**","/swagger-ui/**",
                "/swagger-ui.html","/colors/**",
                "/memories/**", "/usageCategories/**", "/attributes/**",
                "/payment/**","/file","/files",
                "/email","/email/**",
        };
        registry.addInterceptor(this.permissionInterceptor)
                .excludePathPatterns(whiteList);
    }
}
