package iuh.fit.se.techgalaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//disable security
// @SpringBootApplication(exclude = {
// 		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
// 		org
@SpringBootApplication
public class TechGalaxyApplication {
    public static void main(String[] args) {
        SpringApplication.run(TechGalaxyApplication.class, args);
    }
}
