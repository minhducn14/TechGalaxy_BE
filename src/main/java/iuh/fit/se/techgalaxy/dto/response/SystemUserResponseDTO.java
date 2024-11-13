package iuh.fit.se.techgalaxy.dto.response;

import iuh.fit.se.techgalaxy.entities.Account;
import iuh.fit.se.techgalaxy.entities.Order;
import iuh.fit.se.techgalaxy.entities.Role;
import iuh.fit.se.techgalaxy.entities.enumeration.Gender;
import iuh.fit.se.techgalaxy.entities.enumeration.SystemUserLevel;
import iuh.fit.se.techgalaxy.entities.enumeration.SystemUserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemUserResponseDTO {

    private String id;
    private String systemUserStatus;
    private String level;
    private String name;
    private String address;
    private String phone;
    private String gender;
    private String avatar;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private AccountResponse account;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AccountResponse {
        private String email;
        private List<Role> roles;
    }
}
