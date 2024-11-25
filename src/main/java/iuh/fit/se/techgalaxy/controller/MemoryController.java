package iuh.fit.se.techgalaxy.controller;


import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.entities.Memory;
import iuh.fit.se.techgalaxy.service.MemoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/memories")
public class MemoryController {
    MemoryService memoryServiceImpl;

    @GetMapping
    public ResponseEntity<DataResponse<Memory>> getAllMemory() {
        return ResponseEntity
                .ok(DataResponse.<Memory>builder().data(memoryServiceImpl.getAllMemories()).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Memory>> getMemoryById(String id) {
        return ResponseEntity.ok(DataResponse.<Memory>builder().data(List.of( memoryServiceImpl.getMemoryById(id))).build());
    }
    @GetMapping("/ids")
    public ResponseEntity<DataResponse<Memory>> getMemoriesByIDs(@RequestParam List<String> ids) {
        return ResponseEntity.ok(DataResponse.<Memory>builder().data(memoryServiceImpl.getMemoriesByIDs(ids)).build());
    }
}
