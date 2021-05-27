public class RecursionObject {

    public static void recFoo(MyObject obj) {
        if (obj.i == 5) {
            return;
        }
        //System.out.println("For Loop: i: " + i);
        System.out.println();
        System.out.println("Before Recursion");
        System.out.println(obj);
        obj.i += 1;
        recFoo(obj);
        System.out.println("After Recursion");
        System.out.println(obj);
    }

    public static void main(String[] args) {
        MyObject obj = new MyObject(1, "hi");
        recFoo(obj);
    }
}

class MyObject {
    int i;
    String s;

    public MyObject(int i, String s) {
        this.i = i;
        this.s = s;
    }

    public String toString() {
        return "i: " + i + "; s: " + s;
    }
}
