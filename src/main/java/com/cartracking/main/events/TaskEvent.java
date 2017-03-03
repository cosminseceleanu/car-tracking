package com.cartracking.main.events;

import com.cartracking.main.entities.Task;
import org.springframework.context.ApplicationEvent;

public class TaskEvent extends ApplicationEvent {
    private final Task task;
    private final boolean isNew;

    public TaskEvent(Task task, boolean isNew) {
        super(task);
        this.task = task;
        this.isNew = isNew;
    }

    public Task getTask() {
        return task;
    }

    public boolean isNew() {
        return isNew;
    }
}
