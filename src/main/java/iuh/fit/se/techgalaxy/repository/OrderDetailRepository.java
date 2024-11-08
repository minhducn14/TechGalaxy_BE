package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findOrderDetailsByOrderId(String orderId);
}
