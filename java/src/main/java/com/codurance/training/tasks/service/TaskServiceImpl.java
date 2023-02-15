package com.codurance.training.tasks.service;

import com.codurance.training.tasks.db.TaskDb;
import com.codurance.training.tasks.model.Task;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import static com.codurance.training.tasks.utils.Utils.*;
import static java.lang.System.out;

public class TaskServiceImpl implements TaskService{

    @Override
    public void addTask(String project, String description, Long lastId) {
        List<Task> projectTasks = TaskDb.getTasks().get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectTasks.add(new Task(lastId.toString(), description, false));
    }

    @Override
    public void addTask(String project, String id, String description) {
        if(!isAlphaNumeric(id.trim())) {
            out.println("Invalid id = " + id);
            return;
        }
        List<Task> projectTasks = TaskDb.getTasks().get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectTasks.add(new Task(id, description, false));
    }

    @Override
    public void setDone(String idString, boolean done) {
        Integer id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : TaskDb.getTasks().entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId().equals(id.toString())) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
    }

    @Override
    public void deadLine(String commands) {
        String[] idAndDate = commands.split(" ");
        Long id = Long.parseLong(idAndDate[0]);
        TaskDb.getTasks().forEach((k, v) -> {
            v.forEach(task -> {
                if(task.getId().equals(id.toString())){
                    try {
                        task.setDueDate(df.parse(idAndDate[1]));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            } );
        });
    }

}
