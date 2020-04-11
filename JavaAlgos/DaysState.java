import java.util.*;
import java.util.stream.*;

public class DaysState {

    private static class RowColDistance
    {
        private int row;
        private int col;
        private int distance;

        public RowColDistance(int row, int col, int distance)
        {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }

        public int getRow() { return row; }
        public int getCol() { return col; }
        public int getDistance() { return distance; }
    }

    public static List<Integer> ROW_MOVES = new ArrayList<>(Arrays.asList(-1,  0, 0, 1));
    public static List<Integer> COL_MOVES = new ArrayList<>(Arrays.asList( 0, -1, 1, 0));

    int removeObstacle(int numRows, int numColumns, List<List<Integer>> lot)
    {
        Queue<RowColDistance> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(new RowColDistance(0, 0, 0));

        while (!bfsQueue.isEmpty())
        {
            RowColDistance currentEntry = bfsQueue.remove();
            // Mark the current entry as visited by changing its value to -1
            lot.get(currentEntry.getRow()).set(currentEntry.getCol(), -1);

            // Check neighbors of current entry and add it to queue if the are not an obstacle
            // Traverse in the possible 4 directions to check the neighbors
            for (int i = 0; i < 4; i++)
            {
                int newRow = currentEntry.getRow() + ROW_MOVES.get(i);
                int newCol = currentEntry.getCol() + COL_MOVES.get(i);
                if (newRow >= 0 && newRow < numRows &&
                    newCol >= 0 && newCol < numColumns)
                {
                    if (lot.get(newRow).get(newCol) == 1)
                    {
                        bfsQueue.add(new RowColDistance(newRow, newCol, currentEntry.getDistance() + 1));
                    }
                    else if (lot.get(newRow).get(newCol) == 9)
                    {
                        return currentEntry.getDistance() + 1;
                    }
                }

            }
        }
        return -1;
    }

    static String removeFirstPart(String entry)
    {
        int index = 0;
        for (Character c : entry.toCharArray())
        {
            if (c == ' ') 
            {
                index++;
                break;
            }
            index++;
        }
        return entry.substring(index);
    }
    public static void main(String[] args) {
        /*
        DaysState ob = new DaysState();
        System.out.println("hi");
        List<Integer> l1 = new ArrayList<>(Arrays.asList(1, 0, 0));
        List<Integer> l2 = new ArrayList<>(Arrays.asList(1, 0, 0));
        List<Integer> l3 = new ArrayList<>(Arrays.asList(1, 9, 1));
        List<List<Integer>> lists1 = new ArrayList<List<Integer>>();
        lists1.add(l1);
        lists1.add(l2);
        lists1.add(l3);

        List<Integer> la = new ArrayList<>(Arrays.asList(1, 1, 1, 1));
        List<Integer> lb = new ArrayList<>(Arrays.asList(0, 1, 1, 1));
        List<Integer> lc = new ArrayList<>(Arrays.asList(0, 1, 0, 1));
        List<Integer> ld = new ArrayList<>(Arrays.asList(1, 1, 9, 1));
        List<Integer> le = new ArrayList<>(Arrays.asList(0, 0, 1, 1));
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        lists.add(la);
        lists.add(lb);
        lists.add(lc);
        lists.add(ld);
        lists.add(le);


        List<Integer> la = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 1));
        List<Integer> lb = new ArrayList<>(Arrays.asList(0, 1, 1, 1, 1, 1));
        List<Integer> lc = new ArrayList<>(Arrays.asList(0, 1, 0, 1, 1, 1));
        List<Integer> ld = new ArrayList<>(Arrays.asList(1, 1, 0, 1, 9, 1));
        List<Integer> le = new ArrayList<>(Arrays.asList(0, 0, 1, 0, 1, 1));
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        lists.add(la);
        lists.add(lb);
        lists.add(lc);
        lists.add(ld);
        lists.add(le);



        System.out.println(ob.removeObstacle(3, 3, lists1));
        System.out.println(ob.removeObstacle(5, 4, lists));
        */

        System.out.println(removeFirstPart("abc def ghi kl"));



        /*
        1. In order to find the minimum distance between two elements in a 2D matrix, BFS can be used.
        2. I used a Queue to implement the BFS logic. As I visit each entry in the 2D matrix, I should remember its row, column and the distance for that entry from 0,0. I added a RowColDistance class to save this info into the BFS Queue.
        3.
        3. As I visit each entry in the 2D matrix, I should keep track of the visited entries and should not visit them again. Instead of using another 2D array to stored the visited entries, I changed the value of the existing entries to -1. With this approach I can avoid extra space. If I want to recreate the original 2D matrix, I can replace -1 with 1.
        4. Once I reach the destination, I can just return the distance required
        */
    }
}
