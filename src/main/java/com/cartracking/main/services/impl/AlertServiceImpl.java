package com.cartracking.main.services.impl;

import com.cartracking.main.entities.Alert;
import com.cartracking.main.entities.User;
import com.cartracking.main.repositories.AlertRepo;
import com.cartracking.main.services.AlertService;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlertServiceImpl implements AlertService {

    private AlertRepo alertRepo;
    private SimpMessagingTemplate messagingTemplate;
    private final static String alertTopic = "/topic/user.*.alerts";

    @Autowired
    public AlertServiceImpl(AlertRepo alertRepo, SimpMessagingTemplate messagingTemplate) {
        this.alertRepo = alertRepo;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public Page<Alert> search(Predicate predicate, Pageable pageable) {
        return alertRepo.findAll(predicate, pageable);
    }

    @Transactional
    @Override
    public void add(String title, String message, User user) {
        Alert alert = new Alert();
        alert.setMessage(message);
        alert.setTitle(title);
        alert.setUser(user);
        alertRepo.save(alert);
        sendAlert(alert);
    }

    private void sendAlert(Alert alert) {
        AlertMessage alertMessage = new AlertMessage();
        alertMessage.title = alert.getTitle();
        alertMessage.message = alert.getMessage();
        messagingTemplate.convertAndSend(alertTopic, alertMessage);
    }

    private class AlertMessage {
        public String message;
        public String title;
    }
}
