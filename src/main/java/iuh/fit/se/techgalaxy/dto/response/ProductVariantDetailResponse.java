package iuh.fit.se.techgalaxy.dto.response;

import iuh.fit.se.techgalaxy.entities.ProductsImage;
import iuh.fit.se.techgalaxy.entities.enumeration.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariantDetailResponse {
    String id;
    String name;
    Integer viewsCount;
    Integer quantity;
    Double price;
    Double sale;
    ProductStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    ProductsImage productsImage;
    private Map<String, String[]> memories;
}
