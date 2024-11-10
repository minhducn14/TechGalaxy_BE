package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.request.ProductDetailUpdateRequest;
import iuh.fit.se.techgalaxy.dto.request.ProductVariantDetailRequest;
import iuh.fit.se.techgalaxy.dto.response.ProductVariantDetailResponse;

public interface ProductVariantDetailService {

    ProductVariantDetailResponse getProductVariantDetail(String variantId);

    Boolean createProductVariantDetail(String variantId, ProductVariantDetailRequest productVariantDetailRequest);
    Boolean updateProductVariantDetail(String productDetailId, ProductDetailUpdateRequest productDetailUpdateRequest);
    Boolean updateProductVariantDetailPrice(String productVariantId, Double price, Double sale);

    void deleteProductVariantDetail(String productDetailId);

}
