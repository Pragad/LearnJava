import java.io.*;
import java.util.*;
import java.lang.*;

/*
 * PROBLEM 1. Print all possilbe steps down a ladder
 * void printLadderPaths(int numSteps, String path)
 *
 * PROBLEM 2: Print matrix diagonally
 * void printDiagonally(int[][] matrix) 
 *
 * PROBLEM 3: Print matrix spirally
 * void printMatrixSpirally(int[][] twoDMat) 
 *
 * PROBLEM 4: Given a string of 1s and 0s with '?' in between. A '?' can be either one or zero.
 * void printCombinationOnesZerosRec(StringBuilder str, int stIdx, int lastQMarkPos) 
 *
 * PROBLEM 5. Given two strings, find the first non matching character
 * char findFirstNonMatchingChar(String s1, String s2) *
 *
 * PROBLEM 6: Find the unique character present in a string
 * int firstUniqueCharacter(String s) 
 *
 * PROBLEM 7: Count the number of islands in a 2D matrix
 * uint32_t countConnectedIslands(int** twoDmat, uint32_t rows, uint32_t cols)
 *
 * PROBLEM 8: Given a input stream of numbers, compute the median after each entry
 * int      medianOfStreams(int number, maxHeap, minHeap)
 *
 * PROBLEM 9. Find the max sub array sum
 * int      maxSumSubArray(int arr[], int num)
 * void     maxSumSubSequence(int arr[], int num)
 *
 * PROBLEM 10. Find the max sub array product
 * int maxProduct(vector<int>& nums)
 * 
 */
public class AllJavaAlgoProblems {
    // -------------------------------------------------------------------------
    // Problem 1.
    // Print all possilbe steps down a ladder
    // -------------------------------------------------------------------------
    public static void printLadderPaths(int numSteps, String path) {
        if (numSteps == 0) {
            System.out.println(path);
            return;
        }

        if (numSteps >= 1) {
            path  += '1';
            printLadderPaths(numSteps - 1, path);
            path = path.substring(0, path.length()-1);
        }

        if (numSteps >= 2) {
            path  += '2';
            printLadderPaths(numSteps - 2, path);
        }
    }

    // -------------------------------------------------------------------------
    // Problem 2.
    // Print matrix diagonally
    // -------------------------------------------------------------------------
    public static void printDiagonally(int[][] matrix) {
        for(int i=0; i<matrix.length; i++) {
            for(int j=0, k=i; j<=i; j++,k--) {
                if(j<0) {
                    break;
                }

                if (k >= 0 && k <matrix.length && j >= 0 && j < matrix[0].length) {
                    System.out.print(matrix[k][j] + ", ");
                }
            }

            System.out.println();
        }

        for(int j=1; j<matrix[0].length; j++) {
            for(int i=matrix.length-1, k=j; k<=matrix[0].length; i--,k++) {
                if(k>=matrix[0].length) {
                    break;
                }

                if (i >= 0 && i < matrix.length && k >= 0 && k < matrix[0].length) {
                    System.out.print(matrix[i][k] + ", ");
                }
            }

            System.out.println();
        }
    }

    // -------------------------------------------------------------------------
    // Problem 3.
    // Print matrix spirally
    // -------------------------------------------------------------------------
    public static void printMatrixSpirally(int[][] twoDMat) {
        int topBoundary = 0;
        int leftBoundary = 0;
        int bottomBoundary = twoDMat.length-1;
        int rightBoundary = twoDMat[0].length-1;
        
        while(topBoundary < bottomBoundary && leftBoundary < rightBoundary) { 
            for(int i=leftBoundary; i<=rightBoundary; i++) {
                System.out.print(twoDMat[topBoundary][i]+" " );
            }
            System.out.println();
            topBoundary++;
            
            for(int i=topBoundary; i<=bottomBoundary; i++) {
                System.out.print(twoDMat[i][rightBoundary]+" " );
            }
            System.out.println();
            rightBoundary--;

            for(int i=rightBoundary; i>=leftBoundary; i--) {
                System.out.print(twoDMat[bottomBoundary][i]+" " );
            }
            System.out.println();
            bottomBoundary--;

            for(int i=bottomBoundary; i>=topBoundary; i--) {
                System.out.print(twoDMat[i][leftBoundary]+" " );
            }
            System.out.println();
            leftBoundary++;
        }
    }

    // -------------------------------------------------------------------------
    // Problem 4.
    // Given a string of 1s and 0s with '?' in between. A '?' can be either one or zero.
    // -------------------------------------------------------------------------
    public static void printCombinationOnesZerosRec(StringBuilder str, int stIdx, int lastQMarkPos) {
        if (stIdx == lastQMarkPos) {
            System.out.println(str);
            return;
        }

        for (int i = stIdx; i < str.length(); i++) {
            if (str.charAt(i) == '?') {
                str.setCharAt(i, '0');
                printCombinationOnesZerosRec(str, i, lastQMarkPos);
                str.setCharAt(i, '1');
                printCombinationOnesZerosRec(str, i, lastQMarkPos);
            }
        }
    }

    public static void printCombinationOnesZeros(String str) {
        int lastQMarkPos = str.length();
        for (int i = str.length()-1; i >=0; i--) {
            if (str.charAt(i) == '?') {
                lastQMarkPos = i;
            }
        }

        if (lastQMarkPos < str.length()) {
            // Convert the string to string builder so that it can be changed in place
            StringBuilder strBuilder = new StringBuilder(str);
            printCombinationOnesZerosRec(strBuilder, 0, lastQMarkPos);
        } else {
            System.out.println(str);
        }
    }

    // -------------------------------------------------------------------------
    // PROBLEM 5.
    // Given two strings, find the first non matching character
    // -------------------------------------------------------------------------
    public static char findFirstNonMatchingChar(String s1, String s2) {
        return ' ';
    }

    // -------------------------------------------------------------------------
    // PROBLEM 6.
    // Find the unique character present in a string
    // -------------------------------------------------------------------------
    public static int firstUniqueCharacter(String s) {
        HashMap<Character, Integer> charMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (charMap.containsKey(c)) {
                charMap.put(c, charMap.get(c) + 1);
            } else {
                charMap.put(c, 1);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (charMap.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 7.
    // Count the number of islands in a 2D matrix
    // -------------------------------------------------------------------------
    // Procedure to do the DFS search Iteratively
    private static class PairRowCol {
        private int row;
        private int col;

        public PairRowCol(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() { return row; }
        public int getCol() { return col; }
    }

    public static List<Integer> ROW_DIRECTION = new ArrayList<>(Arrays.asList(-1, -1, -1,  0, 0,  1, 1, 1));
    public static List<Integer> COL_DIRECTION = new ArrayList<>(Arrays.asList(-1,  0,  1, -1, 1, -1, 0, 1));

    public static void dfsRecursive(List<List<Integer>> twoDMat, int row, int col) {
        twoDMat.get(row).set(col, 2);
        for (int i = 0; i < 8; i++) {
            int newRow = row + ROW_DIRECTION.get(i);
            int newCol = col + COL_DIRECTION.get(i);
            if (newRow >= 0 && newRow < twoDMat.size() &&
                newCol >= 0 && newCol < twoDMat.get(newRow).size() &&
                twoDMat.get(newRow).get(newCol) == 1) {
                dfsRecursive(twoDMat, newRow, newCol);
            }
        }
    }

    public static void dfsIterative(List<List<Integer>> twoDMat, int row, int col) {
        //Stack<PairRowCol> dfsStack = new Stack<PairRowCol>();
        Queue<PairRowCol> dfsStack = new LinkedList<PairRowCol>();
        dfsStack.add(new PairRowCol(row, col));
        while (!dfsStack.isEmpty()) {
            PairRowCol obj = dfsStack.remove();
            twoDMat.get(obj.getRow()).set(obj.getCol(), 2);
            for (int i = 0; i < 8; i++) {
                // IMP: Should be from obj.getRow() and not row
                int newRow = obj.getRow() + ROW_DIRECTION.get(i);
                int newCol = obj.getCol() + COL_DIRECTION.get(i);
                if (newRow >= 0 && newRow < twoDMat.size() &&
                    newCol >= 0 && newCol < twoDMat.get(newRow).size() &&
                    twoDMat.get(newRow).get(newCol) == 1) {
                    dfsStack.add(new PairRowCol(newRow, newCol));
                }
            }
        }
    }

    public static void printTwoDIntMat(List<List<Integer>> twoDMat) {
        for (int i = 0; i < twoDMat.size(); i++) {
            for (int j = 0; j < twoDMat.get(i).size(); j++) {
                System.out.print(twoDMat.get(i).get(j));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int countConnectedIslands(List<List<Integer>> twoDMat) {
        int countIslands = 0;
        for (int i = 0; i < twoDMat.size(); i++) {
            for (int j = 0; j < twoDMat.get(i).size(); j++) {
                if (twoDMat.get(i).get(j) == 1) {
                    //dfsRecursive(twoDMat, i, j);
                    dfsIterative(twoDMat, i ,j);
                    countIslands++;
                }
            }
        }
        return countIslands;
    }

    // -------------------------------------------------------------------------
    // Main Function
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("All Java Algo Problems");

        // Problem 1. Print all steps down a ladder
        {
            System.out.println("\nProblem 1. Print All Steps down a ladder");
            String str = "";
            int [][] twoDMat = new int[5][3];
            printLadderPaths(4, str);
        }

        // Problem 2. Print the matrix in diagonal order
        {
            System.out.println("\nProblem 2. Print Matrix Diagonally");
            int[][] matrix1 = {{1, 2, 3, 4, 5},
                              {6, 7, 8, 9, 10},
                              {11, 12, 13, 14, 15},
                              {16, 17, 18, 19, 20},
                              {21, 22, 23, 24, 25} };
             int[][] matrix2 = {{1, 2, 3, 4, 5},
                              {6, 7, 8, 9, 10},
                              {16, 17, 18, 19, 20} };
            printDiagonally(matrix2);
        }

        // Problem 3. Print a matrix in Spiral order
        {
            System.out.println("\nProblem 3. Print Matrix Spirally");
            int[][] twoDMatrix = {{1, 2, 3, 4},
                                  {5, 6, 7, 8},
                                  {9, 10, 11, 12},
                                  {13, 14, 15, 16},
                                  {17, 18, 19, 20}};
            printMatrixSpirally(twoDMatrix);
        }

        // Problem 4. Given a string of 1s and 0s with '?' in between. A '?' can be either one or zero.
        {
            System.out.println("\nProblem 4. Replace ? with 0s and 1s");
            String str = "0?0?1";
            printCombinationOnesZeros(str);
        }

        // Problem 6. Find the unique character present in a string
        {
            System.out.println("\nProblem 6. Find the unique character present in a string");
            String s = "GeeksForGeeks";
            System.out.println(s.charAt(firstUniqueCharacter(s)));
        }

        // Problem 7. Count the number of islands in a 2D matrix
        {
            System.out.println("\nProblem 7. Count the number of islands in a 2D matrix");
            List<List<Integer>> twoDMat = new ArrayList<List<Integer>>() {{
                add(Arrays.asList(1, 1, 0, 0, 0));
                add(Arrays.asList(0, 1, 0, 0, 1));
                add(Arrays.asList(1, 0, 0, 1, 1));
                add(Arrays.asList(0, 0, 0, 0, 0));
                add(Arrays.asList(1, 0, 1, 0, 1));
            }};
            System.out.println(countConnectedIslands(twoDMat));
        }
    }
}

