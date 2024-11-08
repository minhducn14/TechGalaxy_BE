package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.ProductVariantDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductVariantDetailRepository extends JpaRepository<ProductVariantDetail, String> {
    List<ProductVariantDetail> findAllByProductVariantId(String productVariantId);
}
