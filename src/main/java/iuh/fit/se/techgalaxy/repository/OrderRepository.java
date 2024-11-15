package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "orders", path = "orders", exported = false)
public interface OrderRepository extends JpaRepository<Order, String> {
    // get list of orders by customer id


}
