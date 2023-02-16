package com.codurance.training.tasks;

import com.codurance.training.tasks.command.CommandExecutor;
import com.codurance.training.tasks.command.CommandExecutorImpl;
import com.codurance.training.tasks.db.TaskDb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import com.codurance.training.tasks.model.Task;
import com.codurance.training.tasks.service.*;

public final class TaskList{

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        new TaskRunner(in).run();
    }
}
