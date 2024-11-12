package iuh.fit.se.techgalaxy.service;

public interface EmailService {
    public void sendSimpleEmail();
    public void sendEmailSync(String to, String subject, String content, boolean isMultipart, boolean isHtml);

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
    );
}
