package iuh.fit.se.techgalaxy.dto.request;

import iuh.fit.se.techgalaxy.entities.Order;
import iuh.fit.se.techgalaxy.entities.ProductVariantDetail;
import iuh.fit.se.techgalaxy.entities.enumeration.DetailStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailRequest {
    String id;
    DetailStatus detailStatus;
    Order order;
    ProductVariantDetail productVariantDetail;
    Integer quantity;
    Double price;
}
