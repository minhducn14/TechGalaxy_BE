package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.request.UsageCategoryRequest;
import iuh.fit.se.techgalaxy.dto.response.ProductVariantResponse;
import iuh.fit.se.techgalaxy.dto.response.UsageCategoryResponse;

import java.util.List;

public interface UsageCategoryService {
    List<UsageCategoryResponse> getAllUsageCategories();

    UsageCategoryResponse getUsageCategoryById(String id);

    UsageCategoryResponse createUsageCategory(UsageCategoryRequest usageCategoryRequest);

    UsageCategoryResponse updateUsageCategory(String id, UsageCategoryRequest usageCategoryRequest);

    void deleteUsageCategory(String id);

    List<ProductVariantResponse> getProductsByUsageCategoryId(String usageCategoryId);

}
