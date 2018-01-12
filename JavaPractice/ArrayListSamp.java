import java.io.*;
import java.util.*;

public class ArrayListSamp
{
    public static void main(String[] args)
    {
        {
            ArrayList<Integer> intArrList = new ArrayList<>();
            intArrList.add(1);
            intArrList.add(2);
            intArrList.add(3);
            intArrList.add(4);

            for (int i : intArrList)
            {
                System.out.println("Num: " + i);
            }
        }

        // Setting max capacity of ArrayList
        {
            List<Integer> al = new ArrayList<>(3);
            al.add(11);
            al.add(12);
            al.add(13);
            al.add(14); // This is allowed
            System.out.println(al);

            // In the below list "add" and "remove" won't work
            // https://stackoverflow.com/questions/5196113/any-way-to-set-max-size-of-a-collection
            List<Integer> bl = Arrays.asList(new Integer[3]);
            // bl.add(5); // This throws UnsupportedOperationException
            bl.set(0, 6);
            bl.set(1, 7);
            bl.set(2, 8);
            // bl.set(3, 9); // This gives ArrayIndexOutOfBoundsException
        }
    }
}
