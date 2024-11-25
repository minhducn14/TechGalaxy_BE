package iuh.fit.se.techgalaxy.dto.response;

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
    ProductStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Map<String, ColorQuantity[]> memories;
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ColorQuantity {
        Integer viewsCount;
        Double price;
        Double sale;
        Integer quantity;
        String colorId;
    }
}
