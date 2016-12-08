import java.io.*;
import java.util.*;


public class SampleLearning
{
    // Problem 1:
    // A class variable of primitive data type MUST be INITIALIZED
    //final int a;      // Compile Time Error
    final int a = 5;

    // Problem 5a
    // A final CLASS variable MUST be Initialized
    // OR it MUST be initialized in the constructor
    // The below is a BLANK Final Variable
    //final String d;

    // Problem 6:
    // Making an array list as final
    // Warning if we don't mention "String"
    final List<String> myAl = new ArrayList<String>();

    public static void main(String[] args)
    {
        // Problem 2:
        // Final Variable inside a method need not be initialized
        // No compile time error
        //final int b;
        final int b = 4;

        // Problem 3:
        // But trying to use the above variable errors out
        // final primitive variable MUST be initialized to be used
        //System.out.println(b);

        // Problem 4:
        // The value of a final variable CAN also be known at run-time instead of compile time
        final int c = b;

        // Problem 5:
        // All method variables must be initialized before use
        final Integer d;

        // Problem 6:
        // Even though "myAl" is final, we can add data to it
        // FINAL means that "myAl" CAN'T point to any other array list
        SampleLearning ob = new SampleLearning();
        ob.myAl.add("hello");

        List<String> myAl2 = new ArrayList<String>();
        myAl2.add("hi");

        List<String> myAl3 = new ArrayList<String>();
        myAl3 = myAl2;

        // Since "myAl" is final can't assign another ArrayList to it
        // Reference can't be changed
        //ob.myAl = myAl2;


        System.out.println("Hi");
    }
}

