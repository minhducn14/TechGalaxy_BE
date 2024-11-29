//package iuh.fit.se.techgalaxy.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@RequiredArgsConstructor
//public class PermissionInterceptorConfiguration implements WebMvcConfigurer {
//
//    private final PermissionInterceptor permissionInterceptor;
//
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        String[] whiteList = {
//                "/",
//                "/api/accounts/auth/register", "/api/accounts/auth/login",
//                "/api/accounts/auth/logout", "/api/accounts/auth/account",
//                "/storage/**",
//                "/v3/api-docs/**","/swagger-ui/**",
//                "/swagger-ui.html","/colors/**","/trademarks/**",
//                "/memories/**", "/usageCategories/**", "/attributes/**", "/product-feedbacks/**",
//                "/payment/**","/file","/files"
//        };
//        registry.addInterceptor(this.permissionInterceptor)
//                .excludePathPatterns(whiteList);
//    }
//}
