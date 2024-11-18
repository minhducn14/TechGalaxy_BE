package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.request.ProductDetailUpdateRequest;
import iuh.fit.se.techgalaxy.dto.request.ProductVariantDetailRequest;
import iuh.fit.se.techgalaxy.dto.response.ProductPageResponse;
import iuh.fit.se.techgalaxy.dto.response.ProductVariantDetailResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductVariantDetailService {

    ProductVariantDetailResponse getProductVariantDetail(String variantId);

    Boolean createProductVariantDetail(String variantId, List<ProductVariantDetailRequest> productVariantDetailRequest);
    Boolean updateProductVariantDetail(String productDetailId, ProductDetailUpdateRequest productDetailUpdateRequest);
    void deleteProductVariantDetail(String productDetailId);

    Page<ProductPageResponse> getFilteredProductDetails(List<String> trademark,
                                                        Double minPrice, Double maxPrice,
                                                        List<String> memory,
                                                        List<String> usageCategoryId,
                                                        List<String> values,
                                                        String sort,
                                                        Integer page,
                                                        Integer size);

}
