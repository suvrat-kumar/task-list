package com.codurance.training.tasks.command;

import com.codurance.training.tasks.service.TaskPrintService;

public class TodayCommandExecutorImpl implements TodayCommandExecutor{
    private TaskPrintService taskPrintService;
    public TodayCommandExecutorImpl(TaskPrintService taskPrintService){
        this.taskPrintService = taskPrintService;
    }
    @Override
    public void today() {
        taskPrintService.today();
    }
}
