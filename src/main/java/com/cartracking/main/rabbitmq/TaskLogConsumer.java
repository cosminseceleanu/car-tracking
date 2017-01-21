package com.cartracking.main.rabbitmq;

import com.cartracking.main.entities.TaskLog;
import com.cartracking.main.rabbitmq.annotation.TaskLogListener;
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

    @Autowired
    public TaskLogConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private static final Log logger = LogFactory.getLog(TaskLogConsumer.class);

    @TaskLogListener
    public void processTaskLog(Message message) throws IOException {
        String messageString = new String(message.getBody());
        TaskLog taskLog = objectMapper.readValue(messageString, TaskLog.class);

        logger.info(messageString + "...from consumer");
    }
}
