package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.request.ProductVariantRequest;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.ProductVariantResponse;
import iuh.fit.se.techgalaxy.service.impl.ProductVariantServiceImpl;
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
@RequestMapping("/products/{productId}/variants")
public class ProductVariantController {
    ProductVariantServiceImpl productVariantServiceImpl;
    @GetMapping
    public ResponseEntity<DataResponse<ProductVariantResponse>> getAllProductVariants(@PathVariable String productId) {
        return ResponseEntity.ok(DataResponse.<ProductVariantResponse>builder().data(productVariantServiceImpl.getAllProductVariantsByProductId(productId)).build());
    }

    @PostMapping
    public ResponseEntity<DataResponse<ProductVariantResponse>> createVariant(@PathVariable String productId, @RequestBody ProductVariantRequest request) {
        Set<ProductVariantResponse> createdVariant = new HashSet<>();
        createdVariant.add(productVariantServiceImpl.createProductVariant(productId, request));
        return ResponseEntity.ok(DataResponse.<ProductVariantResponse>builder().data(createdVariant).build());
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ProductVariant> getVariantById(@PathVariable String id) {
//        ProductVariant variant = productVariantService.findVariantById(id);
//        return ResponseEntity.ok(variant);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ProductVariant> updateVariant(@PathVariable String id, @RequestBody ProductVariant variant) {
//        ProductVariant updatedVariant = productVariantService.updateVariant(id, variant);
//        return ResponseEntity.ok(updatedVariant);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteVariant(@PathVariable String id) {
//        productVariantService.deleteVariant(id);
//        return ResponseEntity.noContent().build();
//    }
}




