package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static com.codurance.training.tasks.Utils.*;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private final BufferedReader in;
    private final PrintWriter out;

    private final SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    private Long lastId = 0l;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(in, out).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
    }

    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            execute(command);
        }
    }

    private void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                show();
                break;
            case "add":
                add(commandRest[1]);
                break;
            case "check":
                check(commandRest[1]);
                break;
            case "uncheck":
                uncheck(commandRest[1]);
                break;
            case "help":
                help();
                break;
            case "deadline":
                deadLine(commandRest[1]);
                break;
            case "today":
                today();
                break;
            case "view":
                view(commandRest[1]);
                break;
            default:
                error(command);
                break;
        }
    }

    private void view(final String s) {
        final String[] remainArgs = s.split(" ", 3);
        if(!remainArgs[0].equals("by")){
            out.println("Invalid args");
            return;
        }
        if(remainArgs[1].equals("date") || remainArgs[1].equals("deadline")){
            try {
                final Date date = df.parse(remainArgs[2]);
                tasks.forEach((k, v) -> {
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
            tasks.forEach((k, v) -> {
                if(k.equals(remainArgs[2])){
                    v.forEach(task -> {
                        out.printf("    [%c] %s: %s%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), (task.getDueDate() == null) ? "" : " " + df.format(task.getDueDate()));
                    });
                }
            });
        }
    }

    private void delete(final String id) {
        if(id.trim().contains(" ")){
            out.println(" Invalid id");
            return;
        }
        tasks.forEach((k, v) -> {
            v.removeIf(task -> task.getId().equals(id));
        });

    }

    private void today() {
        final Date today = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        tasks.forEach((k, v) -> {
            v.forEach(task -> {
                if(task.getDueDate().compareTo(today) == 0)
                    out.printf("    [%c] %s: %s%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), (task.getDueDate() == null) ? "" : " " + df.format(task.getDueDate()));
            });
        });
    }

    private void deadLine(String commands) {
        String[] idAndDate = commands.split(" ");
        Long id = Long.parseLong(idAndDate[0]);
        tasks.forEach((k, v) -> {
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

    private void show() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %s: %s%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), (task.getDueDate() == null) ? "" : " " + df.format(task.getDueDate()));
            }
            out.println();
        }
    }

    private void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split("ID", 2);
            String prj = subcommandRest[1].split(" ", 2)[0];
            if(projectTask.length == 1)
                addTask(prj, projectTask[0].replace(prj, ""));
            if(projectTask.length == 2)
                addTask(prj, projectTask[1], projectTask[0].replace(prj, ""));
        }
    }

    private void addProject(String name) {
        tasks.put(name, new ArrayList<Task>());
    }

    private void addTask(String project, String description) {
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        lastId = nextId(lastId);
        projectTasks.add(new Task(lastId.toString(), description, false));
    }

    private void addTask(String project, String id, String description) {
        if(!isAlphaNumeric(id.trim())) {
            out.println("Invalid id = " + id);
            return;
        }
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectTasks.add(new Task(id, description, false));
    }

    private void check(String idString) {
        setDone(idString, true);
    }

    private void uncheck(String idString) {
        setDone(idString, false);
    }

    private void setDone(String idString, boolean done) {
        Integer id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
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

    private void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  add task <project name> <task description> ID <task ID>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println("  deadline <task ID> <dd-MM-yyyy>");
        out.println("  delete <task ID>");
        out.println("  view by date <dd-MM-yyyy>");
        out.println("  view by deadline <dd-MM-yyyy>");
        out.println("  view by project <project ID>");
        out.println();
    }

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}
