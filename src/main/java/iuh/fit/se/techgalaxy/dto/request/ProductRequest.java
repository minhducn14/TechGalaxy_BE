package iuh.fit.se.techgalaxy.dto.request;

import iuh.fit.se.techgalaxy.exception.ErrorCode;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
     @Size(min = 1, max = 20, message = "PRODUCT_NAME_INVALID")
     String name;
     String trademarkId;
}
