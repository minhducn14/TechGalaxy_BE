package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.request.AttributeRequest;
import iuh.fit.se.techgalaxy.dto.request.AttributeValueRequest;
import iuh.fit.se.techgalaxy.dto.response.AttributeResponse;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.ValueResponse;
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

    @PostMapping("/productvariant")
    public ResponseEntity<DataResponse<Object>> createAttributeValueVariant(@RequestParam String provariant, @RequestBody List<AttributeValueRequest> request) {
        attributeServiceImpl.createValueProductVariant(provariant, request);
        return ResponseEntity.ok(DataResponse.<Object>builder().message("Ok").build());
    }

    @GetMapping("/atributevalue/{name}")
    public ResponseEntity<DataResponse<ValueResponse>> getValueByNameAtri(@PathVariable String name) {
        List<ValueResponse> valueResponses = attributeServiceImpl.getValueByNameAtri(name);
        return ResponseEntity.ok(DataResponse.<ValueResponse>builder().data(valueResponses).build());
    }
}
