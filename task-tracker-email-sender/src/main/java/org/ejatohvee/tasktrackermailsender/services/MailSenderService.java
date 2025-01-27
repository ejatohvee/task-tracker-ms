package org.ejatohvee.tasktrackermailsender.services;

import lombok.RequiredArgsConstructor;
import org.ejatohvee.tasktrackermailsender.dtos.EmailDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    @Value("${spring.mail.username}")
    private String username;

    private final JavaMailSender emailSender;

    public void sendEmail(EmailDto emailDto) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(username);
        message.setTo(emailDto.to());
        message.setSubject(emailDto.subject());
        message.setText(emailDto.text());

        emailSender.send(message);
    }
}
