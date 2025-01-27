package org.ejatohvee.tasktrackerscheduler.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ejatohvee.tasktrackerscheduler.clients.TaskServiceClient;
import org.ejatohvee.tasktrackerscheduler.clients.UserServiceClient;
import org.ejatohvee.tasktrackerscheduler.clients.auth.AuthServiceClient;
import org.ejatohvee.tasktrackerscheduler.dtos.EmailDto;
import org.ejatohvee.tasktrackerscheduler.dtos.TaskDto;
import org.ejatohvee.tasktrackerscheduler.dtos.UserDto;
import org.ejatohvee.tasktrackerscheduler.kafka.EmailProducer;
import org.ejatohvee.tasktrackerscheduler.models.SchedulerEmail;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulerService {
    private final TaskServiceClient taskServiceClient;
    private final UserServiceClient userServiceClient;
    private final AuthServiceClient authServiceClient;
    private final EmailProducer emailProducer;

    @Scheduled(cron = "0 59 23 * * *", zone = "Europe/Moscow")
    public void createReportCompletedTasks() {
        String token = authServiceClient.getToken();
        int page = 0;

        List<UserDto> usersBatch;
        do {
            usersBatch = userServiceClient.getAllUsersBatch(page, token);

            for (UserDto user : usersBatch) {
                try {
                    EmailDto email = createEmailDto(user, token);
                    emailProducer.sendEmailDto(email);

                    log.info("Email report created for user {}. Body: {} {} {}", user.email(), email.to(), email.subject(), email.text());
                } catch (Exception e) {
                    log.info("Error creating email for user {}: {}", user.id(), e.getMessage());
                }
            }

            page++;
        }
        while (!usersBatch.isEmpty());
    }


    private EmailDto createEmailDto(UserDto user, String token) {
        SchedulerEmail schedulerEmail = new SchedulerEmail(user.email());
        List<TaskDto> tasks = taskServiceClient.getUsersTasks(user.id(), token);

        for (TaskDto task : tasks) {
            if (task.isDone()) {
                schedulerEmail.addDoneTask(task.title());
            } else {
                schedulerEmail.addNotDoneTask(task.title());
            }
        }

        StringBuilder text = new StringBuilder();

        if (schedulerEmail.getIsDoneAmount() > 0) {
            text.append(String.format("You have done %d task(s): %s ", schedulerEmail.getIsDoneAmount(), String.join(", ", schedulerEmail.getNotDoneTasksTitles())));
        }

        else if (schedulerEmail.getIsNotDoneAmount() > 0) {
            text.append(String.format("You haven't done %d task(s): %s", schedulerEmail.getIsNotDoneAmount(), String.join(", ", schedulerEmail.getNotDoneTasksTitles())));
        }

        else {
            text.append("Create some new tasks to do!");
        }

        return new EmailDto(schedulerEmail.getTo(), "Daily reminder!", text.toString());
    }
}
