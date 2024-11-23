package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.request.ProductDetailUpdateRequest;
import iuh.fit.se.techgalaxy.dto.request.ProductVariantDetailRequest;
import iuh.fit.se.techgalaxy.dto.response.ProductDetailResponse;
import iuh.fit.se.techgalaxy.dto.response.ProductPageResponse;
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
                                                detail.getViewsCount(),
                                                detail.getPrice(),
                                                detail.getSale(),
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

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "quantity", source = "detail.quantity")
    @Mapping(target = "price", source = "requestDTO.price")
    @Mapping(target = "sale", source = "requestDTO.sale")
    @Mapping(target = "productVariant", source = "productVariant")
    ProductVariantDetail toProductVariantDetail(ProductVariantDetailRequest requestDTO,
                                                ProductVariantDetailRequest.ColorRequest detail,
                                                Color color,
                                                Memory memory,
                                                ProductVariant productVariant);

    void toUpdate(@MappingTarget ProductVariantDetail productVariantDetail, ProductDetailUpdateRequest productDetailUpdateRequest);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "productVariant.id", target = "variantId")
    @Mapping(source = "productVariant.name", target = "name")
    @Mapping(source = "productVariant.avatar", target = "avatar")
    ProductPageResponse toResponsePage(ProductVariantDetail detail);


    @Mapping(source = "productVariant.id",target = "productVariantId")
    ProductDetailResponse toResponse(ProductVariantDetail detail);
}
