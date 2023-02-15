package com.codurance.training.tasks.command;

import com.codurance.training.tasks.service.ProjectService;
import com.codurance.training.tasks.service.TaskService;
import com.codurance.training.tasks.utils.Utils;

public class AddCommandExecutorImpl implements AddCommandExecutor{

    private TaskService taskService;
    private ProjectService projectService;

    public AddCommandExecutorImpl(TaskService taskService, ProjectService projectService){
        this.projectService=projectService;
        this.taskService = taskService;
    }

    @Override
    public void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            projectService.addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split("ID", 2);
            String prj = subcommandRest[1].split(" ", 2)[0];
            if(projectTask.length == 1)
                taskService.addTask(prj, projectTask[0].replace(prj, ""), Utils.lastId++);
            if(projectTask.length == 2)
                taskService.addTask(prj, projectTask[1], projectTask[0].replace(prj, ""));
        }
    }
}
