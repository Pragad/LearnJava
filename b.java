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

        int dir = 0;
        if (dir == 0) {
            System.out.println("dir 0");
            dir = 1;
        } else if (dir == 1) {
            System.out.println("dir 1");
            dir = 2;
        } else if (dir == 2) {
            System.out.println("dir 2");
            dir = 3;
        } else {
            System.out.println("dir else");
        }
        System.out.println(Math.log10(7) / Math.log10(2));
    }
}
