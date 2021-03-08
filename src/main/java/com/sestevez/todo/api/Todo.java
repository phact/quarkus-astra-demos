package com.sestevez.todo.api;

import java.util.UUID;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Todo {
    private UUID id;
    private String title;
    private boolean completed;

    public Todo() {
    }

    public Todo(UUID id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
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
