package org.ejatohvee.tasktrackerscheduler.clients.auth;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "external.user")
public class AuthUser {
    private String username;
    private String password;
}
