package iuh.fit.se.techgalaxy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisteRequest {
    private String email;
    private String password;
    private String fullName;
}
