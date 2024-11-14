package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.request.OrderDetailRequest;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.OrderDetailResponse;
import iuh.fit.se.techgalaxy.entities.OrderDetail;
import iuh.fit.se.techgalaxy.mapper.OrderDetailMapper;
import iuh.fit.se.techgalaxy.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<DataResponse<OrderDetailResponse>> createOrderDetail(OrderDetailRequest orderDetailRequest) {
        List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();
        orderDetailResponses.add(orderDetailService.save(orderDetailRequest));
        return ResponseEntity.ok(DataResponse.<OrderDetailResponse>builder()
                .data(orderDetailResponses)
                .build());
    }

    //    using query parameter to get order id
    @GetMapping(params = "orderId")
    public ResponseEntity<DataResponse<OrderDetailResponse>> getOrderDetailsByOrderId(@PathVariable String orderId) {
        return ResponseEntity.ok(DataResponse.<OrderDetailResponse>builder()
                .data(orderDetailService.getOrderDetailsByOrderId(orderId))
                .build());
    }

    //    using path variable to get order id
    @GetMapping("/{orderId}")
    public ResponseEntity<DataResponse<OrderDetailResponse>> getById(@PathVariable String orderId) {
        List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();
        orderDetailResponses.add(orderDetailService.findById(orderId));
        return ResponseEntity.ok(DataResponse.<OrderDetailResponse>builder()
                .data(orderDetailResponses)
                .build());
    }
}
