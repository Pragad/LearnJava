import java.io.*;
import java.util.*;

public class BufferedReaderScanner {
    public static void main(String[] args) {
        // 1a. Reading 1D array from user usig scanner
        /*
        {
            Scanner s = new Scanner(System.in);

            System.out.print("Enter array size: ");
            int count = s.nextInt();
            s.nextLine(); // throw away the newline.

            int [] numbers = new int[count];
            // Scanner numScanner = new Scanner(s.nextLine());
            System.out.println("Enter array in same line separated by space or one element at a time");
            for (int i = 0; i < count; i++) {
                if (s.hasNextInt()) {
                    numbers[i] = s.nextInt();
                } else {
                    System.out.println("You didn't provide enough numbers");
                    break;
                }
            }
            System.out.println(Arrays.toString(numbers));
        }

        // 1b. Reading 1D array from user usig buffered reader
        {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Enter array size: ");
                int count = Integer.parseInt(br.readLine());
                int[] a = new int[count];
                System.out.println("Enter array in same line separated by space");
                String lines = br.readLine();    
                String[] strs = lines.trim().split("\\s+");
                for (int i = 0; i < strs.length; i++) {
                    a[i] = Integer.parseInt(strs[i]);
                }
                System.out.println(Arrays.toString(a));
            } catch (Exception e) {
            }
        }
        */

        // 2. Reading 2D array from user
        {
            System.out.println("Read 2D array from user");
            int ROW = 6;
            int COL = 6;
            int[][] data = new int[ROW][COL]; 
            Scanner in = new Scanner(System.in);
            for(int row = 0; row < ROW; row++){ 
                for(int col = 0; col < COL; col++){ 
                    data[row][col] = in.nextInt(); 
                }
            } 

            for(int row = 0; row < ROW; row++){
                for(int col = 0 ;col < COL; col++){ 
                    System.out.print(data[row][col] + " ");
                } 
                System.out.println(); 
            }
        }

    }
}
