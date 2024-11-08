package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.request.OrderRequest;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.OrderResponse;
import iuh.fit.se.techgalaxy.entities.Order;
import org.springframework.hateoas.PagedModel;

public interface OrderService {
    OrderResponse save(OrderRequest orderRequest);

    OrderResponse findById(String id);

    OrderResponse update(OrderRequest orderRequest);

    PagedModel<OrderResponse> findAllOrders(int page, int size);
}
