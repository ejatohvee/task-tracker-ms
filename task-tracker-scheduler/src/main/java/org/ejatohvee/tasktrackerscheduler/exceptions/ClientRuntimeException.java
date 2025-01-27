package org.ejatohvee.tasktrackerscheduler.exceptions;

import org.springframework.http.HttpStatusCode;

public class ClientRuntimeException extends RuntimeException {
    public ClientRuntimeException(HttpStatusCode code, String body) {
        super("Client error: " + code + ", Body of error: " + body);
    }
}
