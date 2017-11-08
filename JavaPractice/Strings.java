import java.io.*;
import java.util.*;

// https://www.tutorialspoint.com/java/lang/string_split.htm
public class Strings
{
    static void foo (String e){ 
        e = "bla bla";
        System.out.println(e);
    }

    public static void main(String[] args) {
        // 1.
        {
            String str = "a d, m, i.n";
            String[] tokens = str.split(",\\s*");
            //String[] tokens = str.split(",\\s*");

            for (String tok : tokens) {
                System.out.println(tok);
            }
        }

        // 2. Mutability of Strings
        {
            String s = "blabla";
            System.out.println(s);
            foo(s);
            System.out.println(s);
        }
    }
}

/*
https://books.google.com/books?id=diqHjRjMhW0C&pg=PA24&lpg=PA24&dq=java+string+split+%22,%5C%5Cs*%22&source=bl&ots=IwpwyEfgmz&sig=n0PZCuhVxDx5pwgEZI1D_YpTXrE&hl=en&sa=X&ved=0ahUKEwi5g76FuOHRAhVO3WMKHVd7C_UQ6AEISTAI#v=onepage&q=java%20string%20split%20%22%2C%5C%5Cs*%22&f=false
\s - Whitespace (space, tab, line feed, carriage return)
\S - Non Whitespace
\d - Digits
\D - Non Digits
\w - 0-9,a-z,A-Z,_
\W - Non word chars
*  - Repeat preceding pattern 0 or more times
+  - Repeat preceding pattern 1 or more times
?  - Repeat preceding pattern 0 or 1 times
*/
