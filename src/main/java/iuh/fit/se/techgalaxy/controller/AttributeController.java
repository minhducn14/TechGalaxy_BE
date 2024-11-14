package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.request.AttributeRequest;
import iuh.fit.se.techgalaxy.dto.request.AttributeValueRequest;
import iuh.fit.se.techgalaxy.dto.response.AttributeResponse;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.ProductResponse;
import iuh.fit.se.techgalaxy.service.impl.AttributeServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/attributes")
public class AttributeController {
	AttributeServiceImpl attributeServiceImpl;
	
	@GetMapping
	public ResponseEntity<DataResponse<AttributeResponse>> getAllAttribute() {
		return ResponseEntity
				.ok(DataResponse.<AttributeResponse>builder().data(attributeServiceImpl.getAllAttribute()).build());
	}

	@GetMapping("/attributes/{id}")
	public ResponseEntity<DataResponse<AttributeResponse>> getAttributeById(@PathVariable String id) {
		Set<AttributeResponse> attributeRespone = new HashSet<>();
		attributeRespone.add(attributeServiceImpl.getAttributeById(id));
		return ResponseEntity.ok(DataResponse.<AttributeResponse>builder().data(attributeRespone).build());
	}

	@PostMapping
	public ResponseEntity<DataResponse<AttributeResponse>> creatAttribute(@RequestBody AttributeRequest request) {
		Set<AttributeResponse> attributeRespone = new HashSet<>();
		attributeRespone.add(attributeServiceImpl.createAttribute(request));
		return ResponseEntity.ok(DataResponse.<AttributeResponse>builder().data(attributeRespone).build());
	}

	@PutMapping("attributes/{id}")
	public ResponseEntity<DataResponse<AttributeResponse>> updateAttribute(@PathVariable String id,
			@RequestBody AttributeRequest request) {
		Set<AttributeResponse> attributeRespone = new HashSet<>();
		attributeRespone.add(attributeServiceImpl.updateAttribute(id, request));
		return ResponseEntity.ok(DataResponse.<AttributeResponse>builder().data(attributeRespone).build());
	}

	@DeleteMapping("attributes/{id}")
	public ResponseEntity<DataResponse<AttributeResponse>> deleteAttribute(@PathVariable String id
			) {
		Set<AttributeResponse> attributeRespone = new HashSet<>();
		attributeRespone.add(attributeServiceImpl.deleteAttribute(id));
		return ResponseEntity.ok(DataResponse.<AttributeResponse>builder().data(attributeRespone).build());
	}

	@PostMapping()
	public ResponseEntity<DataResponse<ProductResponse>> createAttributeValueVariant(@RequestParam String provariant, @RequestBody List<AttributeValueRequest> request) {
//		Set<ProductResponse> productRespone = new HashSet<>();
//		productRespone.add(attributeServiceImpl.createProduct(request));
	//	return ResponseEntity.ok(DataResponse.<ProductResponse>builder().data(productRespone).build());
		return  null;
	}
}
