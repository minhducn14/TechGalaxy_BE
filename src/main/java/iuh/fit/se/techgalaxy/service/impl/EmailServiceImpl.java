package iuh.fit.se.techgalaxy.service.impl;
import iuh.fit.se.techgalaxy.dto.request.EmailRequest;
import iuh.fit.se.techgalaxy.exception.AppException;
import iuh.fit.se.techgalaxy.exception.ErrorCode;
import iuh.fit.se.techgalaxy.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl  implements EmailService {
    private final MailSender mailSender;
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Autowired
    public EmailServiceImpl(MailSender mailSender, JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendSimpleEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("congtyviethan1234@gmail.com");
        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World from Spring Boot Email");
        this.mailSender.send(msg);
    }

    @Override
    public void sendEmailSync(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content, isHtml);
            this.javaMailSender.send(mimeMessage);
        } catch (MailException | MessagingException e) {
            new AppException(ErrorCode.FAILED_SEND_EMAIL);
        }
    }
    public void sendEmailFromTemplateSync(
            String to,
            String subject,
            String templateName,
            EmailRequest emailRequest
    ) {
        Context context = new Context();
        context.setVariable("orderCode", emailRequest.getOrderCode());
        context.setVariable("paymentInfo", emailRequest.getPaymentInfo());
        context.setVariable("shippingAddress", emailRequest.getShippingAddress());
        context.setVariable("orderNumber", emailRequest.getOrderNumber());
        context.setVariable("symbol", emailRequest.getSymbol());
        context.setVariable("invoiceDate", emailRequest.getInvoiceDate());
        context.setVariable("invoiceNumber", emailRequest.getInvoiceNumber());
        context.setVariable("customerName", emailRequest.getCustomerName());
        context.setVariable("taxCode", emailRequest.getTaxCode());
        context.setVariable("searchCode", emailRequest.getSearchCode());
        context.setVariable("productVariantDetails", emailRequest.getProductVariantDetails());

        String content = templateEngine.process(templateName, context);
        this.sendEmailSync(to, subject, content, false, true);
    }



    public void sendEmailFromTemplateSync(String to, String subject, String templateName) {
        Context context = new Context();
        String content = templateEngine.process(templateName, context);
        this.sendEmailSync(to, subject, content, false, true);
    }
}
