package org.ejatohvee.tasktrackerscheduler.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ejatohvee.tasktrackerscheduler.dtos.EmailDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailProducer {
    private final KafkaTemplate<String, EmailDto> schedulerEmailProducer;

    public void sendEmailDto(EmailDto emailDto) {
        try {
            schedulerEmailProducer.send("EMAIL_SENDING_TASKS", emailDto);
        } catch (Exception e) {
            log.error("Error occurred ", e);
        }
        log.info("Email sent successfully!");
    }
}
