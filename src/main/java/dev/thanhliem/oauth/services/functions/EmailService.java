package dev.thanhliem.oauth.services.functions;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    SimpleMailMessage send(String sendTo, String subject, String text);

    void sendResetPassword(String sendTo, String username, String newPassword);

    void sendNewPassword(String sendTo, String username, String newPassword);
}
