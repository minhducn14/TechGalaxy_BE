package iuh.fit.se.techgalaxy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import iuh.fit.se.techgalaxy.entities.ImgProductFeedback;
import iuh.fit.se.techgalaxy.entities.ProductFeedback;

@RepositoryRestResource(collectionResourceRel = "imgFeedbacks", path = "imgFeedbacks", exported = false)
public interface ImgProductFeedbackRepository extends JpaRepository<ImgProductFeedback, String> {

}
