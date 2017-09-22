package com.pragad.javaproblems.JavaProblems;

import java.util.*;
import com.google.common.collect.Multimap;
import com.google.common.collect.HashMultimap;

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
 * char findFirstNonMatchingChar(String s1, String s2)
 *
 * PROBLEM 6: Find the unique character present in a string
 * int firstUniqueCharacter(String s)
 *
 * PROBLEM 7: Count the number of islands in a 2D matrix
 * int countConnectedIslands(List<List<Integer>> twoDMat)
 *
 * PROBLEM 8: Given a input stream of numbers, compute the median after each entry
 * double findMedian(int num, PriorityQueue<Integer> ascPq, PriorityQueue<Integer> dscPq)
 *
 * PROBLEM 9. Find the max sub array sum
 * int maxSubArraySum(int[] arr)
 * void maxSubArrayThatHasSum(int[] arr)
 *
 * PROBLEM 10. Find the max sub array product
 * int maxProduct(int[] nums)
 *
 * PROBLEM 11. Find k'th smallest number in unsorted array
 * int findKthSmallest(ArrayList<Integer> al, int k)
 *
 * PROBLEM 12. Find two numbers add to a sum
 * int[] twoSum(int[] nums, int sum)
 *
 * PROBLEM 13. Find all pairs that add to a sum
 */

// Pair Class
// https://stackoverflow.com/questions/521171/a-java-collection-of-value-pairs-tuples
final class Pair<L, R> {
    private final L left;
    private final R right;

    public Pair (L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    @Override
    public int hashCode() {
        return left.hashCode() ^ right.hashCode();
    }

    @Override
    public boolean equals (Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair<Object, Object> p = (Pair<Object, Object>) o;
        return this.left.equals(p.getLeft()) && this.right.equals(p.getRight());
    }

    @Override
    public String toString() {
        return left + " : " + right;
    }
}

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
        Queue<PairRowCol> dfsStack = new LinkedList<>();
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
    // PROBLEM 8. Given a input stream of numbers, compute the median after each entry
    // -------------------------------------------------------------------------
    static double findMedian(int num, PriorityQueue<Integer> ascPq, PriorityQueue<Integer> dscPq) {
        // Very first element can be inserted anywhere
        if (dscPq.size() == 0) {
            dscPq.add(num);
            return dscPq.peek();
        }

        // If the new number is smaller than top element in dscPq, it should go there
        // If the new number is greater than top element in ascPq, it should go there
        if (num < dscPq.peek()) {
            dscPq.add(num);
            if (dscPq.size() - ascPq.size() > 1) {
                ascPq.add(dscPq.poll());
            }
        } else {
            ascPq.add(num);
            if (ascPq.size() - dscPq.size() > 1) {
                dscPq.add(ascPq.poll());
            }
        }

        if (ascPq.size() > dscPq.size()) {
            return ascPq.peek();
        } else if (dscPq.size() > ascPq.size()) {
            return dscPq.peek();
        } else {
            return (ascPq.peek() + dscPq.peek()) / 2.0;
        }
    }

    static void findMedian() {
        ArrayList<Integer> al = new ArrayList<>(Arrays.asList(5, 2, 4, 7, 2, 9, 1, 15, -3, 8, 13, -1, 3, 1, 6));
        // VERY IMP: Java default order of priority queue is ascending order
        PriorityQueue<Integer> dscPq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> ascPq = new PriorityQueue<>();
        System.out.println(al);
        for (Integer a : al) {
            System.out.println(findMedian(a, ascPq, dscPq));
        }
    }

    // -------------------------------------------------------------------------
    // PROBLEM 9. Find the max sub array sum
    // -------------------------------------------------------------------------
    static int maxSubArraySum(int[] nums) {
        int curSum = 0;
        int maxSum = Integer.MIN_VALUE;
        // Either approach works
        //int maxSum = nums[0];
        //for (int i = 1; i < nums.length; i++) {
        for (int i = 0; i < nums.length; i++) {
            curSum = Math.max(nums[i], nums[i] + curSum);
            maxSum = Math.max(curSum, maxSum);
        }
        return maxSum;
    }

    static void maxSubArrayThatHasSum(int[] nums) {
        int curSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int stIdx = 0;
        int endIdx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[i] + curSum) {
                curSum = nums[i];
                stIdx = i;
            } else {
                curSum = nums[i] + curSum;
            }

            if (curSum > maxSum) {
                maxSum = curSum;
                endIdx = i;
            }
        }
        // IMP: If all negative numbers with two equals nums, then stIdx will be greater than endIdx
        // {-2,-3,-4,-1,-2,-1,-5,-3}
        if (stIdx > endIdx) {
            stIdx = endIdx;
        }
        System.out.println("St Idx: " + stIdx + "; EndIdx: " + endIdx);
    }

    // -------------------------------------------------------------------------
    // PROBLEM 10. Find the max sub array product
    // -------------------------------------------------------------------------
    static int maxProduct(int[] nums) {
        int maxPosProd = nums[0];
        int minNegProd = nums[0];
        int finalMaxProd = nums[0];
        int tmpMax;
        int tmpMin;
        for (int i = 1; i < nums.length; i++) {
            tmpMax = maxPosProd;
            tmpMin = minNegProd;
            maxPosProd = Math.max(Math.max(nums[i] * tmpMax, nums[i] * tmpMin), nums[i]);
            minNegProd = Math.min(Math.min(nums[i] * tmpMax, nums[i] * tmpMin), nums[i]);
            finalMaxProd = Math.max(maxPosProd, finalMaxProd);
        }
        return finalMaxProd;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 11. Find k'th smallest number in unsorted array
    // -------------------------------------------------------------------------
    static int findKthSmallest(ArrayList<Integer> al, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int i = 0;
        for (; i < k; i++) {
            pq.add(al.get(i));
        }

        for (; i < al.size(); i++) {
            if (pq.peek() > al.get(i)) {
                pq.poll();
                pq.add(al.get(i));
            }
        }

        return pq.peek();
    }

    // -------------------------------------------------------------------------
    // PROBLEM 12. Find two numbers add to a sum
    // -------------------------------------------------------------------------
    static int[] twoSum(int[] nums, int sum) {
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        int[] pairIdx = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (sumMap.containsKey(sum - nums[i])) {
                pairIdx[0] = sumMap.put(sum - nums[i], i);
                pairIdx[1] = i;
                return pairIdx;
            } else {
                sumMap.put(nums[i], i);
            }
        }
        return pairIdx;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 13. Find all pairs that add to a sum
    // -------------------------------------------------------------------------
    static List<Pair<Integer, Integer>> twoSumAllPairs(int[] nums, int sum) {
        Map<Integer, List<Integer>> sumMap = new HashMap<>();
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (sumMap.containsKey(sum - nums[i])) {
                for (Integer idx : sumMap.get(sum - nums[i])) {
                    result.add(new Pair<>(idx, i));
                }
                // Insert the current element into the map
                if (sumMap.containsKey(nums[i])) {
                    sumMap.get(nums[i]).add(i);
                } else {
                    sumMap.put(nums[i], new ArrayList<>(Arrays.asList(i)));
                }
            } else {
                sumMap.put(nums[i], new ArrayList<>(Arrays.asList(i)));
            }
        }
        for (Map.Entry<Integer, List<Integer>> e : sumMap.entrySet()) {
            System.out.println(e.getKey() + ":" + e.getValue());
        }
        return result;
    }

    // Using Multi-map
    static List<Pair<Integer, Integer>> twoSumAllPairsMultiMap(int[] nums, int sum) {
        Multimap<Integer, Integer> sumMap = HashMultimap.create();
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (sumMap.containsKey(sum - nums[i])) {
                for (Integer idx : sumMap.get(sum - nums[i])) {
                    result.add(new Pair<>(idx, i));
                }
                // Insert the current element into the map
                sumMap.put(nums[i], i);
            } else {
                sumMap.put(nums[i], i);
            }
        }
        for (Map.Entry<Integer, Collection<Integer>> e : sumMap.asMap().entrySet()) {
            System.out.println(e.getKey() + ":" + e.getValue());
        }
        return result;
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

        // PROBLEM 8: Given a input stream of numbers, compute the median after each entry
        {
            System.out.println("\nProblem 8. Given a input stream of numbers, compute the median after each entry");
            findMedian();
        }

        // PROBLEM 9. Find the max sub array sum
        {
            System.out.println("\nPROBLEM 9. Find the max sub array sum");
            int arr1[] = {-2,-3,4,-1,-2,1,5,-3};
            int arr2[] = {15, 2, 4, 8, 9, 5, 10, 13, 23};
            int arr3[] = {5, 6, 1, -2, -4, 3, 1, 5};
            int arr4[] = {4, 1, -3, 2, 6 , -5};
            int arr5[] = {-2,-3,-4,-1,-2,-1,-5,-3};
            System.out.println("Max sub array sum: " + maxSubArraySum(arr1));
            maxSubArrayThatHasSum(arr1);
            System.out.println("Max sub array sum: " + maxSubArraySum(arr2));
            maxSubArrayThatHasSum(arr2);
            System.out.println("Max sub array sum: " + maxSubArraySum(arr3));
            maxSubArrayThatHasSum(arr3);
            System.out.println("Max sub array sum: " + maxSubArraySum(arr4));
            maxSubArrayThatHasSum(arr4);
            System.out.println("Max sub array sum: " + maxSubArraySum(arr5));
            maxSubArrayThatHasSum(arr5);
        }

        // PROBLEM 10. Find the max sub array product
        {
            int[] arr1 = {-2, -3, 0, -2, -40}; // 80
            int[] arr2 = {-1, -3, -10, 0, 60}; // 60
            int[] arr3 = {6, -3, -10, 0, 2};   // 180
            System.out.println("\nPROBLEM 10. Find the max sub array product");
            System.out.println("Max product: " + maxProduct(arr1));
            System.out.println("Max product: " + maxProduct(arr2));
            System.out.println("Max product: " + maxProduct(arr3));
        }

        // PROBLEM 11. Find k'th smallest number in unsorted array
        {
            System.out.println("\nProblem 11. Find k'th smallest number in unsorted array");
            ArrayList<Integer> al = new ArrayList<>();
            al.add(7);
            al.add(10);
            al.add(4);
            al.add(3);
            al.add(20);
            al.add(15);
            System.out.println(findKthSmallest(al, 3));
            System.out.println(findKthSmallest(al, 5));
        }

        // PROBLEM 12. Find two numbers add to a sum
        {
            System.out.println("\nPROBLEM 12. Find two numbers add to a sum");
            int[] arr1 = {2, 7, 11, 15};
            System.out.println(Arrays.toString(twoSum(arr1, 13)));
        }

        // PROBLEM 13. Find all pairs that add to a sum
        {
            System.out.println("\nPROBLEM 13. Find all pairs that add to a sum");
            int[] arr1 = {2, 7, 11, 15};
            int[] arr2 = {5, 3, -2, 4, 3, 6, 5, 5, -1, 3, 10, 2};
            System.out.println(twoSumAllPairs(arr2, 8));
            System.out.println();
            System.out.println(twoSumAllPairsMultiMap(arr2, 8));
        }
    }
}
