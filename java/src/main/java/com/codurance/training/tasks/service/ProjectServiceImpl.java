package com.codurance.training.tasks.service;

import com.codurance.training.tasks.db.TaskDb;
import com.codurance.training.tasks.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProjectServiceImpl implements ProjectService{
    @Override
    public void addProject(String name){
        TaskDb.getTasks().put(name, new ArrayList<Task>());
    }
}
