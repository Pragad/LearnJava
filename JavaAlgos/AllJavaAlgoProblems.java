import java.io.*;
import java.util.*;

public class AllJavaAlgoProblems
{
    public static int findFirstGreaterElementIndex(ArrayList<Integer> nums, int myNum)
    {
        /*
        if (nums.empty())
        {
            return -1;
        }

        if (myNum < nums[0])
        {
            return 0;
        }

        int32_t stIdx = 0;
        int32_t endIdx = nums.size() - 1;

        while (stIdx <= endIdx)
        {
            int32_t midIdx = stIdx + (endIdx - stIdx) / 2;

            if (nums[midIdx] <= myNum)
            {
                if (midIdx + 1 < nums.size() - 1 && nums[midIdx + 1] > myNum)
                {
                    return midIdx + 1;
                }
                else
                {
                    stIdx = midIdx + 1;
                }
            }
            // So num[midIdx] is GREATER than myNum
            // If the previous number is a smaller or equal number, we go the answer
            else
            {
                if (midIdx - 1 >= 0 && nums[midIdx - 1] <= myNum)
                {
                    return midIdx;
                }
                else
                {
                    endIdx = midIdx - 1;
                }
            }
        }

        return -1;
        */
    }

    public int elementGreater(ArrayList<Integer> aList, int givenNumber)
    {
        if(aList.isEmpty)                   // Case "alist"
        {
            return -1;
        }

        int start = 0;                      // Where is INT
        int end = aList.size() -1;          // Where in INT
        while(start <= end) {
            int mid = start + (end - start) / 2;            
            if(end - start <= 2)
            {
                if(mid<=given) {            // What is given?
                    return end;
                }
                else {
                    return mid;
                }
            }


            if(aList.get(mid) == givenNumber) {
                return aList.get(mid+1);
            }
            else if(aList.get(mid) < givenNumber) {
                start = mid;
            }
            else {
                end = mid;
            }
        }

        // Where is the return type for this case?
        return -1;
    }

    public static void main(String[] args)
    {
        System.out.println("hi");
    }
}
