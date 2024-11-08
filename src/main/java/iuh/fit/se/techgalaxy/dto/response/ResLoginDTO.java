package iuh.fit.se.techgalaxy.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import iuh.fit.se.techgalaxy.entities.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ResLoginDTO {
    @JsonProperty("access_token")
    private String accessToken;

    private AccountLogin account;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AccountLogin {
        private String id;
        private String email;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AccountGetAccount {
        private AccountLogin account;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AccountInsideToken {
        private String id;
        private String email;

    }

}
