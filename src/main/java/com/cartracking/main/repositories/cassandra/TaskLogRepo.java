package com.cartracking.main.repositories.cassandra;

import com.cartracking.main.entities.cassandra.TaskLog;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.core.WriteOptions;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskLogRepo implements com.cartracking.main.repositories.TaskLogRepo {
    private CassandraOperations cassandraOperations;

    @Autowired
    public TaskLogRepo(CassandraOperations cassandraOperations) {
        this.cassandraOperations = cassandraOperations;
    }

    public void add(TaskLog taskLog) {
        cassandraOperations.insert(taskLog, getWriteOptions());
    }

    @Override
    public List<TaskLog> find(long taskId) {
        Select select = QueryBuilder.select()
                .from(TaskLog.TABLE_NAME);
        select.where(QueryBuilder.eq("task_id", taskId));

        return cassandraOperations.select(select, TaskLog.class);
    }

    private WriteOptions getWriteOptions() {
        return WriteOptions.builder()
                .consistencyLevel(ConsistencyLevel.QUORUM)
                .build();
    }
}
