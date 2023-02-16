package com.codurance.training.tasks.service;

import com.codurance.training.tasks.db.TaskDb;
import com.codurance.training.tasks.model.Task;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.codurance.training.tasks.utils.Utils.df;
import static java.lang.System.out;

public class TaskPrintServiceImpl implements TaskPrintService{

    @Override
    public void show() {
        for (Map.Entry<String, List<Task>> project : TaskDb.getTasks().entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %s: %s%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), (task.getDueDate() == null) ? "" : " " + df.format(task.getDueDate()));
            }
            out.println();
        }
    }

    @Override
    public void view(final String command) {
        final String[] remainArgs = command.split(" ", 3);
        if(!remainArgs[0].equals("by")){
            out.println("Invalid args");
            return;
        }
        if(remainArgs[1].equals("date") || remainArgs[1].equals("deadline")){
            try {
                final Date date = df.parse(remainArgs[2]);
                TaskDb.getTasks().forEach((k, v) -> {
                    v.forEach(task -> {
                        if(task.getDueDate() != null) {
                            if (task.getDueDate().compareTo(date) == 0)
                                out.printf("    [%c] %s: %s%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), (task.getDueDate() == null) ? "" : " " + df.format(task.getDueDate()));
                        }
                    });
                });
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(remainArgs[1].equals("project")){
            TaskDb.getTasks().forEach((k, v) -> {
                if(k.equals(remainArgs[2])){
                    v.forEach(task -> {
                        out.printf("    [%c] %s: %s%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), (task.getDueDate() == null) ? "" : " " + df.format(task.getDueDate()));
                    });
                }
            });
        }
    }

    @Override
    public void today() {
        final Date today = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        TaskDb.getTasks().forEach((k, v) -> {
            v.forEach(task -> {
                if(task.getDueDate() != null){
                    if(task.getDueDate().compareTo(today) == 0)
                        out.printf("    [%c] %s: %s%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), (task.getDueDate() == null) ? "" : " " + df.format(task.getDueDate()));
                }
            });
        });
    }
}
