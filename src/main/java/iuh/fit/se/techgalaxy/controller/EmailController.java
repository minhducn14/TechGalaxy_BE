package iuh.fit.se.techgalaxy.controller;


import iuh.fit.se.techgalaxy.dto.request.EmailRequest;
import iuh.fit.se.techgalaxy.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/email")
    public String sendEmail(@RequestBody EmailRequest request) {
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setOrderCode("SP.190BHV.T23.12.000931");
        emailRequest.setPaymentInfo(request.getPaymentInfo());
        emailRequest.setShippingAddress(request.getShippingAddress());
        emailRequest.setOrderNumber(request.getOrderNumber());
        emailRequest.setSymbol("CAAAAAA");
        emailRequest.setInvoiceDate(LocalDate.now().toString());
        emailRequest.setInvoiceNumber(request.getInvoiceNumber());
        emailRequest.setCustomerName(request.getCustomerName());
        emailRequest.setTaxCode(request.getTaxCode());
        emailRequest.setSearchCode("ABCDEF123");
        emailRequest.setProductVariantDetails(request.getProductVariantDetails());
        emailService.sendEmailFromTemplateSync(
                "ggducvu@gmail.com",
                "Hóa đơn mua hàng",
                "email-template",
                emailRequest
        );

        return "ok";
    }
}
