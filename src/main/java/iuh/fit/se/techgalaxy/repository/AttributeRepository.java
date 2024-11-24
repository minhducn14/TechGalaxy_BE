package iuh.fit.se.techgalaxy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import iuh.fit.se.techgalaxy.entities.Attribute;


@RepositoryRestResource(collectionResourceRel = "attributes", path = "attributes", exported = false)
public interface AttributeRepository extends JpaRepository<Attribute, String>{
}
