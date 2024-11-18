package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.request.ProductDetailUpdateRequest;
import iuh.fit.se.techgalaxy.dto.request.ProductVariantDetailRequest;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.ProductPageResponse;
import iuh.fit.se.techgalaxy.dto.response.ProductVariantDetailResponse;
import iuh.fit.se.techgalaxy.service.ProductVariantDetailService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping({"/variants/{variantId}/details", "/variants/details"})
public class ProductVariantDetailController {
    static String successMessage = "success";
    ProductVariantDetailService productVariantDetailServiceImpl;
    PagedResourcesAssembler<ProductPageResponse> pagedResourcesAssembler;

    @GetMapping
    public ResponseEntity<DataResponse<ProductVariantDetailResponse>> getAllProductVariantDetails(@PathVariable String variantId) {
        Set<ProductVariantDetailResponse> productVariantDetailResponses = new HashSet<>();
        productVariantDetailResponses.add(productVariantDetailServiceImpl.getProductVariantDetail(variantId));
        return ResponseEntity.ok(DataResponse.<ProductVariantDetailResponse>builder().data(productVariantDetailResponses).build());
    }


    @PostMapping
    public ResponseEntity<DataResponse<Boolean>> createProductVariantDetail(@PathVariable String variantId, @RequestBody List<ProductVariantDetailRequest> productVariantDetailRequest) {
        productVariantDetailServiceImpl.createProductVariantDetail(variantId, productVariantDetailRequest);
        return ResponseEntity.ok(DataResponse.<Boolean>builder().message(successMessage).build());
    }

    @PutMapping("/{productDetailId}")
    public ResponseEntity<DataResponse<Boolean>> updateProductVariantDetail(@PathVariable String productDetailId, @Valid @RequestBody ProductDetailUpdateRequest productDetailUpdateRequest) {
        productVariantDetailServiceImpl.updateProductVariantDetail(productDetailId, productDetailUpdateRequest);
        return ResponseEntity.ok(DataResponse.<Boolean>builder().message(successMessage).build());
    }

    @DeleteMapping("/{productDetailId}")
    public ResponseEntity<DataResponse<Boolean>> deleteProductVariantDetail(@PathVariable String productDetailId) {
        productVariantDetailServiceImpl.deleteProductVariantDetail(productDetailId);
        return ResponseEntity.ok(DataResponse.<Boolean>builder().message(successMessage).build());
    }

    @GetMapping("/filter")
    public ResponseEntity<PagedModel<EntityModel<ProductPageResponse>>> getFilteredProductDetails(
            @RequestParam(required = false) List<String> trademark,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) List<String> memory,
            @RequestParam(required = false) List<String> usageCategoryId,
            @RequestParam(required = false) List<String> values,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        Page<ProductPageResponse> response = productVariantDetailServiceImpl.getFilteredProductDetails(
                trademark, minPrice, maxPrice, memory, usageCategoryId, values, sort, page, size);
        PagedModel<EntityModel<ProductPageResponse>> pagedModel = pagedResourcesAssembler.toModel(response);
        return ResponseEntity.ok(pagedModel);
    }
}
