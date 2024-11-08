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
import iuh.fit.se.techgalaxy.dto.response.AttributeRespone;
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
	public ResponseEntity<DataResponse<AttributeRespone>> getAllAttribute() {

		return ResponseEntity
				.ok(DataResponse.<AttributeRespone>builder().data(attributeServiceImpl.getAllAttribute()).build());
	}

	@GetMapping("/attributes/{id}")
	public ResponseEntity<DataResponse<AttributeRespone>> getAttributeById(@PathVariable String id) {
		Set<AttributeRespone> attributeRespone = new HashSet<>();
		attributeRespone.add(attributeServiceImpl.getAttributeById(id));
		return ResponseEntity.ok(DataResponse.<AttributeRespone>builder().data(attributeRespone).build());
	}

	@PostMapping
	public ResponseEntity<DataResponse<AttributeRespone>> creatAttribute(@RequestBody AttributeRequest request) {
		Set<AttributeRespone> attributeRespone = new HashSet<>();
		attributeRespone.add(attributeServiceImpl.createAttribute(request));
		return ResponseEntity.ok(DataResponse.<AttributeRespone>builder().data(attributeRespone).build());
	}

	@PutMapping("attributes/{id}")
	public ResponseEntity<DataResponse<AttributeRespone>> updateAttribute(@PathVariable String id,
			@RequestBody AttributeRequest request) {
		Set<AttributeRespone> attributeRespone = new HashSet<>();
		attributeRespone.add(attributeServiceImpl.updateAttribute(id, request));
		return ResponseEntity.ok(DataResponse.<AttributeRespone>builder().data(attributeRespone).build());
	}

	@DeleteMapping("attributes/{id}")
	public ResponseEntity<DataResponse<AttributeRespone>> deleteAttribute(@PathVariable String id,
			@RequestBody AttributeRequest request) {
		Set<AttributeRespone> attributeRespone = new HashSet<>();
		attributeRespone.add(attributeServiceImpl.updateAttribute(id, request));
		return ResponseEntity.ok(DataResponse.<AttributeRespone>builder().data(attributeRespone).build());
	}

}
