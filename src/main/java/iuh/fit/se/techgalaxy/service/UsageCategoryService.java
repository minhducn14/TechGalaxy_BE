package iuh.fit.se.techgalaxy.service;

import java.util.List;

import iuh.fit.se.techgalaxy.dto.request.UsageCategoryRequest;
import iuh.fit.se.techgalaxy.dto.response.ProductVariantResponse;
import iuh.fit.se.techgalaxy.dto.response.UsageCategoryResponse;

public interface UsageCategoryService {
	List<UsageCategoryResponse> getAllUsageCategories();

	UsageCategoryResponse getUsageCategoryById(String id);

	UsageCategoryResponse createUsageCategory(UsageCategoryRequest usageCategoryRequest);

	UsageCategoryResponse updateUsageCategory(String id, UsageCategoryRequest usageCategoryRequest);

	void deleteUsageCategory(String id);

	List<ProductVariantResponse> getProductsByUsageCategoryId(String usageCategoryId);

}
