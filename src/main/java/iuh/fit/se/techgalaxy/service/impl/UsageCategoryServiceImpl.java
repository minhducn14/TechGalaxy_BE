package iuh.fit.se.techgalaxy.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import iuh.fit.se.techgalaxy.dto.request.UsageCategoryRequest;
import iuh.fit.se.techgalaxy.dto.response.ProductVariantResponse;
import iuh.fit.se.techgalaxy.dto.response.UsageCategoryResponse;
import iuh.fit.se.techgalaxy.entities.UsageCategory;
import iuh.fit.se.techgalaxy.mapper.UsageCategoryMapper;
import iuh.fit.se.techgalaxy.repository.UsageCategoryRepository;
import iuh.fit.se.techgalaxy.service.UsageCategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UsageCategoryServiceImpl implements UsageCategoryService {
	UsageCategoryRepository usageCategoryRepository;
	UsageCategoryMapper usageCategoryMapper;

	@Override
	public List<UsageCategoryResponse> getAllUsageCategories() {
		return usageCategoryRepository.findAll().stream().map(usageCategoryMapper::toUseCategoryResponse).toList();
	}

	@Override
	public UsageCategoryResponse getUsageCategoryById(String id) {
		return usageCategoryRepository.findById(id).map(usageCategoryMapper::toUseCategoryResponse)
				.orElseThrow(() -> new RuntimeException("UsageCategory not found"));
	}

	@Override
	public UsageCategoryResponse createUsageCategory(UsageCategoryRequest usageCategoryRequest) {
		UsageCategory usageCategory = usageCategoryMapper.toUsageCategory(usageCategoryRequest);
		return usageCategoryMapper.toUseCategoryResponse(usageCategoryRepository.save(usageCategory));
	}

	@Override
	public UsageCategoryResponse updateUsageCategory(String id, UsageCategoryRequest usageCategoryRequest) {
		UsageCategory usageCategory = usageCategoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("usageCategory not found"));
		usageCategoryMapper.updateUsageCategoryFromRequest(usageCategory,usageCategoryRequest);
		return usageCategoryMapper.toUseCategoryResponse(usageCategoryRepository.save(usageCategory));
	}

	@Override
	public void deleteUsageCategory(String id) {
		UsageCategory usageCategory = usageCategoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("usageCategory not found"));
		usageCategoryRepository.delete(usageCategory);
	}

	@Override
	public List<ProductVariantResponse> getProductsByUsageCategoryId(String usageCategoryId) {
		return null;
	}

}
