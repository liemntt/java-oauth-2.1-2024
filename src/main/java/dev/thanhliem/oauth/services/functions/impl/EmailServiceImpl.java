package dev.thanhliem.oauth.services.functions.impl;

import dev.thanhliem.oauth.services.functions.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final MailSender mailSender;
    private final MailProperties mailProperties;

    @Override
    public SimpleMailMessage send() {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("Liem");
        mail.setTo("icanandiwill187@gmail.com");
        mail.setSentDate(Date.from(Instant.now()));
        mail.setText("Hello, how are you today?");
        mail.setSubject("Subject modification");

        mailSender.send(mail);
        return mail;
    }
}
