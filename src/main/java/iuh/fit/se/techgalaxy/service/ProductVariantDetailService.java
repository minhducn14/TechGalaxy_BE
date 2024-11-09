package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.request.ProductVariantDetailRequest;
import iuh.fit.se.techgalaxy.dto.response.ProductVariantDetailResponse;

public interface ProductVariantDetailService {

    ProductVariantDetailResponse getProductVariantDetail(String variantId);

    Boolean createProductVariantDetail(String variantId, ProductVariantDetailRequest productVariantDetailRequest);

}
