import java.io.*;
import java.util.*;

public class CountConnectedEntries {
    public static List<Integer> ROW_DIRECTION = new ArrayList<>(Arrays.asList(-1, -1, -1,  0, 0,  1, 1, 1));
    public static List<Integer> COL_DIRECTION = new ArrayList<>(Arrays.asList(-1,  0,  1, -1, 1, -1, 0, 1));
    public static int findConnectedCount(int[][] twoDMat, int x, int y) {
        boolean[][] visited = new boolean[twoDMat.length][twoDMat[0].length];
        return dfs(twoDMat, x, y, visited);    
    }

    public static int dfs(int[][] twoDMat, int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        int count = 1;
        for (int i = 0; i < 8; i++) {
            int newRow = x + ROW_DIRECTION.get(i);
            int newCol = y + COL_DIRECTION.get(i);
            if (newRow >= 0 && newRow < twoDMat.length && newCol >=0 && newCol < twoDMat[0].length && visited[newRow][newCol] == false && twoDMat[newRow][newCol] == 1) {
                count = 1 + dfs(twoDMat, newRow, newCol, visited);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] twoD = {{1, 1, 0, 0, 0},
                        {0, 1, 0, 0, 1},
                        {0, 0, 1, 0, 1}};
        System.out.println(findConnectedCount(twoD, 1, 4));

    }
}
