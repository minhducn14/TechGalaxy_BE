package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.entities.Memory;

import java.util.List;

public interface MemoryService {
    public List<Memory> getAllMemories();
    public Memory getMemoryById(String id);
}
