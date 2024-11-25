package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.entities.Memory;

import java.util.List;

public interface MemoryService {
     List<Memory> getAllMemories();
     Memory getMemoryById(String id);

     List<Memory> getMemoriesByIDs(List<String> ids);

}
