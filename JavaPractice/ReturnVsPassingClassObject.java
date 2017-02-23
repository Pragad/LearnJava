import java.io.*;

class Foo
{
    int a;
    int b;

    public Foo(int x, int y)
    {
        a = x;
        b = y;
    }

    public void setA(int val)
    {
        a = val;
    }

    public void setB(int val)
    {
        b = val;
    }

    public int getA()
    {
        return a;
    }

    public int getB()
    {
        return b;
    }

}
public class ReturnVsPassingClassObject
{
    public static void createFooObject(Foo ob)
    {
        System.out.println("In Create");
        ob = new Foo(1, 2);
        System.out.println(ob.getA());
        System.out.println(ob.getB());
        ob.setA(5);
        ob.setB(10);
    }

    public static void main(String[] args)
    {
        // Can't do it as Java is pass by value
        // http://stackoverflow.com/questions/20537367/why-null-object-cant-be-assigned-through-method
        // http://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value
        Foo obFoo = null;
        createFooObject(obFoo);

        System.out.println(obFoo.getA());
        System.out.println(obFoo.getB());
    }
}
