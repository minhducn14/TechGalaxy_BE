package iuh.fit.se.techgalaxy.controller;


import iuh.fit.se.techgalaxy.dto.request.EmailRequest;
import iuh.fit.se.techgalaxy.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        emailRequest.setPaymentInfo("GGDuck");
        emailRequest.setShippingAddress("123 Đường ABC, Quận 1, TP.HCM");
        emailRequest.setOrderNumber("123456789");
        emailRequest.setSymbol("CAAAAAA");
        emailRequest.setInvoiceDate("12/11/2024");
        emailRequest.setInvoiceNumber("00012345");
        emailRequest.setCustomerName("GGDUCK");
        emailRequest.setTaxCode("1234567890");
        emailRequest.setSearchCode("ABCDEF123");
        emailRequest.setProductVariantDetails(null);
        emailService.sendEmailFromTemplateSync(
                "ggducvu@gmail.com",
                "Hóa đơn mua hàng",
                "email-template",
                emailRequest
        );

        return "ok";
    }
}
