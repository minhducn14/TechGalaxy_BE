package iuh.fit.se.techgalaxy.dto.response;

import iuh.fit.se.techgalaxy.entities.enumeration.Gender;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustommerCreateResponse {
    private String id;
    private String name;
    private String email;
    private Gender gender;
    private String address;
    private int age;
    private LocalDateTime createdAt;

}
