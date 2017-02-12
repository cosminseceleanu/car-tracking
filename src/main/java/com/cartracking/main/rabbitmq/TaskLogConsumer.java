package com.cartracking.main.rabbitmq;

import com.cartracking.main.rabbitmq.annotation.TaskLogListener;
import com.cartracking.main.rabbitmq.message.TaskLogMessage;
import com.cartracking.main.services.TaskLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TaskLogConsumer {

    private ObjectMapper objectMapper;

    private TaskLogService taskLogService;

    @Autowired
    public TaskLogConsumer(ObjectMapper objectMapper, TaskLogService taskLogService) {
        this.objectMapper = objectMapper;
        this.taskLogService = taskLogService;
    }

    private static final Log logger = LogFactory.getLog(TaskLogConsumer.class);

    @TaskLogListener
    public void processTaskLog(Message message) throws IOException {
        String messageString = new String(message.getBody());
        TaskLogMessage taskLogMessage = objectMapper.readValue(messageString, TaskLogMessage.class);
        taskLogService.add(taskLogMessage, 1);

        logger.info(messageString + "...from consumer");
    }
}
