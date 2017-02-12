package com.cartracking.rest.controller;

import com.cartracking.main.entities.cassandra.TaskLog;
import com.cartracking.main.services.TaskLogService;
import com.cartracking.rest.resources.TaskLogListResource;
import com.cartracking.rest.resources.asm.TaskLogResourceListAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class TaskLogController {
    @Autowired
    private TaskLogService taskLogService;

    @Autowired
    private TaskLogResourceListAsm resourceListAsm;

    @RequestMapping(value = "/tasks/{id}/logs", method = RequestMethod.GET)
    public ResponseEntity<TaskLogListResource> get(@PathVariable long id) {
        List<TaskLog> taskLogs = taskLogService.get(id);

        return new ResponseEntity<>(resourceListAsm.toResource(taskLogs), HttpStatus.OK);
    }
}
