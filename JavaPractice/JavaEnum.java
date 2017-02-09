import java.io.*;

public class JavaEnum {

    private enum OpenFlags {
        NO_OVERWRITE (0), OVERWRITE (1);

        private int value;

        private OpenFlags(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        System.out.println("Java Enum");
        System.out.println("1. " + OpenFlags.NO_OVERWRITE.value);
        System.out.println("2. " + OpenFlags.OVERWRITE.value);
    }
}
