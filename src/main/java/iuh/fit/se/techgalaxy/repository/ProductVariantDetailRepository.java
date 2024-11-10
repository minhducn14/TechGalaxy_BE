package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.ProductVariantDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductVariantDetailRepository extends JpaRepository<ProductVariantDetail, String> {
    List<ProductVariantDetail> findAllByProductVariantId(String productVariantId);

    @Modifying
    @Query("UPDATE ProductVariantDetail p SET p.price = :price, p.sale = :sale  WHERE p.productVariant.id = :variantId")
    int updatePriceByVariantId(@Param("price") Double price, @Param("sale") Double sale, @Param("variantId") String variantId);
}
