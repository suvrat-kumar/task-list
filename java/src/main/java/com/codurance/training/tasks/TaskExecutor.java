package com.codurance.training.tasks;

import com.codurance.training.tasks.command.*;
import com.codurance.training.tasks.db.TaskDb;
import com.codurance.training.tasks.model.Task;
import com.codurance.training.tasks.service.*;

import java.util.List;
import java.util.Map;

public class TaskExecutor {

    public static void execute(String commandLine) {
        PrintService printService = new PrintServiceImpl();
        ProjectService projectService = new ProjectServiceImpl();
        TaskService taskService = new TaskServiceImpl();
        TaskPrintService taskPrintService = new TaskPrintServiceImpl();

        CommandExecutor commandExecutor = new CommandExecutorImpl(printService);
        AddCommandExecutor addCommandExecutor = new AddCommandExecutorImpl(taskService, projectService);
        CheckCommandExecutor checkCommandExecutor = new CheckCommandExecutorImpl(taskService);
        TodayCommandExecutor todayCommandExecutor = new TodayCommandExecutorImpl(taskPrintService);
        ShowCommandExecutor showCommandExecutor = new ShowCommandExecutorImpl(taskPrintService);
        DeadlineCommandExecutor deadlineCommandExecutor = new DeadlineCommandExecutorImpl(taskService);

        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];

        switch (command) {
            case "show":
                showCommandExecutor.show();
                break;
            case "add":
                addCommandExecutor.add(commandRest[1]);
                break;
            case "check":
                checkCommandExecutor.check(commandRest[1]);
                break;
            case "uncheck":
                checkCommandExecutor.uncheck(commandRest[1]);
                break;
            case "help":
                commandExecutor.help();
                break;
            case "deadline":
                deadlineCommandExecutor.deadLine(commandRest[1]);
                break;
            case "today":
                todayCommandExecutor.today();
                break;
            case "view":
                showCommandExecutor.view(commandRest[1]);
                break;
            default:
                commandExecutor.error(command);
                break;
        }
    }
}
