import java.io.*;
import java.util.*;

class MyException extends Exception {
    public MyException(String errMsg) {
        super(errMsg);
    }

    public MyException(String errMsg, Throwable t) {
        super(errMsg, t);
    }
}

class A1Exception extends Exception {
    public A1Exception(String errMsg) {
        super(errMsg);
    }
}

class B1Exception extends Exception {
    public B1Exception(String errMsg) {
        super(errMsg);
    }
}

public class TryCatchThrowException_Nested
{
    static void foo() throws MyException {
        try {
            a();
            b();
        } catch (MyException e) {
            System.out.println("Foo."+ e);
            throw new MyException("Foo - MyException happened.", e);
        }
    }

    static void a() throws MyException {
        try {
            a1();
        } catch (A1Exception e) {
            //System.out.println("A.");
            throw new MyException("A - MyException happened.", e);
        }
    }

    static void b() throws MyException {
        try {
            b1();
        } catch (B1Exception e) {
            //System.out.println("B.");
            throw new MyException("B - MyException happened.", e);
        }
    }

    static void a1() throws A1Exception {
        throw new A1Exception("A1");
    }

    static void b1() throws B1Exception {
        throw new B1Exception("B1");
    }

    public static void main(String[] args) {
        System.out.println("Exception");
        try {
            foo();
        } catch (MyException e) {
            System.out.println("Main Failed");
        }
    }
}
