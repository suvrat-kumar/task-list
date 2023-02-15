package com.codurance.training.tasks.command;

public interface CheckCommandExecutor {

    void check(String idString);

    void uncheck(String idString);
}
