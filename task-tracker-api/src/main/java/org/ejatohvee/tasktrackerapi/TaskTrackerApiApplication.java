package org.ejatohvee.tasktrackerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TaskTrackerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskTrackerApiApplication.class, args);
    }

}
