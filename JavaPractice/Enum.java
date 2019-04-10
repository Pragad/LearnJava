import java.io.*;
import java.util.Random;

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
        BLUE("BLUE"),
        GREEN("GREEN");

        private final String color;

        private Color(String val) {
            this.color = val;
        }

        public String getColor() {
            return color;
        }
    }

    /*
    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        Random random = new Random();
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
    */

    public static void main(String[] args) {
        System.out.println("Java Enum");
        System.out.println("1. " + OpenFlags.NO_OVERWRITE.value);
        System.out.println("2. " + OpenFlags.OVERWRITE.value);

        System.out.println("Random Enum");
        System.out.println(Color.values()[new Random().nextInt(Color.values().length)]);
        System.out.println(Color.values()[0]);
        System.out.println(Color.values()[1]);
        System.out.println(Color.values()[2]);
        System.out.println("Done Random Enum");

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
