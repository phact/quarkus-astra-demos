package com.sestevez.mapper;

import java.util.Objects;

public class CreatureDto {

    private String type;
    private String name;
    private int age;

    public CreatureDto(String name, String type, int age) {
        this.name = name;
        this.type = type;
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
