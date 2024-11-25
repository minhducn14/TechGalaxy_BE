package iuh.fit.se.techgalaxy.service.impl;

import iuh.fit.se.techgalaxy.entities.Memory;
import iuh.fit.se.techgalaxy.repository.MemoryRepository;
import iuh.fit.se.techgalaxy.service.MemoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MemoryServiceImpl implements MemoryService {
    MemoryRepository memoryRepository;

    @Override
    public List<Memory> getAllMemories() {
        return memoryRepository.findAll();
    }

    @Override
    public Memory getMemoryById(String id) {
        return memoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Memory> getMemoriesByIDs(List<String> ids) {
        return memoryRepository.findMemoriesByIdIsIn(ids);
    }
}
