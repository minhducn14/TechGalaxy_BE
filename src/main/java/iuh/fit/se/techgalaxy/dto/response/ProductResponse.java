package iuh.fit.se.techgalaxy.dto.response;

import iuh.fit.se.techgalaxy.entities.Trademark;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private String id;
    private String name;
    private Trademark trademark;
}
