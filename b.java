import java.io.*;
import java.util.*;

public class b
{
    public static void foo()
    {
        System.out.println("hello");
    }

    public static int countPalindromes(String str) {
        int count = 0;
        count = str.length();

        for (int i = 0, j = 0; i < str.length(); i++) {
            j = str.length() - 1;

            while (i < j) {
                if (str.charAt(i) == str.charAt(j)) {
                    String tmp = str.substring(i, j+1);
                    
                    String tmpRev = new StringBuilder(tmp).reverse().toString();
                    if (tmp.equals(tmpRev)) {
                        System.out.println(tmp);
                        count++;
                    }
                }
                j--;
            }
        }

        return count;
    }

    public static void main(String[] args)
    {
        System.out.println("hi");
        foo();
        String str = "hellolle";
        System.out.println(countPalindromes(str));
        String str2 = "wowpurerocks";
        System.out.println(countPalindromes(str2));
    }
}
