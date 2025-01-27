package org.ejatohvee.tasktrackerapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "users_management", name = "t_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "c_username", nullable = false, unique = true)
    private String username;

    @Column(name = "c_email", nullable = false, unique = true)
    private String email;

    @Column(name = "c_password", nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_authorities", schema = "users_management",
            joinColumns =
            @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "authority_id", referencedColumnName = "id")
    )
    private List<Authority> authorities;
}
