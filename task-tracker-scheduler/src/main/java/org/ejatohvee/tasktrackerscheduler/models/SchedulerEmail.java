package org.ejatohvee.tasktrackerscheduler.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class SchedulerEmail {
    private final String to;
    private int isDoneAmount;
    private final List<String> doneTasksTitles;
    private int isNotDoneAmount;
    private final List<String> notDoneTasksTitles;

    public SchedulerEmail(String to) {
        this.to = to;
        this.doneTasksTitles = new ArrayList<>();
        this.notDoneTasksTitles = new ArrayList<>();
    }

    public void addDoneTask(String title) {
        this.doneTasksTitles.add(title);
        this.isDoneAmount++;
    }

    public void addNotDoneTask(String title) {
        this.notDoneTasksTitles.add(title);
        this.isNotDoneAmount++;
    }
}
