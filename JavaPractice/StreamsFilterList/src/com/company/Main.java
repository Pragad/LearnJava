package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Main {

    public static final List<String> allSubjects = Arrays.asList(
            "Chemistry",
            "Physics",
            "Maths",
            "History",
            "Economics"
    );

    public static void main(String[] args) {
        Student a = new Student("abc", new HashSet<>(Arrays.asList("PhySicS", "EcoNomicS")));
        Student b = new Student("xyz", new HashSet<>(Arrays.asList("MathS")));

        List<Student> students = Arrays.asList(a, b);

        // Get available subjects not taken by any students
        Set<String> subjectsTaken = students.stream()
                .flatMap(student -> student.getSubjects().stream())
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
        System.out.println(subjectsTaken);

        List<String> availableSubjects = allSubjects.stream()
                .filter(s -> !subjectsTaken.contains(s.toLowerCase()))
                .collect(Collectors.toList());


        /*
        final Set<String> consumedSpaceNames =
                spaceCRUD.getSpacesByCustomerId(customerId)
                        .stream()
                        .flatMap(space -> space.getNames().stream())
                        .map(String::toLowerCase)
                        .collect(Collectors.toSet());

        final List<String> spaceNamesAvailable =
                spaceNamesSupported
                        .stream()
                        .filter(spaceName -> !consumedSpaceNames.contains(spaceName.toLowerCase()))
                        .limit(limit)
                        .collect(ImmutableList.toImmutableList());
        */

        /*
        final List<Person> personList = somelist.stream()
                .map(entry -> {
						return Asd.builder()
								.withA(entry)
								.withS("S")
								.build();
                }).collect(ImmutableList.toImmutableList());

        final List<Person> personList = somelist.stream()
                .map(entry -> 
						return Asd.builder()
								.withA(entry)
								.withS("S")
								.build()
                ).collect(ImmutableList.toImmutableList());
        */
        System.out.println(availableSubjects);

        // Construct a list of Entities from a List of String
        List<String> ids = Arrays.asList("a", "b", "c", "d");
        List<Entity> entities = ids.stream().map(
                s -> new Entity("GROUP", s)
        ).collect(collectingAndThen(toList(), Collections::unmodifiableList));

        for (Entity e : entities) {
            System.out.println(e);
        }
    }
}
