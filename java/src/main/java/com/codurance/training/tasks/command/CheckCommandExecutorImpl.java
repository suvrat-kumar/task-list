package com.codurance.training.tasks.command;

import com.codurance.training.tasks.service.TaskService;

public class CheckCommandExecutorImpl  implements CheckCommandExecutor{
    private TaskService taskService;

    public CheckCommandExecutorImpl(TaskService taskService){
        this.taskService = taskService;
    }

    @Override
    public void check(String idString) {
        taskService.setDone(idString, true);
    }

    @Override
    public void uncheck(String idString) {
        taskService.setDone(idString, false);
    }
}
