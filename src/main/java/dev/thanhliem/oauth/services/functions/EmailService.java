package dev.thanhliem.oauth.services.functions;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    SimpleMailMessage send();
}
