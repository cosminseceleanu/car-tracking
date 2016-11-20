package com.cartracking.websocket.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class TaskLogController {
    private static final Log logger = LogFactory.getLog(TaskLogController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/tasks")
    public void test(String test) throws Exception {
        logger.info(test);
        messagingTemplate.convertAndSend("/queue/tasks", "test");
        messagingTemplate.convertAndSend("/topic/tasks.position.1", "test from topic 1");
        messagingTemplate.convertAndSend("/topic/tasks.position.2", "test from topic 2");
        logger.info("after messages was sent");
    }
}
