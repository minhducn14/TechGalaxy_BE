package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "memories", path = "memories", exported = false)
public interface MemoryRepository extends JpaRepository<Memory, String> {
}
