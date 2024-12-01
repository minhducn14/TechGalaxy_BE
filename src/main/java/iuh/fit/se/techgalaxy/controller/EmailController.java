package iuh.fit.se.techgalaxy.controller;


import iuh.fit.se.techgalaxy.dto.request.EmailRequest;
import iuh.fit.se.techgalaxy.service.impl.EmailServiceImpl;
import iuh.fit.se.techgalaxy.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class EmailController {
    private final EmailServiceImpl emailService;

    @Autowired
    public EmailController(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/email")
    public String sendEmail(@RequestBody EmailRequest request) {
        String emailLogin = SecurityUtil.getCurrentUserLogin().orElse(null);
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setOrderCode(request.getOrderCode());
        emailRequest.setPaymentInfo(request.getPaymentInfo());
        emailRequest.setShippingAddress(request.getShippingAddress());
        emailRequest.setOrderNumber(request.getOrderNumber());
        emailRequest.setSymbol(request.getSymbol());
        emailRequest.setInvoiceDate(LocalDate.now().toString());
        emailRequest.setInvoiceNumber(request.getInvoiceNumber());
        emailRequest.setCustomerName(request.getCustomerName());
        emailRequest.setTaxCode(request.getTaxCode());
        emailRequest.setSearchCode(request.getSearchCode());
        emailRequest.setProductVariantDetails(request.getProductVariantDetails());
        emailService.sendEmailFromTemplateSync(
                emailLogin,
                "Hóa đơn mua hàng",
                "email-template",
                emailRequest
        );

        return "ok";
    }
}
