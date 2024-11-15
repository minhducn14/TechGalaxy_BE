package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.request.ProductDetailUpdateRequest;
import iuh.fit.se.techgalaxy.dto.request.ProductVariantDetailRequest;
import iuh.fit.se.techgalaxy.dto.response.ProductVariantDetailResponse;

import java.util.List;

public interface ProductVariantDetailService {

    ProductVariantDetailResponse getProductVariantDetail(String variantId);

    Boolean createProductVariantDetail(String variantId, List<ProductVariantDetailRequest> productVariantDetailRequest);
    Boolean updateProductVariantDetail(String productDetailId, ProductDetailUpdateRequest productDetailUpdateRequest);

    void deleteProductVariantDetail(String productDetailId);

}
