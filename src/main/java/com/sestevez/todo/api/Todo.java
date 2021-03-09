package com.sestevez.todo.api;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Todo {
    private String id;
    private String title;
    private boolean completed;

    public Todo() {
    }

    public Todo(String id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
