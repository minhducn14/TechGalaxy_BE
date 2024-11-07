package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.request.ProductVariantRequest;
import iuh.fit.se.techgalaxy.dto.response.ProductVariantResponse;
import iuh.fit.se.techgalaxy.entities.ProductVariant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {
    ProductVariantResponse toProductVariantResponse(ProductVariant productVariant);

    ProductVariant toProductVariant(ProductVariantRequest productVariantRequest);
}
