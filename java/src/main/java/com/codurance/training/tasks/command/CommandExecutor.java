package com.codurance.training.tasks.command;

public interface CommandExecutor {
    void help();

    void error(String command);

}
