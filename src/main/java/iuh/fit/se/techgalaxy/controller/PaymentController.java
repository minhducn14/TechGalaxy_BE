package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.PaymentResponse;
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
    public ResponseEntity<DataResponse<PaymentResponse.VNPayResponseCreate>> pay(HttpServletRequest request) {
        return ResponseEntity.ok(DataResponse.<PaymentResponse.VNPayResponseCreate>builder()
                .data(List.of(paymentService.createVnPayPayment(request)))
                .build());
    }

    @GetMapping("/vn-pay-callback")
    public ResponseEntity<DataResponse<PaymentResponse.VNPayResponse>> payCallbackHandler(HttpServletRequest request) {
        String amount = request.getParameter("vnp_Amount");
        String bankCode = request.getParameter("vnp_BankCode");
        String bankTranNo = request.getParameter("vnp_BankTranNo");
        String cardType = request.getParameter("vnp_CardType");
        String orderInfo = request.getParameter("vnp_OrderInfo");
        String payDate = request.getParameter("vnp_PayDate");
        String responseCode = request.getParameter("vnp_ResponseCode");
        String transactionNo = request.getParameter("vnp_TransactionNo");
        String transactionStatus = request.getParameter("vnp_TransactionStatus");
        String txnRef = request.getParameter("vnp_TxnRef");
        String secureHash = request.getParameter("vnp_SecureHash");

        // Tạo đối tượng VNPayResponse
        PaymentResponse.VNPayResponse vnPayResponse = PaymentResponse.VNPayResponse.builder()
                .txnRef(txnRef)
                .amount(amount)
                .orderInfo(orderInfo)
                .responseCode(responseCode)
                .transactionNo(transactionNo)
                .bankCode(bankCode)
                .bankTranNo(bankTranNo)
                .cardType(cardType)
                .payDate(payDate)
                .transactionStatus(transactionStatus)
                .secureHash(secureHash)
                .signValue("")
                .build();

        return ResponseEntity.ok(DataResponse.<PaymentResponse.VNPayResponse>builder()
                .data(List.of(vnPayResponse))
                .build());
    }
}
