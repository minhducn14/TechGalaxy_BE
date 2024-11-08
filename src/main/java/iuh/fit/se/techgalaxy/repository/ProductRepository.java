package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}
