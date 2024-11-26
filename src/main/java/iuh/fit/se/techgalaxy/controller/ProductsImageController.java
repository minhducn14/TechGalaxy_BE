package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.request.ProductsImageRequest;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.ProductsImageResponse;
import iuh.fit.se.techgalaxy.service.ProductsImageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/products/image")
public class ProductsImageController {

    ProductsImageService productsImageService;

    @GetMapping("/{productId}")
    public ResponseEntity<DataResponse<ProductsImageResponse>> getAllProductsImageByProductId(@PathVariable String productId) {
        return ResponseEntity.ok(DataResponse.<ProductsImageResponse>builder()
                .data(productsImageService.getProductsImageByProductId(productId))
                .build());
    }

    @PostMapping("/{variantDetail}")
    public ResponseEntity<DataResponse<ProductsImageResponse>> createProductsImage(@PathVariable String variantDetail, @RequestBody List<ProductsImageRequest> productsImageRequest) {
        return ResponseEntity.ok(DataResponse.<ProductsImageResponse>builder()
                .data(productsImageService.createProductsImage(variantDetail, productsImageRequest))
                .build());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<DataResponse<Object>> deleteProductsImageByProductId(@PathVariable String productId) {
        productsImageService.deleteProductsImageByProductId(productId);
        return ResponseEntity.ok(DataResponse.<Object>builder()
                .message("Delete success")
                .build());
    }
    @DeleteMapping("/delete/{imageId}")
    public ResponseEntity<DataResponse<Object>> deleteProductsImageByImageId(@PathVariable String imageId) {
        productsImageService.deleteProductsImageByImageId(imageId);
        return ResponseEntity.ok(DataResponse.<Object>builder()
                .message("Delete success")
                .build());
    }

}
