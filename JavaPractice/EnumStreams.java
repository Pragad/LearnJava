import java.util.Arrays;

enum Colors {
    RED(0),
    BLUE(1),
    GREEN(2);

    private int color;

    Colors(final int color) {
        this.color = color;
    }

    public int getValue() {
        return color;
    }
}

public class A {

   public static Colors intToColor(final int colorValue) {
        return Arrays.stream(Colors.values())
                .filter(e -> e.getValue() == colorValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Exception"));
        /*
        for (Colors color : Colors.values()) {
            if (colorValue == color.getValue()) {
                return color;
            }
        }
        throw new IllegalArgumentException("Exception");
        */
    }

    public static void main(String[] args) {
        try {
            System.out.println(intToColor(2));
        } catch (Exception e) {
            // Exception
        }
    }
}

