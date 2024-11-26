package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.ProductsImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsImageRepository extends JpaRepository<ProductsImage, String> {
    List<ProductsImage> findAllByProductVariantDetailId(String productVariantDetailId);
}
