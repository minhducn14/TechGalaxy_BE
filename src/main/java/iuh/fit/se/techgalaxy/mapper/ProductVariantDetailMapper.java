package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.response.ProductVariantDetailResponse;
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
}
