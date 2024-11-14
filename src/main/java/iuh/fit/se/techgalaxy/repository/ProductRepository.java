package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "products", path = "products", exported = false)
public interface ProductRepository extends JpaRepository<Product, String> {

}
