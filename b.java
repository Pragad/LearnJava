import java.io.*;
import java.util.*;

public class b
{
    public static int solution(int[] A)
    {
        ArrayList<Integer> resultsList = new ArrayList<Integer>();

        int resIdx = -1;

        int totalSum = 0;
        int leftSum = 0;
        int rightSum = 0;
        for (int a: A)
        {
            totalSum += a;
        }

        for (int i = 0; i < A.length; i++)
        {
            // IMP: First thing to do is add element to Left sum
            // For Right Sum, we should exclude the current element as well.
            rightSum = totalSum - (leftSum + A[i]);
            if (rightSum == leftSum)
            {
                resultsList.add(i);
                resIdx = i;
            }

            leftSum += A[i];
        }

        for (Integer i : resultsList)
        {
            System.out.println(i);
        }

        return resIdx;
    }

    public static void main(String[] args)
    {
        System.out.println("hi");
        int[] A = {-1, 3, -4, 5, 1, -6, 2, 1};
        int[] A = {-1, 3, -4, 5, 1, -6, 2, 1};
        System.out.println(solution(A));
    }


    //-------------------------------------
    /*
    public int countNodes(node head) {

        if(head == null) {
            return 0;
        }
        else { 
            return 1 + countNodes(head.l) + countNodes(head.r);
        }
    }

    public int minValue(node head) {

        while (head != null) {
            if (head.l == null)
            {
                return head.x;
            }
            return minValue(head.l);
        }

        return -1;
    }

    public int maxValue(node head) {

        while (head != null) {
            if (head.r == null)
            {
                return head.x;
            }
            return minValue(head.r);
        }

        return -1;
    }

    public int solution(int A, int B, tree T)
    {
        if (T == null)
        {
            return 0;
        }

        // If root.data is more than Max Range then go left
        if (T.x > B)
        {
            return solution(A, B, T.l);
        }
        else if (T.x < A)
        {
            return solution(A, B, T.r);
        }
        else
        {
            // root.data falls within the range

            int minVal = minValue(T);
            int maxVal = maxValue(T);

            if (minVal > A && maxVal < B)
            {
                return size(T);
            }
            else
            {
                return max (solution(A, B, T.l), solution(A, B, T.r));
            }
        }
    }
    */
    //-------------------------------------
}
