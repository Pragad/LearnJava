import java.io.*;
import java.util.*;

public class IntegerObject {
    public static void foo(Integer a) {
        a = 10;
    }

    public static void main(String[] args) {
        Integer a = 5;
        System.out.println(a);
        foo(a);
        System.out.println(a);
    }
}
