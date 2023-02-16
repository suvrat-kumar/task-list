package com.codurance.training.tasks.command;

import com.codurance.training.tasks.service.TaskService;

public class DeadlineCommandExecutorImpl implements DeadlineCommandExecutor{
    private final TaskService taskService;
    public DeadlineCommandExecutorImpl(TaskService taskService){
        this.taskService = taskService;
    }
    @Override
    public void deadLine(String commands) {
        taskService.deadLine(commands);
    }
}
