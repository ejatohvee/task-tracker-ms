package org.ejatohvee.tasktrackerapi.exceptions;

public class TaskOwnershipException extends RuntimeException {
    public TaskOwnershipException(String message) {
        super(message);
    }
}
