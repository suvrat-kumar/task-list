package com.codurance.training.tasks.command;

import com.codurance.training.tasks.service.TaskPrintService;
import com.codurance.training.tasks.service.TaskService;

public class ShowCommandExecutorImpl implements ShowCommandExecutor{

    private TaskPrintService taskPrintService;

    public ShowCommandExecutorImpl(TaskPrintService taskPrintService){
        this.taskPrintService = taskPrintService;
    }

    @Override
    public void show() {
        taskPrintService.show();
    }

    @Override
    public void view(String command) {
        taskPrintService.view(command);
    }
}
