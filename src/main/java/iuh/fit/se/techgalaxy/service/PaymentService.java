package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.response.PaymentResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface PaymentService {

    public PaymentResponse.VNPayResponse createVnPayPayment(HttpServletRequest request);

}
