package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.response.ProductVariantResponse;

import java.util.List;

public interface ProductVariantService {
    List<ProductVariantResponse> getAllProductVariantsByProductId(String productId);
}
