package com.codurance.training.tasks.service;

import static java.lang.System.out;

public class HelperPrintServiceImpl implements HelperPrintService {
    @Override
    public void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  add task <project name> <task description> ID <task ID>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println("  deadline <task ID> <dd-MM-yyyy>");
        out.println("  today");
        out.println("  delete <task ID>");
        out.println("  view by date <dd-MM-yyyy>");
        out.println("  view by deadline <dd-MM-yyyy>");
        out.println("  view by project <project ID>");
        out.println();
    }

    @Override
    public void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}
