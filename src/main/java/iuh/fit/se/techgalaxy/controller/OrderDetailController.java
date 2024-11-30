package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.request.OrderDetailRequest;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.OrderDetailResponse;
import iuh.fit.se.techgalaxy.service.OrderDetailService;
import iuh.fit.se.techgalaxy.service.ProductVariantDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-details")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;
    private final ProductVariantDetailService productVariantDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService, ProductVariantDetailService productVariantDetailService) {
        this.orderDetailService = orderDetailService;
        this.productVariantDetailService = productVariantDetailService;
    }

    @PostMapping
    public ResponseEntity<DataResponse<OrderDetailResponse>> createOrderDetail(@RequestBody OrderDetailRequest orderDetailRequest) {
        productVariantDetailService.updateQuantity(orderDetailRequest.getProductVariantDetail().getId(), orderDetailRequest.getQuantity());
        List<OrderDetailResponse> orderDetailResponses = List.of(orderDetailService.save(orderDetailRequest));
        return ResponseEntity.ok(DataResponse.<OrderDetailResponse>builder()
                .message("Create order detail success")
                .data(orderDetailResponses)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<OrderDetailResponse>> getById(@PathVariable String id) {
        List<OrderDetailResponse> orderDetailResponses = List.of(orderDetailService.findById(id));
        return ResponseEntity.ok(DataResponse.<OrderDetailResponse>builder()
                .message("Get order detail by id success")
                .data(orderDetailResponses)
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<OrderDetailResponse>> updateOrderDetail(@PathVariable String id, @RequestBody OrderDetailRequest orderDetailRequest) {
        List<OrderDetailResponse> orderDetailResponses = List.of(orderDetailService.update(id, orderDetailRequest));
        return ResponseEntity.ok(DataResponse.<OrderDetailResponse>builder()
                .message("Update order detail success")
                .data(orderDetailResponses)
                .build());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<DataResponse<OrderDetailResponse>> getOrderDetailsByOrderId(@PathVariable String orderId) {
        return ResponseEntity.ok(DataResponse.<OrderDetailResponse>builder()
                .message("Get order details by order id success")
                .data(orderDetailService.getOrderDetailsByOrderId(orderId))
                .build());
    }

}
