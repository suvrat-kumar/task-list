package com.codurance.training.tasks.command;

import com.codurance.training.tasks.service.TaskPrintService;
import com.codurance.training.tasks.utils.Utils;
import com.codurance.training.tasks.model.Task;
import com.codurance.training.tasks.service.PrintService;
import com.codurance.training.tasks.service.ProjectService;
import com.codurance.training.tasks.service.TaskService;

import java.util.List;
import java.util.Map;

public class CommandExecutorImpl implements CommandExecutor {

    private PrintService printService;

    public CommandExecutorImpl(PrintService printService) {
        this.printService = printService;
    }

    @Override
    public void help() {
        printService.help();
    }

    @Override
    public void error(String command) {
        printService.error(command);
    }

}
