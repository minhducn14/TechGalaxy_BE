package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.request.EmailRequest;

public interface EmailService {
    public void sendSimpleEmail();
    public void sendEmailSync(String to, String subject, String content, boolean isMultipart, boolean isHtml);

    public void sendEmailFromTemplateSync(
            String to,
            String subject,
            String templateName,
            EmailRequest emailRequest
    );
}
