import java.util.*;

public final class InnerClass {
    List<OuterClass> lengths;

    public static void main(String[] args) {
        System.out.println("Hello Inner Class");
    }
}

final class OuterClass {
    private final int length;
}
