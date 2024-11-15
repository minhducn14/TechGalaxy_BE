package iuh.fit.se.techgalaxy.service.impl;

import iuh.fit.se.techgalaxy.config.VNPAYConfig;
import iuh.fit.se.techgalaxy.dto.response.PaymentResponse;
import iuh.fit.se.techgalaxy.util.VNPayUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentServiceImpl {
    private final VNPAYConfig vnPayConfig;

    @Autowired
    public PaymentServiceImpl(VNPAYConfig vnPayConfig) {
        this.vnPayConfig = vnPayConfig;
    }

    public PaymentResponse.VNPayResponseCreate createVnPayPayment(HttpServletRequest request) {
        long amount = Integer.parseInt(request.getParameter("amount")) * 100L;
        String bankCode = request.getParameter("bankCode");
        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();
        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
        if (bankCode != null && !bankCode.isEmpty()) {
            vnpParamsMap.put("vnp_BankCode", bankCode);
        }
        vnpParamsMap.put("vnp_IpAddr", VNPayUtil.getIpAddress(request));
        String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);
        String hashData = VNPayUtil.getPaymentURL(vnpParamsMap, false);
        String vnpSecureHash = VNPayUtil.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentUrl = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;
        return PaymentResponse.VNPayResponseCreate.builder()
                .code("ok")
                .message("success")
                .paymentUrl(paymentUrl).build();
    }
}
