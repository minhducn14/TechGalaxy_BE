package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.request.ProductVariantDetailRequest;
import iuh.fit.se.techgalaxy.dto.response.ProductVariantDetailResponse;
import iuh.fit.se.techgalaxy.entities.Color;
import iuh.fit.se.techgalaxy.entities.Memory;
import iuh.fit.se.techgalaxy.entities.ProductVariant;
import iuh.fit.se.techgalaxy.entities.ProductVariantDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductVariantDetailMapper {
    @Mapping(target = "memories", expression = "java(mapMemories(productVariantDetails))")
    ProductVariantDetailResponse toProductVariantDetailResponse(ProductVariantDetail productVariantDetail, List<ProductVariantDetail> productVariantDetails);

    default Map<String, String[]> mapMemories(List<ProductVariantDetail> productVariantDetails) {
        return productVariantDetails.stream()
                .collect(Collectors.groupingBy(
                        detail -> detail.getMemory().getId(),
                        Collectors.mapping(detail -> detail.getColor().getId(), Collectors.toList())
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toArray(new String[0])));
    }

    @Mapping(target = "price", source = "requestDTO.price")
    @Mapping(target = "sale", source = "requestDTO.sale")
    @Mapping(target = "status", source = "requestDTO.status")
    @Mapping(target = "color", source = "color")
    @Mapping(target = "memory", source = "memory")
    @Mapping(target = "quantity", source = "detail.quantity")
    @Mapping(target = "productVariant", source = "productVariant")
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ProductVariantDetail toProductVariantDetail(ProductVariantDetailRequest requestDTO, ProductVariantDetailRequest.MemoryColorRequest detail, Color color, Memory memory, ProductVariant productVariant);
}
