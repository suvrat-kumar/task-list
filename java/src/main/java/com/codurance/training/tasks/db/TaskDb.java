package com.codurance.training.tasks.db;

import com.codurance.training.tasks.model.Task;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskDb {
    private static final Map<String, List<Task>> tasks = new LinkedHashMap<>();

    public static Map<String, List<Task>> getTasks() {
        return tasks;
    }
}