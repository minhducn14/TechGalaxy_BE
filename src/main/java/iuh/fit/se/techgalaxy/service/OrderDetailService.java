package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.request.OrderDetailRequest;
import iuh.fit.se.techgalaxy.dto.response.OrderDetailResponse;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailResponse> getOrderDetailsByOrderId(String orderId);

    OrderDetailResponse save(OrderDetailRequest orderDetailRequest);

    OrderDetailResponse findById(String id);

    OrderDetailResponse update(String id, OrderDetailRequest orderDetailRequest);
}
