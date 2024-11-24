package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.request.OrderDetailRequest;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.OrderDetailResponse;
import iuh.fit.se.techgalaxy.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-details")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping
    public ResponseEntity<DataResponse<OrderDetailResponse>> createOrderDetail(@RequestBody OrderDetailRequest orderDetailRequest) {
        List<OrderDetailResponse> orderDetailResponses = List.of(orderDetailService.save(orderDetailRequest));
        return ResponseEntity.ok(DataResponse.<OrderDetailResponse>builder()
                .data(orderDetailResponses)
                .build());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<DataResponse<OrderDetailResponse>> getOrderDetailsByOrderId(@PathVariable String orderId) {
        return ResponseEntity.ok(DataResponse.<OrderDetailResponse>builder()
                .data(orderDetailService.getOrderDetailsByOrderId(orderId))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<OrderDetailResponse>> getById(@PathVariable String id) {
        List<OrderDetailResponse> orderDetailResponses = List.of(orderDetailService.findById(id));
        return ResponseEntity.ok(DataResponse.<OrderDetailResponse>builder()
                .data(orderDetailResponses)
                .build());
    }
}
