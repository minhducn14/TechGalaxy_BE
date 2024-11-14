package iuh.fit.se.techgalaxy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import iuh.fit.se.techgalaxy.entities.Value;

@RepositoryRestResource(collectionResourceRel = "values", path = "values", exported = false)
public interface ValueRepository extends JpaRepository<Value, String>{

}
