import java.io.*;

public class Enum {

    private enum OpenFlags {
        NO_OVERWRITE (0), OVERWRITE (1);

        private int value;

        private OpenFlags(int value) {
            this.value = value;
        }
    }

    private enum Color {
        RED("RED"),
        BLUE("BLUE");

        private final String color;

        private Color(String val) {
            this.color = val;
        }

        public String getColor() {
            return color;
        }
    }

    public static void main(String[] args) {
        System.out.println("Java Enum");
        System.out.println("1. " + OpenFlags.NO_OVERWRITE.value);
        System.out.println("2. " + OpenFlags.OVERWRITE.value);

        String s = "RED";
        System.out.println(Color.valueOf(s));

        String strColor = Color.RED.name();
        Color enumColor = Color.valueOf(s);

        switch (enumColor) {
            case RED:
                System.out.println("Color is RED");
                break;
            case BLUE:
                System.out.println("Color is BLUE");
                break;
            default:
                System.out.println("Color is Invalid");
                break;
        }
    }
}
