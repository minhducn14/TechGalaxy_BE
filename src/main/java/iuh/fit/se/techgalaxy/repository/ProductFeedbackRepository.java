package iuh.fit.se.techgalaxy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import iuh.fit.se.techgalaxy.dto.response.ProductFeedbackResponse;
import iuh.fit.se.techgalaxy.entities.ProductFeedback;

@RepositoryRestResource(collectionResourceRel = "feedbacks", path = "feedbacks", exported = false)
public interface ProductFeedbackRepository extends JpaRepository<ProductFeedback, String> {
	List<ProductFeedback> findByProductVariantId (String productVariantId);
	List<ProductFeedback> findByCustomerId (String customerID);

}
