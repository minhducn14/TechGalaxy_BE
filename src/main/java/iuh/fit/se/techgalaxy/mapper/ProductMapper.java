package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.response.ProductResponse;
import iuh.fit.se.techgalaxy.entities.Customer;
import iuh.fit.se.techgalaxy.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public static ProductResponse toProductResponse(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getTrademark()
        );
    }
}
