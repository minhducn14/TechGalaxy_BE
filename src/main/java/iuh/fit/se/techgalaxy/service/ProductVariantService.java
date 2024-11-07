package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.request.ProductVariantRequest;
import iuh.fit.se.techgalaxy.dto.response.ProductVariantResponse;

import java.util.Set;

public interface ProductVariantService {
    Set<ProductVariantResponse> getAllProductVariantsByProductId(String productId);

    ProductVariantResponse createProductVariant(String productId,ProductVariantRequest request);
}
