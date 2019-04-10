package com.company;

import java.util.Set;

public class Student {
    public String name;
    public Set<String> subjects;

    public Student(String name, Set<String> subjects) {
        this.name = name;
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", subjects=" + subjects +
                '}';
    }

    public String getName() {
        return name;
    }

    public Set<String> getSubjects() {
        return subjects;
    }
}
