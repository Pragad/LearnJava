import java.io.*;

/**
 * This is a Singleton class
 */
class SingletonClass {

    private static SingletonClass singleton = null;

    private SingletonClass() {
        System.out.println("Singleton Constructor");
    }

    public static SingletonClass getInstance() {
        if (singleton == null) {
            singleton = new SingletonClass();
        }
        return singleton;
    }

}

public class Singleton {    

    public static void main(String[] args) {
        SingletonClass a = SingletonClass.getInstance();
        SingletonClass b = SingletonClass.getInstance();
        SingletonClass c = SingletonClass.getInstance();
    }

}
