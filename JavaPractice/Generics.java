import java.io.*;

/**
 * A class that accepts two generic variables of type A and B
 * This uses primitive data types that can be printed
 */
public class Generics<A, B> {
    A a;
    B b;

    Generics(A a1, B b1) {
        a = a1;
        b = b1;
    }

    void print() {
        System.out.println(a + " : " + b);
    }

    void genericFunction(A myA, B myB) {
        a = myA;
        b = myB;
    }

    public static void main(String[] args) {
        Generics<Integer, String> ob = new Generics<>(5, "five");
        ob.print();
        ob.genericFunction(6, "six");
        ob.print();
    }
}
