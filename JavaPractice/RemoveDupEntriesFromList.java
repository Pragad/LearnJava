import java.io.*;
import java.util.*;

public class RemoveDupEntriesFromList {
    public static void addEntriesToList(List<String> origE, List<String> addE) {
    }

    public static List<String> removeEntriesFromList(List<String> origE, List<String> remE) {
        HashSet<String> tmp = new HashSet<>(origE);
        tmp.removeAll(remE);                     // Linear operation
        origE = new ArrayList<String>(tmp);
        return origE;
    }

    public static void main(String[] args) {
        List<String> origEntry = new ArrayList<>();
        origEntry.add("hi");
        origEntry.add("hello");
        origEntry.add("how");
        origEntry.add("hi");
        origEntry.add("are");
        origEntry.add("you");

        List<String> remEntry = new ArrayList<>();
        remEntry.add("hi");
        remEntry.add("you");

        List<String> addEntry = new ArrayList<>();
        addEntry.add("hi");
        addEntry.add("why");
        addEntry.add("what");

        System.out.println(origEntry);
        System.out.println("After remove");
        origEntry = removeEntriesFromList(origEntry, remEntry);
        System.out.println(origEntry);

        origEntry.addAll(addEntry);
        System.out.println("After add");
        System.out.println(origEntry);
        //System.out.println(origEntry);
    }
}
