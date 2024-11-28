package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.ProductFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "feedbacks", path = "feedbacks", exported = false)
public interface ProductFeedbackRepository extends JpaRepository<ProductFeedback, String> {
    List<ProductFeedback> findByProductVariantId(String productVariantId);

    List<ProductFeedback> findByCustomerId(String customerID);

}
