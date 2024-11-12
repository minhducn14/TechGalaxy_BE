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

import iuh.fit.se.techgalaxy.dto.request.AttributeRequest;
import iuh.fit.se.techgalaxy.dto.request.ProductRequest;
import iuh.fit.se.techgalaxy.dto.response.AttributeResponse;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.ProductResponse;
import iuh.fit.se.techgalaxy.service.AttributeService;
import iuh.fit.se.techgalaxy.service.impl.AttributeServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

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

}
