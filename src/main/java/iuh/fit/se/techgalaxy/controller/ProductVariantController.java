package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.ProductVariantResponse;
import iuh.fit.se.techgalaxy.service.impl.ProductVariantServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}




