package iuh.fit.se.techgalaxy.controller;


import iuh.fit.se.techgalaxy.service.EmailService;
import iuh.fit.se.techgalaxy.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    private final EmailServiceImpl emailService;

    @Autowired
    public EmailController(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/email")
    public String sendSimpleEmail() {
        emailService.sendEmailFromTemplateSync(
                "ggducvu@gmail.com",
                "Hóa đơn mua hàng",
                "email-template",
                "SP.190BHV.T23.12.000931",
                "GGDuck",
                "123 Đường ABC, Quận 1, TP.HCM",
                "123456789",
                "CAAAAAA",
                "12/11/2024",
                "00012345",
                "GGDUCK",
                "1234567890",
                "ABCDEF123"
        );

        return "ok";
    }
}
