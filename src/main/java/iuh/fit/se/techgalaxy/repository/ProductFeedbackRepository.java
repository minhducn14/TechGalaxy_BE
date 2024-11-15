package iuh.fit.se.techgalaxy.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "feedbacks", path = "feedbacks", exported = false)
public interface ProductFeedbackRepository {

}
