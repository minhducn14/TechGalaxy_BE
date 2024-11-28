package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "orderDetails", path = "orderDetails", exported = false)
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findOrderDetailsByOrderId(String orderId);
}
