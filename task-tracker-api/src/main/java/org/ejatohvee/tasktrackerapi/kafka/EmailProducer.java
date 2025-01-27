package org.ejatohvee.tasktrackerapi.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ejatohvee.tasktrackerapi.dtos.EmailDto;
import org.ejatohvee.tasktrackerapi.mapper.TaskMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailProducer {
    private final KafkaTemplate<String, EmailDto> taskInfoProducer;
    private final TaskMapper taskMapper;

    public void sendEmailDto(EmailDto emailDto) {
        try {
            taskInfoProducer.send("EMAIL_SENDING_TASKS", emailDto);
        } catch (Exception e) {
            log.error("Error occurred ", e);
        }
        log.info("Email sent successfully!");
    }
}