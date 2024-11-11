package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.request.ProductRequest;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.ProductResponse;
import iuh.fit.se.techgalaxy.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
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
@RequestMapping("/products")
public class ProductController {
    ProductServiceImpl productServiceImpl;

    @GetMapping
    ResponseEntity<DataResponse<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(DataResponse.<ProductResponse>builder()
                .data(productServiceImpl.getAllProducts())
                .build());
    }
    @PostMapping
    public ResponseEntity<DataResponse<ProductResponse>> createProduct(@RequestBody @Valid ProductRequest request) {
        Set<ProductResponse> productResponses = new HashSet<>();
        productResponses.add(productServiceImpl.createProduct(request));
        return ResponseEntity.ok(DataResponse.<ProductResponse>builder()
                .data(productResponses)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<ProductResponse>> getProductById(@PathVariable String id) {
        Set<ProductResponse> productResponses = new HashSet<>();
        productResponses.add(productServiceImpl.getProductById(id));
        return ResponseEntity.ok(DataResponse.<ProductResponse>builder()
                .data(productResponses)
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<ProductResponse>> updateProduct(@PathVariable String id, @RequestBody ProductRequest request) {
        Set<ProductResponse> productResponses = new HashSet<>();
        productResponses.add(productServiceImpl.updateProduct(id, request));
        return ResponseEntity.ok(DataResponse.<ProductResponse>builder()
                .data(productResponses)
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Object>> deleteProduct(@PathVariable String id) {
        productServiceImpl.deleteProduct(id);
        return ResponseEntity.ok(DataResponse.<Object>builder().message("Delete " + id + " success").build());
    }
}

