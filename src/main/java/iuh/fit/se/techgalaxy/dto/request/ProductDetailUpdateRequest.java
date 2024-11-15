package iuh.fit.se.techgalaxy.dto.request;

import iuh.fit.se.techgalaxy.entities.enumeration.ProductStatus;
import iuh.fit.se.techgalaxy.validate.DiscountConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailUpdateRequest {
    Double price;
    @DiscountConstraint(message = "PRODUCT_DISCOUNT_INVALID")
    Double sale;
    ProductStatus status;
    Integer quantity;
}
