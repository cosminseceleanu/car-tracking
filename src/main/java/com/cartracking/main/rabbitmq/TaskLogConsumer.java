package com.cartracking.main.rabbitmq;

import com.cartracking.main.entities.TaskLog;
import com.cartracking.main.rabbitmq.annotation.TaskLogListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class TaskLogConsumer {

    private static final Log logger = LogFactory.getLog(TaskLogConsumer.class);

    @TaskLogListener
    public void processTaskLog(@Payload TaskLog taskLog) {
        logger.info(taskLog.getMessage() + "...from consumer");
    }
}
