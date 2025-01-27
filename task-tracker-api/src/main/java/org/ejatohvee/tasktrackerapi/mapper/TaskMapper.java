package org.ejatohvee.tasktrackerapi.mapper;

import org.ejatohvee.tasktrackerapi.dtos.EmailDto;
import org.ejatohvee.tasktrackerapi.dtos.requests.NewTaskPayload;
import org.ejatohvee.tasktrackerapi.dtos.responses.TaskResponse;
import org.ejatohvee.tasktrackerapi.entities.Task;
import org.ejatohvee.tasktrackerapi.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Timestamp;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "owner.username", target = "username")
    @Mapping(source = "done", target = "isDone")
    TaskResponse toTaskResponse(Task task);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "isDone", target = "done")
    Task toTask(NewTaskPayload newTaskPayload, boolean isDone, Timestamp isDoneTime, User owner);

    @Mapping(source = "owner.email", target = "to")
    @Mapping(source = "title", target = "subject")
    @Mapping(source = "description", target = "text")
    EmailDto toEmailDto(Task task);
}
