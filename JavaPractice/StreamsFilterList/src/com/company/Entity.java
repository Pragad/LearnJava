package com.company;

public class Entity {
    String group;
    String id;

    public Entity(String group, String id) {
        this.group = group;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "group='" + group + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
