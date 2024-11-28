package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "attributes", path = "attributes", exported = false)
public interface AttributeRepository extends JpaRepository<Attribute, String> {
}
