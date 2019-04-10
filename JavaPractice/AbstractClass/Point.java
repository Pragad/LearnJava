import java.io.*;

public abstract class Point {
    private final int id;
    private final String name;
    private final boolean isValid;

    public Point() {
        id = 5;
        name = "abc";
        isValid = true;
    }
}
