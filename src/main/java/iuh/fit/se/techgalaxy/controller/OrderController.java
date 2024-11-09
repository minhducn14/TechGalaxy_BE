package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.request.OrderRequest;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.OrderResponse;
import iuh.fit.se.techgalaxy.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderServiceImpl orderServiceImpl;

    @Autowired
    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @GetMapping
    public ResponseEntity<DataResponse<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(DataResponse.<OrderResponse>builder()
                .data(orderServiceImpl.findAll())
                .build());
    }

    @PostMapping
    public ResponseEntity<DataResponse<OrderResponse>> createOrder(@RequestBody OrderRequest request) {
        List<OrderResponse> orderResponses = List.of(orderServiceImpl.save(request));
        return ResponseEntity.ok(DataResponse.<OrderResponse>builder()
                .data(orderResponses)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<OrderResponse>> getOrderById(@PathVariable String id) {
        List<OrderResponse> orderResponses = List.of(orderServiceImpl.findById(id));
        return ResponseEntity.ok(DataResponse.<OrderResponse>builder()
                .data(orderResponses)
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<OrderResponse>> updateOrder(@PathVariable String id, @RequestBody OrderRequest request) {
        List<OrderResponse> orderResponses = List.of(orderServiceImpl.update(id, request));
        return ResponseEntity.ok(DataResponse.<OrderResponse>builder()
                .data(orderResponses)
                .build());
    }
}
