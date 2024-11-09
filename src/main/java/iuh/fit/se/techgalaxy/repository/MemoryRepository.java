package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryRepository extends JpaRepository<Memory, String> {
}
