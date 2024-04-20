package dev.thanhliem.oauth.services.functions.impl;

import dev.thanhliem.oauth.constants.Constants;
import dev.thanhliem.oauth.services.functions.EmailService;
import dev.thanhliem.oauth.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final MailSender mailSender;

    @Override
    public SimpleMailMessage send(String sendTo, String subject, String text) {
        if (Utils.nullOrBlank(sendTo)) {
            throw new IllegalArgumentException("Invalid sendTo");
        }
        if (Utils.nullOrBlank(subject)) {
            throw new IllegalArgumentException("Invalid subject");
        }
        if (Utils.nullOrBlank(text)) {
            throw new IllegalArgumentException("Invalid text");
        }
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(Constants.APP_NAME);
        mail.setTo(sendTo);
        mail.setSentDate(Date.from(Instant.now()));
        mail.setText(text);
        mail.setSubject(subject);

        mailSender.send(mail);
        log.info("[send] {}", Utils.toJson(mail));
        return mail;
    }

    @Override
    public void sendResetPassword(String sendTo, String username, String newPassword) {
        validate(sendTo, username, newPassword);
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(Constants.APP_NAME);
        mail.setTo(sendTo);
        var now = OffsetDateTime.now();
        mail.setSentDate(Date.from(now.toInstant()));
        String text = """
            Dear %s,
            We have reset your password as requested on %s.
            Please find your new temporary password below.
            For security reasons, we strongly recommend that you change this temporary password immediately after logging in.
            Your new temporary password: %s
            To change your password, please follow these steps:
                1. Log in to your account using the password provided.
                2. Navigate to the 'Settings' section of your account.
                3. Click on 'Change Password'.
                4. Follow the prompts to enter your new password.
            
            If you encounter any issues or need further assistance, please do not hesitate to contact our support team.
            Thanks,
            %s Support Team
            """.formatted(username, DateTimeFormatter.RFC_1123_DATE_TIME.format(now), newPassword, Constants.APP_NAME);
        mail.setText(text);
        mail.setSubject("Your %s account - Password change".formatted(Constants.APP_NAME));

        mailSender.send(mail);
        log.info("[sendResetPassword] {}", Utils.toJson(mail));
    }

    private void validate(String sendTo, String username, String newPassword) {
        if (Utils.nullOrBlank(sendTo)) {
            throw new IllegalArgumentException("Invalid sendTo");
        }
        if (Utils.nullOrBlank(username)) {
            throw new IllegalArgumentException("Invalid username");
        }
        if (Utils.nullOrBlank(newPassword)) {
            throw new IllegalArgumentException("Invalid newPassword");
        }
    }

    @Override
    public void sendNewPassword(String sendTo, String username, String newPassword) {
        validate(sendTo, username, newPassword);
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(Constants.APP_NAME);
        mail.setTo(sendTo);
        var now = OffsetDateTime.now();
        mail.setSentDate(Date.from(now.toInstant()));
        String text = """
            Dear %s,
            We are reaching out to confirm that the password for your account has been successfully changed on %s.
            If you did not initiate this change, please contact our support team immediately to ensure the security of your account.
            
            If you encounter any issues or need further assistance, please do not hesitate to contact our support team.
            Thanks,
            %s Support Team
            """.formatted(username, DateTimeFormatter.RFC_1123_DATE_TIME.format(now), Constants.APP_NAME);
        mail.setText(text);
        mail.setSubject("Your %s account - Password Change Confirmation".formatted(Constants.APP_NAME));

        mailSender.send(mail);
        log.info("[sendNewPassword] {}", Utils.toJson(mail));
    }
}
