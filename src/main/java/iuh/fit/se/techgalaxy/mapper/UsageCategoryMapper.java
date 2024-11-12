package iuh.fit.se.techgalaxy.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import iuh.fit.se.techgalaxy.dto.request.ProductRequest;
import iuh.fit.se.techgalaxy.dto.request.UsageCategoryRequest;
import iuh.fit.se.techgalaxy.dto.response.UsageCategoryResponse;
import iuh.fit.se.techgalaxy.entities.Product;
import iuh.fit.se.techgalaxy.entities.UsageCategory;

@Mapper(componentModel = "spring")
public interface UsageCategoryMapper {
	UsageCategoryResponse toUseCategoryResponse(UsageCategory usageCategory);
	
	UsageCategory toUsageCategory(UsageCategoryRequest usageCategoryRequest);
	
    void updateUsageCategoryFromRequest(@MappingTarget UsageCategory usageCategory,UsageCategoryRequest usageCategoryRequest);
}
