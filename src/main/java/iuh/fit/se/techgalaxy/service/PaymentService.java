package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.PaymentDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface PaymentService {

    public PaymentDTO.VNPayResponse createVnPayPayment(HttpServletRequest request);

}
