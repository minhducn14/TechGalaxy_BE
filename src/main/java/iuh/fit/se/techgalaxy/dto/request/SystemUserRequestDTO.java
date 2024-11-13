package iuh.fit.se.techgalaxy.dto.request;

import iuh.fit.se.techgalaxy.entities.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemUserRequestDTO {
    private String id;
    private String name;
    private String phone;
    private String address;
    private String systemUserStatus;
    private String level;
    private String gender;
    private String avatar;
    private AccountRequest account;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public  static  class  AccountRequest{
        private String email;
        private String password;
        private List<Role> roles;
    }
}
