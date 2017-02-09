import java.io.*;
import java.util.*;

class MyAgeException extends Exception
{
    public MyAgeException(String errMsg)
    {
        super(errMsg);
    }
}

public class TryCatchThrowExcepiton
{
    static boolean isPositiveNumber(int num) throws IllegalArgumentException
    {
        if (num < 0)
        {
            throw new IllegalArgumentException("Negative Number");
        }
        else
        {
            return true;
        }
    }

    static int  employeeAgeNoExcep(int age) throws MyAgeException
    {
        if(age < 0)
        {
            // Learn -1
            // This will fail as NO return is happening
            System.out.println("Input is Invalid!!");
        }
        else
        {
            System.out.println("Input is valid!!");
            return 1;
        }

        // Learn 0
        // This is Perfectly valid. We don't have to return anything if throwing an
        // exception
        throw new MyAgeException("Age can't be less than zero");
    }

    static int  employeeAge(int age) throws MyAgeException
    {
        if(age < 0)
        {
            throw new MyAgeException("Age can't be less than zero");
        }
        else
        {
            System.out.println("Input is valid!!");
            return 1;
        }
    }

    public static void main(String[] args)
    {
        System.out.println("Exception");

        // Learn 1:
        // Will fail as the exception is NOT Caught
        //employeeAge(2);

        // Learn 2:
        try
        {
            employeeAge(2);
        }
        catch (MyAgeException e1)
        {
            System.out.println("1.");
            e1.printStackTrace();
        }
        catch (Exception e2)
        {
            // Learn 3
            // This won't get executed
            System.out.println("2.");
            e2.printStackTrace();
        }

        // Learn 4
        int num = -5;
        try
        {
            boolean flag = isPositiveNumber(num);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Negative Number");
            e.printStackTrace();
        }
    }
}
