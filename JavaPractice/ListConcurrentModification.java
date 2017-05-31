import java.io.*;
import java.util.*;

public class ListConcurrentModification {
    public static void foo2(Set<String> entrySet) {
        if (entrySet.contains("b")) {
            entrySet.remove("b");
        }
    }

    public static void foo1() {
        Set<String> entrySet = new HashSet<>(Arrays.asList("a", "b", "c"));
        System.out.println(entrySet);
        for (String entry : entrySet) {
            foo2(entrySet);
        }
    }

    public static void bar2(Iterator<String> itr, Set<String> entrySet) {
        if (entrySet.contains("b")) {
            itr.remove();
        }
    }

    public static void bar1() {
        Set<String> entrySet = new HashSet<>(Arrays.asList("a", "b", "c"));
        System.out.println(entrySet);
        for (Iterator<String> iterator = entrySet.iterator(); iterator.hasNext();iterator.next()) {
            bar2(iterator, entrySet);
        }
    }

    public static void main(String[] args) {
        //foo1();
        bar1();
    }
}
