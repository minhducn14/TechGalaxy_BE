package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.response.ProductResponse;
import iuh.fit.se.techgalaxy.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toProductResponse(Product product);
}
