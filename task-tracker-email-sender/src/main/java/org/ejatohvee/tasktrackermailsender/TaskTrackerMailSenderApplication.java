package org.ejatohvee.tasktrackermailsender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TaskTrackerMailSenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskTrackerMailSenderApplication.class, args);
    }

}
