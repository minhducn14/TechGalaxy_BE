package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.PaymentDTO;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.service.impl.PaymentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentServiceImpl paymentService;
    @Autowired
    public PaymentController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }
    @GetMapping("/vn-pay")
    public ResponseEntity<DataResponse<PaymentDTO.VNPayResponse>> pay(HttpServletRequest request) {
        return ResponseEntity.ok(DataResponse.<PaymentDTO.VNPayResponse>builder()
                .data(List.of(paymentService.createVnPayPayment(request)))
                .build());
    }
    @GetMapping("/vn-pay-callback")
    public ResponseEntity<DataResponse<PaymentDTO.VNPayResponse>> payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        if (status.equals("00")) {
            return ResponseEntity.ok(DataResponse.<PaymentDTO.VNPayResponse>builder()
                    .data(List.of(new PaymentDTO.VNPayResponse("00", "Success", "")))
                    .build());
        } else {
            return ResponseEntity.ok(DataResponse.<PaymentDTO.VNPayResponse>builder()
                    .data(List.of(new PaymentDTO.VNPayResponse("99", "Failed", "")))
                    .build());
        }
    }
}
