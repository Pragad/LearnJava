import java.io.*;
import java.util.*;

public class PriorityQueueHeap
{
    public static void main(String[] args)
    {
        ArrayList<Integer> al = new ArrayList<Integer>();
        al.add(5);
        al.add(3);
        al.add(6);
        al.add(1);
        /*
        if (al.offer(1))
        {
            System.out.println("Added 1");
        }
        else
        {
            System.out.println("Unable to add");
        }
        */

        for (int i : al)
        {
            System.out.println(i);    
        }

        System.out.println("\n\n");

        PriorityQueue<Integer> intPq = new PriorityQueue<Integer>(Collections.reverseOrder());

        for (int i : al)
        {
            intPq.add(i);
        }

        while (intPq.size() != 0)
        {
            System.out.println(intPq.remove());
        }

    }
}
