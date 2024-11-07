package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.UsageCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsageCategoryRepository extends JpaRepository<UsageCategory,String> {
}
