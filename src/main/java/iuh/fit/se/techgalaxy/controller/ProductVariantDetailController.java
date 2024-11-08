package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.ProductVariantDetailResponse;
import iuh.fit.se.techgalaxy.service.impl.ProductVariantDetailServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/variants/{variantId}/details")
public class ProductVariantDetailController {
    ProductVariantDetailServiceImpl productVariantDetailServiceImpl;

    @GetMapping
    public ResponseEntity<DataResponse<ProductVariantDetailResponse>> getAllProductVariantDetails(@PathVariable String variantId) {
        Set<ProductVariantDetailResponse> productVariantDetailResponses = new HashSet<>();
        productVariantDetailResponses.add(productVariantDetailServiceImpl.getProductVariantDetail(variantId));
        return ResponseEntity.ok(DataResponse.<ProductVariantDetailResponse>builder().data(productVariantDetailResponses).build());
    }
}
