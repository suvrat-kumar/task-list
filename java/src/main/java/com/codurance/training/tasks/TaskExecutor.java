package com.codurance.training.tasks;

import com.codurance.training.tasks.command.*;
import com.codurance.training.tasks.service.*;

public class TaskExecutor {
    private final HelperPrintService helperPrintService;
    private final ProjectService projectService;
    private final TaskService taskService;
    private final TaskPrintService taskPrintService;

    private final CommandExecutor commandExecutor;
    private final AddCommandExecutor addCommandExecutor;
    private final CheckCommandExecutor checkCommandExecutor;
    private final TodayCommandExecutor todayCommandExecutor;
    private final ShowCommandExecutor showCommandExecutor;
    private final DeadlineCommandExecutor deadlineCommandExecutor;

    public TaskExecutor(){
        helperPrintService = new HelperPrintServiceImpl();
        projectService = new ProjectServiceImpl();
        taskService = new TaskServiceImpl();
        taskPrintService = new TaskPrintServiceImpl();

        commandExecutor = new CommandExecutorImpl(helperPrintService);
        addCommandExecutor = new AddCommandExecutorImpl(taskService, projectService);
        checkCommandExecutor = new CheckCommandExecutorImpl(taskService);
        todayCommandExecutor = new TodayCommandExecutorImpl(taskPrintService);
        showCommandExecutor = new ShowCommandExecutorImpl(taskPrintService);
        deadlineCommandExecutor = new DeadlineCommandExecutorImpl(taskService);
    }

    public void execute(String commandLine) {

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
