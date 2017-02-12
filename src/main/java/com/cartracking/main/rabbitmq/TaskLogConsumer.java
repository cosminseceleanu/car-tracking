package com.cartracking.main.rabbitmq;

import com.cartracking.main.entities.Task;
import com.cartracking.main.rabbitmq.annotation.TaskLogListener;
import com.cartracking.main.rabbitmq.message.TaskLogMessage;
import com.cartracking.main.repositories.TaskRepo;
import com.cartracking.main.services.TaskLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TaskLogConsumer {

    private ObjectMapper objectMapper;

    private TaskLogService taskLogService;

    private TaskRepo taskRepo;

    @Autowired
    public TaskLogConsumer(ObjectMapper objectMapper, TaskLogService taskLogService, TaskRepo taskRepo) {
        this.objectMapper = objectMapper;
        this.taskLogService = taskLogService;
        this.taskRepo = taskRepo;
    }

    @TaskLogListener
    public void processTaskLog(Message message) throws IOException {
        String messageString = new String(message.getBody());
        TaskLogMessage taskLogMessage = objectMapper.readValue(messageString, TaskLogMessage.class);
        saveMessages(taskLogMessage);
    }

    private void saveMessages(TaskLogMessage taskLogMessage) {
        List<Task> activeTasks = taskRepo.findByStatusAndEmployee(taskLogMessage.getEmployeeId(),
                Task.Status.IN_PROGRESS.toString());

        activeTasks.forEach((Task task) -> taskLogService.add(taskLogMessage, task));
    }
}
