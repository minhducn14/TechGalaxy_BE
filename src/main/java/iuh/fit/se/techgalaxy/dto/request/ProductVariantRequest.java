package iuh.fit.se.techgalaxy.dto.request;

import iuh.fit.se.techgalaxy.entities.enumeration.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariantRequest {
    String name;
    String description;
    String content;
    String avatar;
    Boolean featured;
    ProductStatus status;
    String usageCategoryId;
}
