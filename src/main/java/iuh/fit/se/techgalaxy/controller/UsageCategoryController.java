package iuh.fit.se.techgalaxy.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iuh.fit.se.techgalaxy.dto.request.UsageCategoryRequest;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.UsageCategoryResponse;
import iuh.fit.se.techgalaxy.service.impl.UsageCategoryServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/usageCategories")
public class UsageCategoryController {

	UsageCategoryServiceImpl usageCategoryServiceImpl;

	@GetMapping
	public ResponseEntity<DataResponse<UsageCategoryResponse>> getAllUsageCategory() {
		return ResponseEntity.ok(DataResponse.<UsageCategoryResponse>builder()
				.data(usageCategoryServiceImpl.getAllUsageCategories()).build());
	}

	@GetMapping("/usageCategories/{id}")
	public ResponseEntity<DataResponse<UsageCategoryResponse>> getUsageCategoryById(@PathVariable String id) {
		Set<UsageCategoryResponse> usageCategoryResponse = new HashSet<UsageCategoryResponse>();
		usageCategoryResponse.add(usageCategoryServiceImpl.getUsageCategoryById(id));
		return ResponseEntity.ok(DataResponse.<UsageCategoryResponse>builder().data(usageCategoryResponse).build());
	}

	@PostMapping
	public ResponseEntity<DataResponse<UsageCategoryResponse>> createUsageCategory(
			@RequestBody UsageCategoryRequest usageCategoryRequest) {
		Set<UsageCategoryResponse> usageCategoryResponse = new HashSet<UsageCategoryResponse>();
		usageCategoryResponse.add(usageCategoryServiceImpl.createUsageCategory(usageCategoryRequest));
		return ResponseEntity.ok(DataResponse.<UsageCategoryResponse>builder().data(usageCategoryResponse).build());
	}

	@PutMapping("/usageCategories/{id}")
	public ResponseEntity<DataResponse<UsageCategoryResponse>> updateUsageCategory(@PathVariable String id,
			@RequestBody UsageCategoryRequest usageCategoryRequest) {
		Set<UsageCategoryResponse> usageCategoryResponse = new HashSet<UsageCategoryResponse>();
		usageCategoryResponse.add(usageCategoryServiceImpl.updateUsageCategory(id, usageCategoryRequest));
		return ResponseEntity.ok(DataResponse.<UsageCategoryResponse>builder().data(usageCategoryResponse).build());
	}

	@DeleteMapping("/usageCategories/{id}")
	public ResponseEntity<DataResponse<Object>> deleteUsageCategory(@PathVariable String id) {
		usageCategoryServiceImpl.deleteUsageCategory(id);
		return ResponseEntity.ok(DataResponse.<Object>builder().message("Delete " + id + " success").build());
	}
	
}
