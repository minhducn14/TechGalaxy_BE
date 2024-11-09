package iuh.fit.se.techgalaxy.dto.request;

import iuh.fit.se.techgalaxy.entities.enumeration.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariantDetailRequest {
    Integer viewsCount = 0;
    Double price;
    Double sale;
    ProductStatus status;
    List<MemoryColorRequest> memoryColorRequests;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class MemoryColorRequest {
        int quantity;
        String memoryId;
        String colorId;
    }
}
