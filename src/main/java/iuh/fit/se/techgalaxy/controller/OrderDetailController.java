package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.OrderDetailResponse;
import iuh.fit.se.techgalaxy.dto.response.OrderResponse;
import iuh.fit.se.techgalaxy.service.OrderDetailService;
import iuh.fit.se.techgalaxy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/{order}")
    public ResponseEntity<DataResponse<OrderDetailResponse>> getOrderDetailsByOrderId(@PathVariable String orderId) {
        return ResponseEntity.ok(DataResponse.<OrderDetailResponse>builder()
                .data(orderDetailService.getOrderDetailsByOrderId(orderId))
                .build());
    }
}
