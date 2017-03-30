import java.io.*;
import java.util.*;

public class JavaHashMap {
    public static Map<String, String> foo() {
        return null;
    }

    public static void main(String[] args) {
        Map<String, String> myMap = foo();
        System.out.println(myMap.get("a"));

        String str = "Hello";
        int index = 0;
        HashMap<Character, Integer> hmUniqChar = new HashMap<>();


        for (char ch : str.toCharArray())
        {
            if (hmUniqChar.containsKey(ch))
            {
                System.out.println("Char '" + ch + "' already present at: " + hmUniqChar.get(ch));
            }
            else
            {
                hmUniqChar.put(ch, index);
            }

            index++;
        }
    }
}
