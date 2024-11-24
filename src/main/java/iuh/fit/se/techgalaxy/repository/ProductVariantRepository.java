package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "productVariants", path = "productVariants", exported = false)
public interface ProductVariantRepository extends JpaRepository<ProductVariant, String> {
    List<ProductVariant> findAllByProductId(String productId);
    @Query("SELECT pv FROM ProductVariant pv JOIN pv.productVariantDetails pvd WHERE pvd.id = :id")
    ProductVariant findProductVariantByProductVariantDetailId(@Param("id") String id);
}
