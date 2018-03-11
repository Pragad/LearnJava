import java.io.*;
import java.util.*;

class MyAgeException extends Exception
{
    public MyAgeException(String errMsg)
    {
        super(errMsg);
    }
}

class MyCustomException extends Exception
{
    public MyCustomException(String errMsg)
    {
        super(errMsg);
    }
}

public class TryCatchThrowException
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
            int x = 5;
            throw new MyAgeException("Age can't be less than zero: {} X: {}" + age + x);
        }
        else
        {
            System.out.println("Input is valid!!");
            return 1;
        }
    }

    static void employeeAgeWrapper(int age) throws MyCustomException
    {
        if(age < 0)
        {
            throw new MyCustomException("Custom: Age can't be less than zero: ");
        }
        else
        {
            System.out.println("Input is valid!!");
        }
    }

    static void foo(int age)  throws MyAgeException
    {
        try
        {
            employeeAge(age);
            employeeAgeWrapper(age);
        } 
        catch (MyAgeException e) {
            System.out.println("Caught in parent block 1: " + e);
            System.out.println("Throw back the exception");
            throw e;
        }
        catch (Exception e)
        {
            System.out.println("Caught in parent block 2: " + e);
            System.out.println("DONE");
            e.printStackTrace();
        }
    }

    /*
    static void employeeAgeWrapper(int age) throws MyCustomException
    {
        try
        {
            employeeAge(-2);
        }
        catch (MyAgeException e)
        {
            throw new MyCustomException("Custom age less than zero");
        }
    }
    */

    public static void main(String[] args)
    {
        System.out.println("Exception");

        // Learn 1:
        // Will fail as the exception is NOT Caught
        //employeeAge(2);

        // Learn 2:
        try
        {
            foo(-2);
            //employeeAgeWrapper(-2);
        }
        catch (Exception e1)
        //catch (MyCustomException e1)
        {
            System.out.println("1.");
            System.out.println(e1);
            //e1.printStackTrace();
        }
        /*
        catch (Exception e2)
        {
            // Learn 3
            // This won't get executed
            System.out.println("2.");
            e2.printStackTrace();
        }
        */

        // Learn 4
        /*
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
        */
    }
}
