package iuh.fit.se.techgalaxy.dto.request;

import iuh.fit.se.techgalaxy.entities.Account;
import iuh.fit.se.techgalaxy.entities.enumeration.CustomerStatus;
import iuh.fit.se.techgalaxy.entities.enumeration.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequest {
    String id;
    CustomerStatus userStatus;
    String name;
    String address;
    String phone;
    Gender gender;
    String avatar;
    LocalDateTime dateOfBirth;
    Account account;
}
