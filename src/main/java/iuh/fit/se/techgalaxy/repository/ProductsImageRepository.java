package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.ProductsImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "productsImages", path = "productsImages", exported = false)
public interface ProductsImageRepository extends JpaRepository<ProductsImage, String> {
    List<ProductsImage> findAllByProductVariantDetailId(String productVariantDetailId);
}
