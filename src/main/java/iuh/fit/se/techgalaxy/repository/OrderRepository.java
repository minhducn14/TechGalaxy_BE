package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

}
