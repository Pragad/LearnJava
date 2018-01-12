import java.io.*;
import java.util.*;

public class a {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("abc", 5, null));
        System.out.println(persons.get(0));
    }
}

class Person {
    String name;
    int age;
    Long score;

    public Person(String name, int age, long score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Person<name=").append(name)
                                  .append(", age=").append(age)
                                  .append(", score=").append(score)
                                  .append(">").toString();
    }
}
