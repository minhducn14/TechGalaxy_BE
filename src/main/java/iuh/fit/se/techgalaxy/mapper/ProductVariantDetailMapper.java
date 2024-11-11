package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.request.ProductDetailUpdateRequest;
import iuh.fit.se.techgalaxy.dto.request.ProductVariantDetailRequest;
import iuh.fit.se.techgalaxy.dto.response.ProductVariantDetailResponse;
import iuh.fit.se.techgalaxy.entities.Color;
import iuh.fit.se.techgalaxy.entities.Memory;
import iuh.fit.se.techgalaxy.entities.ProductVariant;
import iuh.fit.se.techgalaxy.entities.ProductVariantDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductVariantDetailMapper {
    @Mapping(target = "memories", expression = "java(mapMemories(productVariantDetails))")
    ProductVariantDetailResponse toProductVariantDetailResponse(ProductVariantDetail productVariantDetail, List<ProductVariantDetail> productVariantDetails);

    default Map<String, ProductVariantDetailResponse.ColorQuantity[]> mapMemories(List<ProductVariantDetail> productVariantDetails) {
        return productVariantDetails.stream()
                .collect(Collectors.groupingBy(
                        detail -> detail.getMemory().getId(),
                        Collectors.mapping(detail ->
                                        new ProductVariantDetailResponse.ColorQuantity(
                                                detail.getQuantity(),
                                                detail.getColor().getId()
                                        ),
                                Collectors.toList()
                        )
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().toArray(new ProductVariantDetailResponse.ColorQuantity[0])
                ));
    }

    @Mapping(target = "status", source = "requestDTO.status")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ProductVariantDetail toProductVariantDetail(ProductVariantDetailRequest requestDTO, ProductVariantDetailRequest.MemoryColorRequest detail, Color color, Memory memory, ProductVariant productVariant);

    void  toUpdate(@MappingTarget ProductVariantDetail productVariantDetail, ProductDetailUpdateRequest productDetailUpdateRequest);
}
