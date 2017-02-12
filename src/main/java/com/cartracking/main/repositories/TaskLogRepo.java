package com.cartracking.main.repositories;

import com.cartracking.main.entities.cassandra.TaskLog;

import java.util.List;

public interface TaskLogRepo {
    void add(TaskLog taskLog);

    List<TaskLog> find(long taskId);
}
