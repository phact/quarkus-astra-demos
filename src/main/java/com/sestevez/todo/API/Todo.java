package com.sestevez.todo.API;

import javax.json.bind.annotation.JsonbProperty;
import java.util.UUID;

public class Todo {

    public Todo() {
    }

    @JsonbProperty()
    public UUID id;
    @JsonbProperty()
    public String title;
    @JsonbProperty()
    public boolean completed;

    public Todo(UUID id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
