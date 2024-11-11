package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.request.ProductDetailUpdateRequest;
import iuh.fit.se.techgalaxy.dto.request.ProductVariantDetailRequest;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.ProductVariantDetailResponse;
import iuh.fit.se.techgalaxy.service.impl.ProductVariantDetailServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping({"/variants/{variantId}/details", "/variants/details"})
public class ProductVariantDetailController {
    ProductVariantDetailServiceImpl productVariantDetailServiceImpl;

    @GetMapping
    public ResponseEntity<DataResponse<ProductVariantDetailResponse>> getAllProductVariantDetails(@PathVariable String variantId) {
        Set<ProductVariantDetailResponse> productVariantDetailResponses = new HashSet<>();
        productVariantDetailResponses.add(productVariantDetailServiceImpl.getProductVariantDetail(variantId));
        return ResponseEntity.ok(DataResponse.<ProductVariantDetailResponse>builder().data(productVariantDetailResponses).build());
    }


    @PostMapping
    public ResponseEntity<DataResponse<Boolean>> createProductVariantDetail(@PathVariable String variantId, @RequestBody ProductVariantDetailRequest productVariantDetailRequest) {
        productVariantDetailServiceImpl.createProductVariantDetail(variantId, productVariantDetailRequest);
        return ResponseEntity.ok(DataResponse.<Boolean>builder().message("success").build());
    }

    @PutMapping("/{productDetailId}")
    public ResponseEntity<DataResponse<Boolean>> updateProductVariantDetail(@PathVariable String productDetailId, @RequestBody ProductDetailUpdateRequest productDetailUpdateRequest) {
        productVariantDetailServiceImpl.updateProductVariantDetail(productDetailId, productDetailUpdateRequest);
        return ResponseEntity.ok(DataResponse.<Boolean>builder().message("success").build());
    }

    @PutMapping()
    public ResponseEntity<DataResponse<Boolean>> updateProductVariantDetailPrice(@PathVariable String variantId, @RequestParam Double price, @RequestParam Double sale) {
        productVariantDetailServiceImpl.updateProductVariantDetailPrice(variantId, price, sale);
        return ResponseEntity.ok(DataResponse.<Boolean>builder().message("success").build());
    }

    @DeleteMapping("/{productDetailId}")
    public ResponseEntity<DataResponse<Boolean>> deleteProductVariantDetail(@PathVariable String productDetailId) {
        productVariantDetailServiceImpl.deleteProductVariantDetail(productDetailId);
        return ResponseEntity.ok(DataResponse.<Boolean>builder().message("success").build());
    }

}
