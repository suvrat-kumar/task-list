package com.codurance.training.tasks;

import java.util.Date;

public final class Task {
    private final String id;
    private final String description;
    private Boolean done;

    private Date dueDate;

    public Task(String id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }

    public Task(String id, String description, boolean done, Date dueDate) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.dueDate = dueDate;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
