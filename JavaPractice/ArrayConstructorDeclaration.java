import java.util.*;

public class ArrayConstructorDeclaration {
    int[] arr;

    public ArrayConstructorDeclaration(int capacity) {
        arr = new int[capacity];
    }

    public static void main(String[] args) {
        ArrayConstructorDeclaration ob = new ArrayConstructorDeclaration(5);
        System.out.println(Arrays.toString(ob.arr));
    }
}
