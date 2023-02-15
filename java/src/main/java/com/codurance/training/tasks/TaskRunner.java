package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import static com.codurance.training.tasks.TaskExecutor.execute;
import static java.lang.System.out;

public class TaskRunner  implements Runnable {

    private final BufferedReader in;

    private static final String QUIT = "quit";

    public TaskRunner(BufferedReader reader) {
        this.in = reader;
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
}