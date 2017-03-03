package com.cartracking.main.listener;

import com.cartracking.main.entities.Task;
import com.cartracking.main.events.TaskEvent;
import com.cartracking.main.services.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class AlertListener {

    private AlertService alertService;

    @Autowired
    public AlertListener(AlertService alertService) {
        this.alertService = alertService;
    }

    @EventListener
    public void onTaskEvent(TaskEvent event) {
        if (event.isNew()) {
            onNewTask(event.getTask());
            return;
        }

        onTaskUpdate(event.getTask());
    }

    private void onNewTask(Task task) {
        String message = String.format("Task-ul cu id %d a fost adaugat", task.getId());
        String title = "Un nou task a fost adaugat";
        alertService.add(title, message, task.getEmployee());
    }

    private void onTaskUpdate(Task task) {
        if (Task.Status.valueOf(task.getStatus()) == Task.Status.FINISHED) {
            return;
        }
        String message = String.format("Task-ul cu id %d a fost finalizat de catre %s",
                task.getId(), task.getEmployee().getName());
        String title = "Un task a fost finalizat";
        alertService.add(title, message, task.getEmployee().getAdmin());
    }
}
