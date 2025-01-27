package org.ejatohvee.tasktrackerapi.exceptions;

public record ApiError(int status, String message) {
}
