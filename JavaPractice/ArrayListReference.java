import java.io.*;
import java.util.*;

public class ArrayListReference {
    public static void foo(List<String> al)
    {
        al.add("hi");
    }

    public static void main(String[] args)
    {
        List<String> al = new ArrayList<>();
        System.out.println(al);
        foo(al);
        System.out.println(al);
    }
}
