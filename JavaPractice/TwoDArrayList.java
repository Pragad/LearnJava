import java.io.*;
import java.util.*;

public class TwoDArrayList {
    public static void main (String[] args) {
        System.out.println("TwoDArrayList");

        ArrayList< ArrayList<Integer> > twoDMat = new ArrayList< ArrayList<Integer> >();
        int numRows = 6;    // This is number of rows
        int numCols = 6;    // This is number of cols
        int[][] resArray = new int[numRows][numCols];
        printTwoDArray(resArray);
        foo(resArray);
        printTwoDArray(resArray);
    }

    public static void printTwoDArray(int[][] resArray) {
        for (int i = 0; i < resArray.length; i++)
        {
            for (int j = 0; j < resArray[0].length; j++)
            {
                System.out.print(CantorPairingFunction(i, j) + ", ");
                //System.out.print(resArray[i][j] + ", ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int CantorPairingFunction(int x, int y) {
        return (int)(0.5 * (x + y) * (x + y + 1) + y);
    }

    public static void foo(int[][] fillArray) {
        // Fill First col with all 1s
        for (int i = 0; i < fillArray.length; i++) {
            fillArray[i][0] = 1;
        }

        // Fill First row with all 1s
        for (int i = 0; i < fillArray[0].length; i++) {
            fillArray[0][i] = 1;
        }
    }
}
