package org.ejatohvee.tasktrackermailsender.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ejatohvee.tasktrackermailsender.dtos.EmailDto;
import org.ejatohvee.tasktrackermailsender.services.MailSenderService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailListener {
    private final MailSenderService mailSenderService;

    @KafkaListener(topics = "EMAIL_SENDING_TASKS", containerFactory = "emailDtoContainerFactory")
    public void handleMessage(@Payload EmailDto emailDto, Acknowledgment acknowledgment) {
        try {
            mailSenderService.sendEmail(emailDto);
        } catch (Exception e) {
            log.error("Error: ", e);
        }
    }
}
