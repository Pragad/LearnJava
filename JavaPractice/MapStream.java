import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class MapStream {

    public Map<String, School> schoolIdToSchoolMap = new HashMap<>();

    static class School {
        public String name;
        public String schoolId;
        public Map<String, Student> studentIdToStudentMap = new HashMap<>();

        public School(String name, String schoolId) {
            this.name = name;
            this.schoolId = schoolId;
        }

        public void addStudent(Student student) {
            studentIdToStudentMap.put(student.studentId, student);
        }

        public void printSchool() {
            System.out.println("School Name: " + name + "; School Id: " + schoolId);
            printStudents();
            System.out.println();
        }

        public void printStudents() {
            studentIdToStudentMap
                .forEach((k, v) -> System.out.println("\t Student Id: " + k + "; " + v));
        }

        public Map<String, Student> getStudentIdToStudentMap() {
            return studentIdToStudentMap;
        }
    }

    static class Student {
        public String name;
        public String studentId;
        public boolean isStudent;

        public Student (String name, String studentId, boolean isStudent) {
            this.name = name;
            this.studentId = studentId;
            this.isStudent = isStudent;
        }

        @Override
        public String toString() {
            return "Name: " + name + "; studentId: " + studentId + "; isStudent: " + isStudent;
        }
    }

    public static Student getStudent(Map<String, School> schoolMap, String studentId) {
        return schoolMap.values()
                .stream()
                .map(School::getStudentIdToStudentMap)
                .filter(map -> map.containsKey(studentId))
                .map(map -> map.get(studentId))
                //.filter() : Have a filter to apply
                .findAny()
                .get()
                ;
    }

    public static void getStudentByName(Map<String, School> schoolMap, String name) {
        String studentId = schoolMap.values()
                .stream()
                .flatMap(fp -> fp.getStudentIdToStudentMap().entrySet().stream)
                .filter(e -> e.getValue().equals(name))
                .map(Map.Entry::getKey)
                .findAny()
                .get()
                ;
        System.out.println("Student Id: " + studentId);
    }

    public static Student getStudentIfPassed(Map<String, School> schoolMap, String schoolId, String studentId) {
        Student student = schoolMap.get(schoolId).getStudentIdToStudentMap().get(studentId);
        if (student == null || student.isStudent) {
            // throw exception
        }
    }

    public static void main(String[] args) {
        System.out.println("Map Streams");

        Map<String, School> schoolMap = new HashMap<>();
        School a = new School("ASD", "a");
        School b = new School("BEE", "b");
        School c = new School("CAG", "c");
        School d = new School("DEG", "d");

        Student s1 = new Student("s1", "1", true);
        Student s2 = new Student("s2", "2", false);
        Student s3 = new Student("s3", "6", false);
        Student s4 = new Student("s4", "4", true);

        Student t1 = new Student("t1", "5", true);
        Student t2 = new Student("t2", "6", true);

        Student u1 = new Student("u1", "7", true);
        Student u2 = new Student("u2", "8", true);
        Student u3 = new Student("u3", "9", true);

        Student v1 = new Student("v1", "10", true);

        a.addStudent(s1);
        a.addStudent(s2);
        a.addStudent(s3);
        a.addStudent(s4);

        b.addStudent(t1);
        b.addStudent(t2);

        c.addStudent(u1);
        c.addStudent(u2);
        c.addStudent(u3);

        d.addStudent(v1);

        schoolMap.put("a", a);
        schoolMap.put("b", b);
        schoolMap.put("c", c);
        schoolMap.put("c", d);

        a.printSchool();
        b.printSchool();
        c.printSchool();
        d.printSchool();

        System.out.println(getStudent(schoolMap, "6"));
        System.out.println(getStudentByName(schoolMap, "s3"));
//        System.out.println(getStudent(schoolMap, "b", "6"));
        /*
        Map<String, Student> studentMap = new HashMap<String, Student>();
        studentMap.put("1", new Student("asd", "1", true));
        studentMap.put("2", new Student("def", "2", false));
        studentMap.put("3", new Student("ghi", "3", false));
        studentMap.put("4", new Student("asdwe", "4", true));

        Student student = studentMap.entrySet()
                .stream()
                .filter(s -> !s.getValue().isStudent)
                .collect(Collectors.toMap(s -> s.getKey(), s -> s.getValue()))
                .computeIfAbsent("3", key -> {
                        return null;
        });

        if (student == null) {
            System.out.println("Student is null");
        } else {
            System.out.println(student);
        }
        */
    }
}
