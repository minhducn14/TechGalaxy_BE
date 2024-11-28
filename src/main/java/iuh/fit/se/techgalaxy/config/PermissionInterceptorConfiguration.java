//package iuh.fit.se.techgalaxy.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class PermissionInterceptorConfiguration implements WebMvcConfigurer {
//    @Bean
//    PermissionInterceptor getPermissionInterceptor() {
//        return new PermissionInterceptor();
//    }
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
//                "/swagger-ui.html","/products/**", "/colors/**","/trademarks/**",
//                "/memories/**", "/usageCategories/**", "/attributes/**", "/product-feedbacks/**",
//                "/payment/**","/file","/files"
//        };
//        registry.addInterceptor(getPermissionInterceptor())
//                .excludePathPatterns(whiteList);
//    }
//}
