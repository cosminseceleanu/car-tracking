package com.cartracking.websocket.controller;

import com.cartracking.main.entities.cassandra.TaskLog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    private static final Log logger = LogFactory.getLog(WebSocketController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/task.logs.{taskId}")
    public void test(@DestinationVariable long taskId, TaskLog taskLog) throws Exception {
        logger.info(taskId);
//        messagingTemplate.convertAndSend("/topic/task.logs." + taskId, taskLog);
        logger.info("after messages was sent");
    }
}
