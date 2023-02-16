package com.codurance.training.tasks.service;

import com.codurance.training.tasks.model.Task;

import java.util.List;
import java.util.Map;

public interface TaskService {
    void addTask(String project, String description, Long lastId);

    void addTask(String project, String id, String description);

    void setDone(String idString, boolean done);

    void deadLine(String commands);
}
