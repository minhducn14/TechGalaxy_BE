package iuh.fit.se.techgalaxy.service.impl;
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
            System.out.println("ERROR SEND EMAIL: " + e);
        }
    }
    public void sendEmailFromTemplateSync(
            String to,
            String subject,
            String templateName,
            String orderCode,
            String paymentInfo,
            String shippingAddress,
            String orderNumber,
            String symbol,
            String invoiceDate,
            String invoiceNumber,
            String customerName,
            String taxCode,
            String searchCode
    ) {
        Context context = new Context();
        context.setVariable("orderCode", orderCode);
        context.setVariable("paymentInfo", paymentInfo);
        context.setVariable("shippingAddress", shippingAddress);
        context.setVariable("orderNumber", orderNumber);
        context.setVariable("symbol", symbol);
        context.setVariable("invoiceDate", invoiceDate);
        context.setVariable("invoiceNumber", invoiceNumber);
        context.setVariable("customerName", customerName);
        context.setVariable("taxCode", taxCode);
        context.setVariable("searchCode", searchCode);

        String content = templateEngine.process(templateName, context);
        this.sendEmailSync(to, subject, content, false, true);
    }



    public void sendEmailFromTemplateSync(String to, String subject, String templateName) {
        Context context = new Context();
        String content = templateEngine.process(templateName, context);
        this.sendEmailSync(to, subject, content, false, true);
    }
}
