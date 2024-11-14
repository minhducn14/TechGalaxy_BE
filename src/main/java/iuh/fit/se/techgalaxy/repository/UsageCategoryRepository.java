package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.UsageCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "usageCategories", path = "usageCategories", exported = false)
public interface UsageCategoryRepository extends JpaRepository<UsageCategory,String> {
}
