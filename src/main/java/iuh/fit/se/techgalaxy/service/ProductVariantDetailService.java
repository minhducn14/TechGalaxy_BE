package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.response.ProductVariantDetailResponse;

public interface ProductVariantDetailService {

    ProductVariantDetailResponse getProductVariantDetail(String variantId);

}
