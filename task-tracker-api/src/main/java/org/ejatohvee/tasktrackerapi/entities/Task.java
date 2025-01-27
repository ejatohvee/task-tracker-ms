package org.ejatohvee.tasktrackerapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "tasks_management", name= "t_tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "c_title", nullable = false)
    private String title;

    @Column(name = "c_description", nullable = false)
    private String description;

    @Column(name = "c_is_done", nullable = false)
    private boolean isDone;

    @Column(name = "c_is_done_time")
    private Timestamp isDoneTime;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;
}
