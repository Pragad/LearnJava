import java.util.*;
import java.util.stream.*;

//import com.google.common.collect.Multimap;
//import com.google.common.collect.HashMultimap;

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
 * List<Pair<Integer, Integer>> twoSumAllPairs(int[] nums, int sum) 
 * 
 * PROBLEM 14. Count sub arrays that will add to a sum
 * int countSubarraySum(int[] nums, int sum)
 *
 * PROBLEM 15. Find Shortest Unsorted Continuous Subarray
 * int findUnsortedSubarray(int[] nums) 
 *
 * PROBLEM 16. Finding greatest sum of elements of array which is divisible by a given number
 *
 * PROBLEM 17. Find Hamilton distance between two numbers
 *
 * PROBLEM 18. Print Matrix Spirally
 *
 * PROBLEM 19. Given a string of 1s and 0s with '?' in between. A '?' can be either one or zero.
 *
 * PROBLEM 20. Given an integer x, find ONE digit who is equal to its adjacent digits,
 *
 * PROBLEM 21. Find Equilibrium Index of an array. Sum of left elements = Sum of Right elements
 *
 * PROBLEM 22. Find if two rectangles overlap
 *
 * PROBLEM 22b. Find overlapping rectangle
 *
 * PROBLEM 23. Find Largest Subarray with equal number of 0s and 1s
 *
 * PROBLEM 24. 3 number sum closest
 *
 * PROBLEM 25. 1 Missing and 1 Duplicate
 *
 * PROBLEM 26. Maximize stock profit
 *
 * PROBLEM 27. Round off seconds to minutes, hours, day, months, years
 *
 * PROBLEM 28. Product of all other numbers
 *
 * PROBLEM 29. Find rotation index
 *
 * PROBLEM 30. Search in rotated sorted array
 *
 * PROBLEM 31. Find shortest route
 *
 * PROBLEM 32. Find unique number in 4billion ints to web crawler visited pages
 *
 * PROBLEM 33. Find if two numbers add to a sum
 *
 * PROBLEM 34. Sorting almost sorted array misplaced by k elements
 *
 * PROBLEM 35. Merge all overlapping intervals
 *
 * PROBLEM 36. Convert number to words
 *
 * PROBLEM 37. Phone number permutations
 *
 * PROBLEM 38. Reverse words in a sentence
 *
 * PROBLEM 39. Love rectangle - Overlapping rectanlge
 *
 * PROBLEM 40. Binary to Decimal and Decimal to binary
 *
 * PROBLEM 41. Roman to decimal and decimal to roman
 *
 * PROBLEM 42. Find smallest range containing elements from k lists
 *
 * PROBLEM 43. Rotate a matrix clockwise by 90degrees
 *
 * PROBLEM 44. Top K Frequent Elements
 *
 * PROBLEM 45a. Convert decimal to any base
 * PROBLEM 45b. Convert any base to decimal
 *
 * PROBLEM 46. ZigZag conversion
 *
 * PROBLEM 47. Minimun length between numbers with max frequency
 *
 * PROBLEM 48. Pancake Sort - Sort just by using flip
 *
 * PROBLEM 49. Flatten list of list of integers
 *
 * Problem 50. Flood fill
 *
 * Problem 51. Find all subsets without duplicates
 *
 * Problem 51b. Find all subsets with duplicates
 *
 * PROBLEM 49. Isomorphic Strings
 *
 * PROBLEM 50. Find k length combinations
 *
 * PROBLEM 51. Word compress
 *
 * PROBLEM 52. Minimum flips to make 1s in the left and 0s in the right
 *
 * PROBLEM 53. Arrange list of numbers such that they form the largest number
 *
 * PROBLEM 54. Maximum subsets to sort the array
 *
 * PROBLEM 55. Word ladder - Transform from one word to another
 *
 * PROBLEM 56. Merge two sorted arrays into end of first array
 *
 * PROBLEM 57. Merge k sorted arrays
 *
 * PROBLEM 58. H-tree construction
 *
 * PROBLEM 59. Find all elements present more than n/3 times
 *
 */

// -----------------------------------------------------------------------------
// Pair Class
// https://stackoverflow.com/questions/521171/a-java-collection-of-value-pairs-tuples
// -----------------------------------------------------------------------------
final class Pair<L, R> {
    private L left;
    private R right;

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

    public void setLeft(L left) {
        this.left = left;
    }

    public void setRight(R right) {
        this.right = right;
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

// -----------------------------------------------------------------------------
// Point class
// -----------------------------------------------------------------------------
class Point {
    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

// -----------------------------------------------------------------------------
// Rectangle class
// https://www.interviewcake.com/question/java/rectangular-love
// -----------------------------------------------------------------------------
class Rectangle {
    // coordinates of bottom left corner
    private int leftX;
    private int bottomY;

    // dimensions
    private int width;
    private int height;

    public Rectangle(int leftX, int bottomY, int width, int height) {
        this.leftX = leftX;
        this.bottomY = bottomY;
        this.width  = width;
        this.height = height;
    }

    public int getLeftX() {
        return leftX;
    }

    public int getBottomY() {
        return bottomY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}


public class AllJavaAlgoProblems {
    // -------------------------------------------------------------------------
    // Problem 1.
    // Print all possilbe steps down a ladder
    // -------------------------------------------------------------------------
    public static void printLadderPaths(int numSteps, StringBuilder path) {
        if (numSteps == 0) {
            System.out.println(path);
            return;
        }

        if (numSteps >= 1) {
            path.append('1');
            printLadderPaths(numSteps - 1, path);
            path.setLength(path.length()-1);
        }

        if (numSteps >= 2) {
            path.append('2');
            printLadderPaths(numSteps - 2, path);
            path.setLength(path.length()-1);
        }
    }

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
    // https://www.geeksforgeeks.org/zigzag-or-diagonal-traversal-of-matrix/
    // https://www.geeksforgeeks.org/print-matrix-diagonal-pattern/
    // https://www.geeksforgeeks.org/zigzag-or-diagonal-traversal-of-matrix/
    // -------------------------------------------------------------------------
    static void printMatrixDiagonally(int[][] twoDMat) {
        int rows = twoDMat.length;
        int cols = twoDMat[0].length;
        for (int i = 0; i < rows + cols; i++) {
            for (int j = 0; j <= i; j++) {
                int k = i - j;
                if (k < rows && j < cols) {
                    System.out.print(twoDMat[k][j] + ", ");
                }
            }
            System.out.println();
        }
    }

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
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
            /*
            if (charMap.containsKey(c)) {
                charMap.put(c, charMap.get(c) + 1);
            } else {
                charMap.put(c, 1);
            }
            */
        }

        for (int i = 0; i < s.length(); i++) {
            if (charMap.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    public static int firstUniqueCharacter2(String s) {
        int[] charMap = new int[256];
        for (char c : s.toCharArray()) {
            charMap[c]++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (charMap[s.charAt(i)] == 1) {
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

    public static int dfsRecursive(List<List<Integer>> twoDMat, int row, int col) {
        twoDMat.get(row).set(col, 2);
        int count = 1;
        for (int i = 0; i < 8; i++) {
            int newRow = row + ROW_DIRECTION.get(i);
            int newCol = col + COL_DIRECTION.get(i);
            if (newRow >= 0 && newRow < twoDMat.size() &&
                newCol >= 0 && newCol < twoDMat.get(newRow).size() &&
                twoDMat.get(newRow).get(newCol) == 1) {
                count += dfsRecursive(twoDMat, newRow, newCol);
            }
        }
        return count;
    }

    public static void dfsIterative(List<List<Integer>> twoDMat, int row, int col) {
        //Stack<PairRowCol> dfsStack = new Stack<PairRowCol>();
        //Queue<PairRowCol> dfsStack = new LinkedList<>();
        Queue<PairRowCol> dfsStack = new ArrayDeque<>();
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
        int maxCount = Integer.MIN_VALUE;
        for (int i = 0; i < twoDMat.size(); i++) {
            for (int j = 0; j < twoDMat.get(i).size(); j++) {
                if (twoDMat.get(i).get(j) == 1) {
                    maxCount = Math.max(maxCount, dfsRecursive(twoDMat, i, j));
                    //dfsIterative(twoDMat, i ,j);
                    countIslands++;
                }
            }
        }
        System.out.println("Size of max island: " + maxCount);
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
        int tmpStIdx = 0;
        int endIdx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[i] + curSum) {
                curSum = nums[i];
                tmpStIdx = i;
            } else {
                curSum = nums[i] + curSum;
            }

            if (curSum > maxSum) {
                maxSum = curSum;
                stIdx = tmpStIdx;
                endIdx = i;
            }
        }
        // IMP: If all negative numbers with two equals nums, then stIdx will be greater than endIdx
        // {-2,-3,-4,-1,-2,-1,-5,-3}
        /*
        if (stIdx > endIdx) {
            stIdx = endIdx;
        }
        */
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
	/*
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
	*/

    // -------------------------------------------------------------------------
    // PROBLEM 14. Count sub arrays that will add to a sum
    //
    // Sum :    5
    // Idx:     0, 1,  2, 3,  4,  5, 6, 7 ,  8, 9,10, 11
    // Arr :    4, 1, -3, 2,  6, -5, 4, 1 , -3, 2, 6 , -5
    // Map : 0, 4, 5,  2, 4, 10,  5, 9, 10,  7, 9, 15, 10 
    // Find: 0,-1, 0, -3,-1,  5,  0, 4,  5,  2, 4, 10,  5
    // -------------------------------------------------------------------------
    static int countSubarraySum2(int[] nums, int sum) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        int subArrayCount = 0;
        //sumMap.put(0, new ArrayList<>(Arrays.asList(-1)));
        sumMap.put(0, -1);
        int curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            if (sumMap.containsKey(curSum - sum)) {
                subArrayCount += sumMap.get(curSum - sum);
            }
            sumMap.put(curSum, sumMap.getOrDefault(curSum, 0) + 1);
        }
        return subArrayCount;
    }

    static int countSubarraySum(int[] nums, int sum) {
        Map<Integer, List<Integer>> sumMap = new HashMap<>();
        int subArrayCount = 0;
        sumMap.put(0, new ArrayList<>(Arrays.asList(-1)));
        int curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            if (sumMap.containsKey(curSum)) {
                sumMap.get(curSum).add(i);
            } else {
                sumMap.put(curSum, new ArrayList<>(Arrays.asList(i)));
            }

            if (sumMap.containsKey(curSum - sum)) {
                for (Integer stIdx : sumMap.get(curSum - sum)) {
                    // VERY IMP: Start index should be greater than end index
                    if ((stIdx + 1) <= i) {
                        System.out.println("StIdx: "  + (stIdx + 1) + " - EndIdx: " + i);
                        subArrayCount++;
                    }
                }
            }
        }
        return subArrayCount;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 15. Find Shortest Unsorted Continuous Subarray
    // Given an integer array, you need to find one continuous subarray
    // that if you only sort this subarray in ascending order, then the whole array
    // will be sorted in ascending order, too.
    //
    // http://www.geeksforgeeks.org/minimum-length-unsorted-subarray-sorting-which-makes-the-complete-array-sorted/
    // -------------------------------------------------------------------------
    static int findUnsortedSubarray(int[] nums) {
        int stIdx = 0;
        int endIdx = 0;
        int maxNum = nums[0];
        int minNum = nums[nums.length - 1];
        for (int i = 1; i < nums.length; i++) {
            maxNum = Math.max(maxNum, nums[i]);
            minNum = Math.min(minNum, nums[nums.length - i - 1]);
            if (nums[nums.length - i - 1] > minNum) {
                stIdx = nums.length - i - 1;
            }
            if (nums[i] < maxNum) {
                endIdx = i;
            }
        }
        return endIdx - stIdx + 1;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 16. Finding greatest sum of elements of array which is divisible by a given number
    // -------------------------------------------------------------------------
    static int greatestSumOfSubarrayDivisibleByK(int[] nums, int k) {
        int[] sumArr1 = new int[k];
        int[] sumArr2 = new int[k];

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < k; j++) {
                int t = (sumArr1[j] + nums[i]) % k;
                sumArr2[t] = Math.max(sumArr2[t], sumArr1[j] + nums[i]);
            }
            sumArr1 = sumArr2;
        }

        return sumArr1[0];
    }

    // -------------------------------------------------------------------------
    // PROBLEM 17. Find Hamilton distance between two numbers
    // Number of positions at which the corresponding bits are different
    // -------------------------------------------------------------------------
    static int hammingDistance(int x, int y) {
        int xorNum = x ^ y;
        int count = 0;
        while (xorNum != 0) {
            xorNum &= (xorNum - 1);
            count++;
        }
        return count;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 18. Print Matrix Spirally
    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------
    // PROBLEM 19. Given a string of 1s and 0s with '?' in between. A '?' can be either one or zero.
    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------
    // PROBLEM 20. Given an integer x, find ONE digit who is equal to its adjacent digits,
    // -------------------------------------------------------------------------
    static int largestNumByRemovingDup(int num) {
        StringBuilder sb = new StringBuilder(String.valueOf(num));

        for (int i = 0; i < sb.length(); i++) {
            if (i + 1 < sb.length() && sb.charAt(i) == sb.charAt(i + 1)) {
                if (i + 2 < sb.length() && (sb.charAt(i) < sb.charAt(i + 2))) {
                    sb.deleteCharAt(i);
                    return Integer.parseInt(sb.toString());
                } else if (i + 2 > sb.length()) {
                    // We have reached the last duplicate character. Safe to remove it
                    sb.deleteCharAt(i);
                    return Integer.parseInt(sb.toString());
                }
            }
        }

        for (int i = sb.length() - 1; i >= 0; i--) {
            if (i - 1 >= 0 && sb.charAt(i) == sb.charAt(i - 1)) {
                if (i - 2 >= 0 && sb.charAt(i) > sb.charAt(i - 2)) {
                    sb.deleteCharAt(i);
                    return Integer.parseInt(sb.toString());
                }
            }
        }

        for (int i = sb.length() - 1; i >= 0; i--) {
            if (i - 1 >= 0 && sb.charAt(i) == sb.charAt(i - 1)) {
                sb.deleteCharAt(i);
                return Integer.parseInt(sb.toString());
            }
        }
        return num;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 21. Find Equilibrium Index of an array. Sum of left elements = Sum of Right elements
    // -------------------------------------------------------------------------
    static int equilibriumIndex(int[] nums) {
        int totalSum = IntStream.of(nums).sum();
        int curSum = 0;

        for (int i = 0; i < nums.length; i++) {
            totalSum -= nums[i];
            if (curSum == totalSum) {
                return i;
            }
            curSum += nums[i];
        }
        return -1;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 22. Find if two rectangles overlap
    // -------------------------------------------------------------------------
    // Points are two diagnoals of a rectangle. a1.x > a2.x and a1.y > a2.y
    static boolean isRectanglesOverlap(Point a1, Point a2, Point b1, Point b2) {
        return (a1.x <= b2.x &&
                a1.y >= b2.y &&
                a2.x >= b1.x &&
                a2.y <= b1.y);
    }

    // TODO
    // -------------------------------------------------------------------------
    // PROBLEM 22b. Find overlapping rectangle
    // https://www.interviewcake.com/question/java/rectangular-love
    // -------------------------------------------------------------------------
    /*
    public static class Rectangle {

        // coordinates of bottom left corner
        private int leftX;
        private int bottomY;

        // dimensions
        private int width;
        private int height;

        public Rectangle(int leftX, int bottomY, int width, int height) {
            this.leftX = leftX;
            this.bottomY = bottomY;
            this.width  = width;
            this.height = height;
        }

        public int getLeftX() {
            return leftX;
        }

        public int getBottomY() {
            return bottomY;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }
    */

    // -------------------------------------------------------------------------
    // PROBLEM 23. Find Largest Subarray with equal number of 0s and 1s
    // -------------------------------------------------------------------------
    static void printLargestSubArrayZeroOne(int[] nums) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        int total = 0;
        int maxArr = 0;
        int stIdx = 0;
        int endIdx = 0;

        sumMap.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                total -= 1;
            } else if (nums[i] == 1) {
                total += 1;
            }

            if (sumMap.containsKey(total)) {
                if (maxArr < (i - sumMap.get(total) + 1) + 1) {
                    stIdx = sumMap.get(total) + 1;
                    endIdx = i;
                    maxArr = endIdx - stIdx + 1;
                }
            } else {
                sumMap.put(total, i);
            }
        }
        System.out.println("St Idx: " + stIdx + "; EndIdx: " + endIdx);
        for (int i = stIdx; i <= endIdx; i++) {
            System.out.print(nums[i] + ", ");
        }
        System.out.println();
    }

    // -------------------------------------------------------------------------
    // PROBLEM 24. 3 number sum AND closest
    // -------------------------------------------------------------------------
    // This does not use HashSet to find unique entries
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        for (int i = 0; i < nums.length; i++)
        {
            int k = nums.length - 1;
            // VERY IMPORTANT. This hugely saves time
            if (i != 0 && nums[i] == nums[i-1]) {
                continue;
            }
            for (int j = i + 1; j < k; )
            {
                if (nums[i] + nums[j] + nums[k] == 0)
                {
                    resultList.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k])));
                    j++;
                    k--;
                    while (j < nums.length && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    while (k >= 0 && nums[k] == nums[k + 1]) {
                        k--;
                    }                    
                }
                else if (nums[i] + nums[j] + nums[k] > 0)
                {
                    k--;
                    while (k >= 0 && nums[k] == nums[k + 1]) {
                        k--;
                    }
                }
                else
                {
                    j++;
                    while (j < nums.length && nums[j] == nums[j - 1]) {
                        j++;
                    }
                }
            }
        }
        return resultList;
    }

    // This uses HashSet to find unique entries
    static List<List<Integer>> threeSumHashSet(int[] nums) {
        Arrays.sort(nums);
        // Copy into Set to remove duplicates
        Set<List<Integer>> result = new HashSet<>();
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        for (int i = 0; i < nums.length; i++)
        {
            int k = nums.length - 1;
            // VERY IMPORTANT. This hugely saves time
            if (i != 0 && nums[i] == nums[i-1]) {
                continue;
            }
            for (int j = i + 1; j < k; )
            {
                if (nums[i] + nums[j] + nums[k] == 0)
                {
                    result.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k])));
                    j++;
                    k--;
                }
                else if (nums[i] + nums[j] + nums[k] > 0)
                {
                    k--;
                }
                else
                {
                    j++;
                }
            }
        }
        return new ArrayList<List<Integer>>(result);       
    }

    static void printThreeNumSum(int[] arr, int sum) {
        int j;
        int k;
        for (int i = 0; i < arr.length - 1; i++) {
            j = i + 1;
            k = arr.length - 1;
            while (j < k) {
                if (arr[i] + arr[j] + arr[k] == sum) {
                    System.out.println(i + ", " + j + ", " + k);
                    return;
                } else if (arr[i] + arr[j] + arr[k] < sum) {
                    j++;
                } else {
                    k--;
                }
            }
        }
    }

    static void printThreeNumSumClosest(int[] arr, int sum) {
        int j;
        int k;
        int p = -1;
        int q = -1;
        int r = -1;
        int closestSum = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            j = i + 1;
            k = arr.length - 1;
            while (j < k) {
                if (closestSum > Math.abs(arr[i] + arr[j] + arr[k] - sum)) {
                    closestSum = Math.abs(arr[i] + arr[j] + arr[k] - sum);
                    p = i;
                    q = j;
                    r = k;
                } else if (arr[i] + arr[j] + arr[k] < sum) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        System.out.println(p + ", " + q + ", " + r);
    }

    // -------------------------------------------------------------------------
    // PROBLEM 25. 1 Missing and 1 Duplicate
    // -------------------------------------------------------------------------
    static void findMissingAndDuplicate(int[] nums) {
        int currentXor = 0;
        for (int n : nums) {
            currentXor ^= n;
        }
        for (int i = 1; i <= nums.length; i++) {
            currentXor ^= i;
        }

        int rightMostSetBit = currentXor & ~(currentXor - 1);
        int numsWithRSB = 0;
        int numsWithoutRSB = 0;
        for (int n : nums) {
            if ((n & rightMostSetBit) == 0) {
                numsWithoutRSB ^= n;
            } else {
                numsWithRSB ^= n;
            }
        }

        for (int i = 1; i <= nums.length; i++) {
            if ((i & rightMostSetBit) == 0) {
                numsWithoutRSB ^= i;
            } else {
                numsWithRSB ^= i;
            }
        }

        System.out.println(numsWithRSB + ", " + numsWithoutRSB);
    }

    // -------------------------------------------------------------------------
    // PROBLEM 26. Maximize stock profit
    // Assumption: All numbers are positve
    // -------------------------------------------------------------------------
    static int maximizeProfit(int[] stockPrice) {
        if (stockPrice.length <= 1) {
            return 0;
        }
        int curMax = stockPrice[0];
        int curMin = stockPrice[0];
        int maxProfit = 0;
        for (int i = 1; i < stockPrice.length; i++) {
            if (stockPrice[i] > curMax) {
                curMax = stockPrice[i];
                maxProfit = Math.max(maxProfit, curMax - curMin);
                // IMP: Should not continue; After setting max to -1, we will come here always. We should also go and set curMin
                // continue;
            }
            if (stockPrice[i] < curMin) {
                curMin = stockPrice[i];
                curMax = -1;
                continue;
            }
        }
        return maxProfit;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 27. Round off seconds to minutes, hours, day, months, years
    // -------------------------------------------------------------------------
    static void roundOffSeconds(int seconds) {
        final int SECONDS_IN_A_MINUTE = 60;
        final int MINUTES_IN_A_HOUR = 60;
        final int HOURS_IN_A_DAY = 24;
        final int DAYS_IN_A_MONTH = 30;
        final int MONTH_IN_A_YEAR = 12;

        int secs = seconds % SECONDS_IN_A_MINUTE;
        int mins = (seconds / SECONDS_IN_A_MINUTE) % MINUTES_IN_A_HOUR;
        int hours = ((seconds / SECONDS_IN_A_MINUTE) / MINUTES_IN_A_HOUR) % HOURS_IN_A_DAY;
        System.out.println("H: " + hours + " M: " + mins + " S: " + secs);
    }

    // -------------------------------------------------------------------------
    // PROBLEM 28. Product of all other numbers
    // -------------------------------------------------------------------------
    static int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        int totalProduct = 1;
        int zeroCount = 0;
        int zeroPosition = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
                zeroPosition = i;
                // Don't have to proceed if we have more than two zeroes
                if (zeroCount > 1) {
                    break;
                }
                // Skip this number and go to the next number
                continue;
            }
            totalProduct *= nums[i];
        }

        if (zeroCount > 1) {
            return result;
        }
        if (zeroCount == 1) {
            // Just one number is zero. So get the position of Zero and fill the
            // product there
            result[zeroPosition] = totalProduct;
            return result;
        }

        // Now none of the elemnts should have 0
        for (int i = 0; i < nums.length; i++) {
            result[i] = totalProduct / nums[i];
        }

        return result;
    }

    static int[] productExceptSelfWithoutDiv(int[] nums) {
        int[] result = new int[nums.length];
        int totalProduct = 1;
        result[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // IMP: Multiply with nums[i] and not results[i]
            result[i] = nums[i] * result[i - 1];
        }

        int prod = 1;
        for (int i = nums.length - 1; i > 0; i--) {
            result[i] = result[i - 1] * prod;
            prod *= nums[i];
        }
        result[0] = prod;

        return result;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 28. Product of all other numbers
    // -------------------------------------------------------------------------
    static int highestProductOfThreeNumbers(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int firstHigh = Integer.MIN_VALUE;
        int secondHigh = Integer.MIN_VALUE;
        int thirdHigh = Integer.MIN_VALUE;
        int mostMin = Integer.MAX_VALUE;
        int leastMin = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > firstHigh) {
                thirdHigh = secondHigh;
                secondHigh = firstHigh;
                firstHigh = nums[i];
            } else if (nums[i] > secondHigh) {
                thirdHigh = secondHigh;
                secondHigh = nums[i];
            } else if (nums[i] > thirdHigh) {
                thirdHigh = nums[i];
            }

            // VERY IMP: This should be in separate if block
            if (nums[i] < mostMin) {
                leastMin = mostMin;
                mostMin = nums[i];
            } else if (nums[i] < leastMin) {
                leastMin = nums[i];
            }
        }

        System.out.println("FH: " + firstHigh);
        System.out.println("SH: " + secondHigh);
        System.out.println("TH: " + thirdHigh);
        System.out.println("FM: " + mostMin);
        System.out.println("SM: " + leastMin);
        return Math.max(firstHigh * secondHigh * thirdHigh, firstHigh * mostMin * leastMin);
    }

    // -------------------------------------------------------------------------
    // PROBLEM 29. Find rotation index
    // https://www.interviewcake.com/question/java/find-rotation-point
    // -------------------------------------------------------------------------
    static int indexOfRotatedSortedArray() {
        return 0;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 30. Search in rotated sorted array
    // -------------------------------------------------------------------------
    boolean findInSortedRotatedArray(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == num || arr[low] == num || arr[high] == num) {
                return true;
            }

            // Check if first part is sorted
            if (arr[mid] >= arr[low]) {
                // 1st part is sorted. Check if the number lies there
                if (num > arr[low] && num < arr[mid]) {
                    high = mid - 1;
                    low = low + 1;
                    continue;
                } else {
                    low = mid + 1;
                    continue;
                }
            } else {
                // 1st part is not sorted. 2nd part is sorted
                if (num > arr[mid] && num < arr[high]) {
                    low = mid + 1;
                    high = high - 1;
                    continue;
                } else {
                    high = mid - 1;
                    continue;
                }
            }
        }
        return false;
    }

    boolean binarySearch(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] > num) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    boolean binarySearchRec(int[] arr, int stIdx, int endIdx, int num) {
        if (stIdx > endIdx) {
            return false;
        }
        int midIdx = stIdx + ((endIdx - stIdx) / 2);
        if (arr[midIdx] == num) {
            return true;
        } else if (arr[midIdx] > num) {
            return binarySearchRec(arr, stIdx, midIdx - 1, num);
        } else {
            return binarySearchRec(arr, midIdx + 1, endIdx, num);
        }
    }

    // -------------------------------------------------------------------------
    // PROBLEM 31. Find shortest route
    // https://www.interviewcake.com/question/java/mesh-message
    // -------------------------------------------------------------------------
    /*
        Map<String, String[]> network = new HashMap<String, String[]>() {{
            put("Min",     new String[] { "William", "Jayden", "Omar" });
            put("William", new String[] { "Min", "Noam" });
            put("Jayden",  new String[] { "Min", "Amelia", "Ren", "Noam" });
            put("Ren",     new String[] { "Jayden", "Omar" });
            put("Amelia",  new String[] { "Jayden", "Adam", "Miguel" });
            put("Adam",    new String[] { "Amelia", "Miguel", "Sofia", "Lucas" });
            put("Miguel",  new String[] { "Amelia", "Adam", "Liam", "Nathan" });
            put("Noam",    new String[] { "Nathan", "Jayden", "William" });
            put("Omar",    new String[] { "Ren", "Min", "Scott" });
            ...
        }};
    */

    // -------------------------------------------------------------------------
    // PROBLEM 32. Find unique number in 4billion ints to web crawler visited pages
    //
    // Find a way to optimize visited pages of web for a crawler
    // https://www.interviewcake.com/question/python/compress-url-list
    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------
    // PROBLEM 33. Find if two numbers add to a sum
    // https://www.interviewcake.com/question/python/inflight-entertainment
    // -------------------------------------------------------------------------
    static boolean isSumPresent(List<Integer> movieTimes, List<Integer> flightLength) {
        return false;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 34. Sorting almost sorted array misplaced by k elements
    //      
    //      {2, 6, 3, 12, 56, 8}
    //
    // http://stackoverflow.com/questions/2726785/sorting-an-almost-sorted-array-elements-misplaced-by-no-more-than-k
    // -------------------------------------------------------------------------
    static int[] sortKMessedArray(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k + 1);
        for (int i = 0; i <= k; i++) {
            pq.add(arr[i]);
        }
        for (int i = 0, j = k + 1; i < arr.length - k - 1; i++, j++) {
            arr[i] = pq.poll();
            pq.add(arr[j]);
        }
        for (int i = arr.length - k - 1; i < arr.length; i++) {
            arr[i] = pq.poll();
        }
        return arr;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 35. Merge all overlapping intervals
    // -------------------------------------------------------------------------
    static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    static List<Interval> mergeOverlappingIntervals(List<Interval> intervals) {
        // Sort by start time
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start > b.start ? 1 : a.start < b.start ? -1 : 0;
            }
        });

        int stIdx = 0;
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(stIdx).end >= intervals.get(i).start) {
                // Merge these two
                if (intervals.get(i).end > intervals.get(stIdx).end) {
                    intervals.get(stIdx).end = intervals.get(i).end;
                }
            } else {
                stIdx++;
                intervals.get(stIdx).start = intervals.get(i).start;
                intervals.get(stIdx).end = intervals.get(i).end;
            }
        }
        
        // Remove extra entries
        for (int i = intervals.size() - 1; i >= stIdx + 1; i--) {
            intervals.remove(i);
        }

        for (Interval i : intervals) {
            System.out.println("S: " + i.start + "; E: " + i.end);
        }
        return intervals;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 35b. Merge all overlapping intervals
    // Say each list is sorted
    // -------------------------------------------------------------------------
    static List<Interval> mergeOverlappingIntervals(List<Interval> intervalOne, List<Interval> intervalTwo) {
        List<Interval> mergedIntervals = new ArrayList<>();
        int indexOne = 0;
        int indexTwo = 0;
        /*
        while (indexOne < intervalOne.size() && indexTwo < intervalTwo.size()) {
            if (intervalOne.get(indexOne).
        }
        */
        return null;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 36. Convert number to words
    // https://leetcode.com/problems/integer-to-english-words/discuss/70625
    // -------------------------------------------------------------------------
    static String convertThreeNumToWords(Integer num) {
        StringBuilder sb = new StringBuilder();
        String[] ones = {"", "one", "two", "three", "four", "five", "six", "seven", "eight",
                         "nine", "ten", "eleven", "twelve", "thirteen", "forteen", "fifteen",
                         "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tens = {"", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        boolean isHundredSeen = false;
        while (num != 0) {
            if (isHundredSeen) {
                sb.append("and ");
                isHundredSeen = false;
            }
            if (num < 20) {
                sb.append(ones[num])
                  .append(" ");
                break;
            } else if (num < 100) {
                sb.append(tens[num / 10])
                  .append(" ");
                num = num % 10;
            } else {
                sb.append(ones[num / 100])
                  .append(" hundred ");
                num = num % 100;
                isHundredSeen = true;
            }
        }
        return sb.toString();
    }

    static String convertNumToWords(int num) {
        String[] thousands = {"", "thousand", "million", "billion"};
        int count = 0;
        String tmp = "";
        while (num != 0) {
            int rem = num % 1000;
            if (rem != 0) {
                tmp = convertThreeNumToWords(rem) + thousands[count] + " " + tmp;
            }
            num /= 1000;
            count++;
        }
        return tmp;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 37. Phone number permutations
    // -------------------------------------------------------------------------
    static void printPhoneNumberPermutationsRec(int[] nums, int stIdx, Map<Integer, String> numMap, StringBuilder res) {
        if (stIdx == nums.length) {
            System.out.println(res.toString());
            return;
        }
        for (int i = 0; i < numMap.get(nums[stIdx]).length(); i++) {
            /*
            System.out.println("S: " + stIdx);
            System.out.println("N: " + nums[stIdx]);
            System.out.println("M: " + numMap.get(nums[stIdx]));
            System.out.println("C: " + numMap.get(nums[stIdx]).charAt(i));
            */
            res.append(numMap.get(nums[stIdx]).charAt(i));
            printPhoneNumberPermutationsRec(nums, stIdx + 1, numMap, res);
            res.setLength(res.length() - 1);

            if (numMap.get(nums[stIdx]).charAt(i) == '0' || numMap.get(nums[stIdx]).charAt(i) == '1') {
                return;
            }
        }
    }

    static void printPhoneNumberPermutations(int[] nums) {
        StringBuilder sb = new StringBuilder();
        Map<Integer, String> numMap = new HashMap<>();
        numMap.put(0, "0");
        numMap.put(1, "1");
        numMap.put(2, "abc");
        numMap.put(3, "def");
        numMap.put(4, "ghi");
        numMap.put(5, "jkl");
        numMap.put(6, "mno");
        numMap.put(7, "pqrs");
        numMap.put(8, "tuv");
        numMap.put(9, "wxyz");
        printPhoneNumberPermutationsRec(nums, 0, numMap, sb);
    }

    // -------------------------------------------------------------------------
    // PROBLEM 38. Reverse words in a sentence
    // -------------------------------------------------------------------------
    static void reverseString(StringBuilder sb) {
        for (int i = 0; i < sb.length() / 2; i++) {
            char c = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(sb.length() - i - 1));
            sb.setCharAt((sb.length() - i - 1), c);
        }
    }

    static void reverseString(StringBuilder sb, int stIdx, int endIdx) {
        if (stIdx < 0 || stIdx >= sb.length() || endIdx < 0 || endIdx >= sb.length()) {
            System.out.println("Invalid Index");
            return;
        }
        for (; stIdx < endIdx; stIdx++, endIdx --) {
            char c = sb.charAt(stIdx);
            sb.setCharAt(stIdx, sb.charAt(endIdx));
            sb.setCharAt((endIdx), c);
        }
    }

    static String reverseWords(String sentence) {
        StringBuilder sb = new StringBuilder(sentence);
        reverseString(sb, 0, sb.length() - 1);

        int stIdx = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ' ') {
                if (stIdx < i) {
                    reverseString(sb, stIdx, i - 1);
                }
                stIdx = i + 1;
            }
        }
        if (stIdx < sb.length()) {
            reverseString(sb, stIdx, sb.length() - 1);
        }

        // Delete beginning spaces
        int cnt = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ' ') {
                cnt++;
            } else {
                break;
            }
        }
        return sb.substring(cnt);

        // Delete ending spaces
        /*
        int cnt = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ' ') {
                cnt++;
            } else {
                break;
            }
        }
        */

    }

    // -------------------------------------------------------------------------
    // PROBLEM 39. Love rectangle - Overlapping rectanlge
    // https://www.interviewcake.com/question/java/rectangular-love
    // -------------------------------------------------------------------------
    static Rectangle findOverlappingRectangle(Rectangle r1, Rectangle r2) {
        if ((r1.getLeftX() < r2.getLeftX() + r2.getWidth()) &&
            (r1.getBottomY() < r2.getBottomY() + r2.getHeight()) &&
            (r1.getLeftX() + r1.getWidth() > r2.getLeftX()) &&
            (r1.getBottomY() + r1.getHeight() > r2.getBottomY())) {

            int lowX = Math.max(r1.getLeftX(), r2.getLeftX());
            int lowY = Math.max(r1.getBottomY(), r2.getBottomY());
            int highX = Math.min(r1.getLeftX() + r1.getWidth(), r2.getLeftX() + r1.getWidth());
            int highY = Math.min(r1.getBottomY() + r1.getHeight(), r2.getBottomY() + r2.getHeight());

            // Overlap
            Rectangle result = new Rectangle(lowX, lowY, highX - lowX, highY - lowY);
            return result;
        }
        return null;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 40. Binary to Decimal and Decimal to binary
    // -------------------------------------------------------------------------
    static int binaryToDecimal(String binNum) {
        int result = 0;
        for (int i = binNum.length() - 1, j = 0; i >= 0; i--, j++) {
            result += (binNum.charAt(i) - '0') * Math.pow(2, j);
        }
        return result;
    }

    static String decimalToBinary(int num) {
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            int rem = num % 2;
            sb.append(String.valueOf(rem));
            num = num / 2;
        }
        return sb.reverse().toString();
    }

    // -------------------------------------------------------------------------
    // PROBLEM 41. Roman to decimal and decimal to roman
    // -------------------------------------------------------------------------
    static int romanToDecimal(String roman) {
        return 0;
    }

    static String decimalToRoman(int num) {
        List<String> ones = Arrays.asList("", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX");
        List<String> tens = Arrays.asList("", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC");
        List<String> hundreds = Arrays.asList("", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM");

        return "";
    }

    // -------------------------------------------------------------------------
    // PROBLEM 42. Find smallest range containing elements from k lists
    // -------------------------------------------------------------------------
    static Pair findSmallestRange(List<List<Integer>> lists) {
        Pair<Integer, Integer> range = new Pair<>(0, Integer.MAX_VALUE);
        PriorityQueue<Pair<Integer, Pair<Integer, Integer>>> pqNums = new PriorityQueue<>(lists.size(), new Comparator<Pair<Integer, Pair<Integer, Integer>>>() {
            public int compare(Pair<Integer, Pair<Integer, Integer>> p1, Pair<Integer, Pair<Integer, Integer>> p2) {
                //return p1.getLeft() > p2.getLeft() ? 1 : p1.getLeft() < p2.getLeft() ? -1 : 0;
                return p1.getLeft().compareTo(p2.getLeft());
            }
        });
        int maxNum = Integer.MIN_VALUE;
        for (int i = 0; i < lists.size(); i++) {
            maxNum = Math.max(maxNum, lists.get(i).get(0));
            pqNums.add(new Pair(lists.get(i).get(0), new Pair(i, 0)));
        }

        while (true) {
            Pair<Integer, Pair<Integer, Integer>> curNum = pqNums.remove();
            if (maxNum - curNum.getLeft() < range.getRight() - range.getLeft()) {
                range.setLeft(curNum.getLeft());
                range.setRight(maxNum);
            }
            // Check if we reached the end of the list
            if (lists.get(curNum.getRight().getLeft()).size() == curNum.getRight().getRight() + 1) {
                return range;
            } else {
                int num = lists.get(curNum.getRight().getLeft()).get(curNum.getRight().getRight() + 1);
                pqNums.add(new Pair(num, new Pair(curNum.getRight().getLeft(), curNum.getRight().getRight() + 1)));
                maxNum = Math.max(maxNum, num);
            }
        }
    }

    // -------------------------------------------------------------------------
    // PROBLEM 43. Rotate a matrix clockwise by 90degrees
    // -------------------------------------------------------------------------
    static void transposeMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
    
    static void reverseRows(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix[i].length - j - 1];
                matrix[i][matrix[i].length - j - 1] = temp;
            }
        }
    }
    
    static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();        
    }
    
    static void rotateMatrix(int[][] matrix) {
        //printMatrix(matrix);
        transposeMatrix(matrix);
        reverseRows(matrix);
        //printMatrix(matrix);
    }

    // -------------------------------------------------------------------------
    // PROBLEM 44. Top K Frequent Elements
    // Can be done in O(N) using Bucket Sort
    // https://stackoverflow.com/questions/3260653/algorithm-to-find-top-10-search-terms
    // https://stackoverflow.com/questions/185697/the-most-efficient-way-to-find-top-k-frequent-words-in-a-big-word-sequence
    //
    // Instead of creating a freqMap, find the maxCount of a number and create
    // an array of that size
    // List<Integer, List<Integers>> where first Integer is the freq and second is
    // all elements with that freq
    // -------------------------------------------------------------------------
    static List<Integer> topKFrequent(int[] nums, int k) {
        if (nums.length == 0 || k <= 0) {
            return new ArrayList<>();
        }
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int num : nums) {
            numMap.put(num, numMap.getOrDefault(num, 0) + 1);
        }
        
        Map<Integer, List<Integer>> freqMap = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry<Integer, Integer> e : numMap.entrySet()) {
            if (freqMap.containsKey(e.getValue())) {
                freqMap.get(e.getValue()).add(e.getKey());
            } else {
                freqMap.put(e.getValue(), new ArrayList<>(Arrays.asList(e.getKey())));
            }
        }

        List<Integer> topKItems = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> e : freqMap.entrySet()) {
            topKItems.addAll(e.getValue());
            if (topKItems.size() >= k) { 
                break;
            }
        }
        if (topKItems.size() > k) {
            topKItems.subList(k, topKItems.size()).clear();
        }
        return topKItems;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 45a. Convert decimal to any base
    // https://stackoverflow.com/questions/742013/how-to-code-a-url-shortener
    // -------------------------------------------------------------------------
    static String convertDecimalToAnyBase(int num, int base) {
        if (base > 62) {
            return "";
        }
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(chars.charAt(num % base));
            num = num / base;
        }
        return sb.reverse().toString();
    }

    // -------------------------------------------------------------------------
    // PROBLEM 45b. Convert any base to decimal
    // https://github.com/delight-im/ShortURL/blob/master/Java/ShortURL.java
    // -------------------------------------------------------------------------
    static int convertAnyBaseToDecimal(String num, int base) {
        if (base > 62) {
            return -1;
        }
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int result = 0;
        for (char c : num.toCharArray()) {
            result = result * base + chars.indexOf(c);
        }
        /*
        for (int i = num.length() - 1, j = 0; i >= 0; i--, j++) {
            int index = chars.indexOf(num.charAt(i));
            result += index * Math.pow(base, j);
        }
        */

        return result;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 46. ZigZag conversion
    // -------------------------------------------------------------------------
    static String zigzagConversion(String s, int numRows) {
        List<Integer> al = new ArrayList<>();
        List<StringBuilder> strLines = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            strLines.add(new StringBuilder(""));
        }

        for (int x = 0; x < s.length(); ) {
            for (int i = 0; i < numRows && x < s.length(); i++) {
                StringBuilder sb = strLines.get(i);
                strLines.set(i, sb.append(s.charAt(x)));
                x++;
            }
            for (int i = numRows - 2; i >= 1 && x < s.length(); i--) {
                strLines.get(i).append(s.charAt(x));
                x++;
            }
        }
        for (int i = 1; i < strLines.size(); i++) {
            strLines.get(0).append(strLines.get(i));
        }
        return strLines.get(0).toString();
    }

    // -------------------------------------------------------------------------
    // PROBLEM 47. Minimun length between numbers with max frequency
    // -------------------------------------------------------------------------
    static class NumDetails {
        int count;
        int stIdx;
        int endIdx;
        int len;
        
        NumDetails(int count, int stIdx, int endIdx) {
            this.count = count;
            this.stIdx = stIdx;
            this.endIdx = endIdx;
            this.len = endIdx - stIdx + 1;
        }

        @Override
        public String toString() {
            return "Cnt: " + this.count + "; St: " + this.stIdx + "; End: "
                   + this.endIdx + "; Len: " + len;
        }
    }

    static int minLengthMaxFrequencyNumbers(int[] arr) {
        HashMap<Integer, NumDetails> hmap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            NumDetails tmp = hmap.get(arr[i]);
            hmap.put(arr[i], tmp != null ? new NumDetails(tmp.count + 1, tmp.stIdx, i) : new NumDetails(1, i, i));
        }

        List<Map.Entry<Integer, NumDetails>> list = new ArrayList<Map.Entry<Integer, NumDetails>>(hmap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, NumDetails>>() {
            public int compare(Map.Entry<Integer, NumDetails> o1, Map.Entry<Integer, NumDetails> o2) {
                // This sorts in reverse order
                return (o2.getValue().count) - (o1.getValue().count);
            }
        });

        int maxFreq = Integer.MIN_VALUE;
        int minNum = Integer.MAX_VALUE;
        for (Map.Entry<Integer, NumDetails> e : list) {
            if (e.getValue().count >= maxFreq) {
                maxFreq = e.getValue().count;
                //minNum = Math.min(e.getValue().endIdx - e.getValue().stIdx + 1, minNum);
                minNum = Math.min(e.getValue().len, minNum);
            } else {
                break;
            }
        }

        return minNum;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 48. Pancake Sort - Sort just by using flip
    // -------------------------------------------------------------------------
    static int findSmallest(int[] arr, int end) {
        int minNum = Integer.MAX_VALUE;
        int minIdx = -1;
        for (int i = 0; i <= end; i++) {
            if (arr[i] < minNum) {
                minNum = arr[i];
                minIdx = i;
            }
        }
        return minIdx;
    }

    static void flip(int[] arr, int k) {
        for (int i = 0, j = k; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    static int[] pancakeSort(int[] arr) {
        int startIndex = 0;
        int endIndex = arr.length - 1;
        while (endIndex > 0) {
            int smallestIndex = findSmallest(arr, endIndex);
            flip(arr, smallestIndex);
            flip(arr, endIndex);
            endIndex--;
        }
        flip(arr, arr.length - 1);
        return arr;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 49. Flatten list of list of integers
    // https://www.careercup.com/question?id=17727664
    // -------------------------------------------------------------------------
    static class FlattenList {
        List<Integer> flattenedList;
        List<List<Integer>> nestedList;
        int currentIndex = 0;

        public FlattenList(List<List<Integer>> nestedList) {
            this.nestedList = nestedList;
            flattenedList = new ArrayList<>();
        }

        public void flatten() {
            for (List<Integer> list : nestedList) {
                flattenedList.addAll(list);
            }
        }

        public Integer next() {
            return flattenedList.get(currentIndex++);
        }

        public boolean hasNext() {
            return currentIndex < flattenedList.size();
        }
    }

    /*
    static class NestedIterator implements Iterator<Integer> {
        List<Integer> flattenedList;
        int currentIndex = 0;
        
        public NestedIterator(List<NestedInteger> nestedList) {
            flattenedList = new ArrayList<>();
            flatten(nestedList);
        }

        private void flatten(List<NestedInteger> nestedList) {
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger()) {
                    flattenedList.add(ni.getInteger());
                } else {
                    flatten(ni.getList());
                }
            }    
        }
        
        @Override
        public Integer next() {
            return flattenedList.get(currentIndex++);
        }

        @Override
        public boolean hasNext() {
            return currentIndex < flattenedList.size();
        }
    }
    */

    // -------------------------------------------------------------------------
    // Problem 50. Flood fill
    // https://leetcode.com/problems/flood-fill/description/
    // -------------------------------------------------------------------------
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) {
            return image;
        }        
        floodFillDfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }
    
    public void floodFillDfs(int[][] image, int sr, int sc, int curColor, int newColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[sr].length) {
            return;
        }
        if (image[sr][sc] == curColor) {
            image[sr][sc] = newColor;
        } else {
            return;
        }
        
        floodFillDfs(image, sr, sc - 1, curColor, newColor);
        floodFillDfs(image, sr - 1, sc, curColor, newColor);
        floodFillDfs(image, sr, sc + 1, curColor, newColor);
        floodFillDfs(image, sr + 1, sc, curColor, newColor);
        
        return;
    }

    // -------------------------------------------------------------------------
    // Problem 51. Find all subsets without duplicates
    // https://leetcode.com/problems/subsets/description/
    // -------------------------------------------------------------------------
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int numSubsets = (int)Math.pow(2, nums.length);
        
        for (int index = 0; index < numSubsets; index++) {
            int numBitsPresent = (int)(Math.log10(index) / Math.log10(2)) + 1;
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < numBitsPresent; i++) {
                // VERY IMP: NOT equals 0. Should not compare with 1 i.e. " == 1"
                if (((1 << i) & index) != 0) {
                    subList.add(nums[i]);
                }
            }
            result.add(subList);
        }
        return result;
    }

    // -------------------------------------------------------------------------
    // Problem 51b. Find all subsets with duplicates
    // https://leetcode.com/problems/subsets-ii/description/
    // -------------------------------------------------------------------------
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Set<List<Integer>> resultSet = new HashSet<List<Integer>>();
        int numSubsets = (int)Math.pow(2, nums.length);
        
        for (int index = 0; index < numSubsets; index++) {
            int numBitsPresent = (int)(Math.log10(index) / Math.log10(2)) + 1;
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < numBitsPresent; i++) {
                // VERY IMP: NOT equals 0. Should not compare with 1 i.e. " == 1"
                if (((1 << i) & index) != 0) {
                    subList.add(nums[i]);
                }
            }
            Collections.sort(subList);
            resultSet.add(subList);
        }
        result.addAll(resultSet);
        return result;
    }

    // -----------------------------------------------------------------------------
    // PROBLEM 49. Isomorphic Strings
    // i.e. All chars in s, map to another char in t
    // -----------------------------------------------------------------------------
    static boolean isIsomorphic(String s, String t) {
        // IMP: If all ASCII don't need hashset
        int[] sourceCharIndex = new int[256];
        int[] targetCharIndex = new int[256];
        
        if (s.length() != t.length()) {
            return false;
        }
        
        // IMP: Fill the array with -1 to handle below case
        // "ab"
        // "aa"
        Arrays.fill(sourceCharIndex, -1);
        Arrays.fill(targetCharIndex, -1);
        
        for (int i = 0; i < s.length(); i++) {
            if (sourceCharIndex[s.charAt(i)] != targetCharIndex[t.charAt(i)]) {
                return false;
            }
            sourceCharIndex[s.charAt(i)] = i;
            targetCharIndex[t.charAt(i)] = i;
        }
        return true;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 50. Find k length combinations
    // Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
    // https://leetcode.com/problems/combinations/description/
    // If n = 4 and k = 2, a solution is:
    // [[2,4],
    //  [3,4],
    //  [2,3],
    //  [1,2],
    //  [1,3],
    //  [1,4]]
    // Complexity O(n^k)
    // -------------------------------------------------------------------------
    static boolean areDoubleSame(double a, double b) {
        return (Math.abs(a - b) <= 0.000001);
    }

    static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> tmpResult = new ArrayList<>();
        // IMP: We need to pass startIndex (1) and current number of entries (0)
        combineRec(n, k, 0, 1, result, tmpResult);
        return result;
    }
    
    static void combineRec(int n, int k, int current, int start, List<List<Integer>> result, List<Integer> kList) {
        if (current == k) {
            result.add(new ArrayList<>(kList));
            return;
        }
        for (int i = start; i <= n; i++) {
            kList.add(i);
            combineRec(n, k, current + 1, i + 1, result, kList);
            kList.remove(kList.get(kList.size() - 1));
        }
    }

    // -------------------------------------------------------------------------
    // PROBLEM 51. Word compress
    // https://leetcode.com/problems/string-compression/description/
    // -------------------------------------------------------------------------
    static int compress(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        
        int totalCount = 0;        
        for (int baseIndex = 0; baseIndex < chars.length;) {
            char currentChar = chars[baseIndex];
            // VERY IMP: Put current character in target location
            chars[totalCount] = chars[baseIndex];
            int currentCharCount = 1;
            int nextPosition = baseIndex + 1;
            while (nextPosition < chars.length && chars[nextPosition] == chars[baseIndex]) {
                currentCharCount++;
                nextPosition++;
            }
            if (currentCharCount != 1) {
                String strCharCount = String.valueOf(currentCharCount);
                for (char c : strCharCount.toCharArray()) {
                    chars[++totalCount] = c;
                }
            }
            totalCount++;
            baseIndex = nextPosition;
        }
        return totalCount;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 52. Minimum flips to make 1s in the left and 0s in the right
    // https://www.geeksforgeeks.org/minimum-flips-make-1s-left-0s-right-set-1-using-bitmask/
    // https://www.geeksforgeeks.org/minimum-flips-make-1s-left-0s-right-set-2/
    // -------------------------------------------------------------------------
    static int minFlipsToMovesOnesToLeftAndZerosToRight(String bits) {
        int num = Integer.parseInt(bits, 2);
        int numBits = (int)(Math.log(num) / Math.log(2) + 1);
        int bitMask = (int)Math.pow(2, numBits) - 1;
        // System.out.println(Integer.toString(bitMask, 2));

        int minFlips = Integer.MAX_VALUE;
        int i = 0;
        while (i < numBits) {
            minFlips = Math.min(Integer.bitCount(num ^ bitMask), minFlips);
            bitMask = bitMask & (~(1 << i));
            i++;
        }
        return minFlips;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 53. Arrange list of numbers such that they form the largest number
    // Given a list of non negative integers, arrange them such that they form the largest number.
    // https://leetcode.com/problems/largest-number/description/
    // For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330
    // -------------------------------------------------------------------------
    static String largestNumber(int[] nums) {
        StringBuilder result = new StringBuilder();
        List<String> strNums = new ArrayList<>();
        for (int num : nums) {
            strNums.add(String.valueOf(num));
        }
        // Collections.sort(strNums, Collections.reverseOrder());
        Collections.sort(strNums, new Comparator<String>() {
            public int compare (String s1, String s2) {
                String temp1 = s1 + s2;
                String temp2 = s2 + s1;
                // Reverse the order
                return temp2.compareTo(temp1);
            }
        });
        // Corner case in case input has a bunch of 0s
        if (strNums.get(0).equals("0")) {
            return "0";
        }
        for (String strNum : strNums) {
            result.append(strNum);
        }
        return result.toString();
    }

    // -------------------------------------------------------------------------
    // PROBLEM 54. Maximum subsets to sort the array
    // https://leetcode.com/problems/max-chunks-to-make-sorted/description/
    // Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], we split the array into some number of "chunks" (partitions), and individually sort each chunk
    // arr = [1,0,2,3,4]
    // [1, 0], [2], [3], [4]
    // -------------------------------------------------------------------------
    static int maxChunksToSorted(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int maxChunks = 0;
        int maxNumSoFar = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            maxNumSoFar = Math.max(maxNumSoFar, arr[i]);
            if (maxNumSoFar == i) {
                maxChunks++;
            }
        }
        return maxChunks;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 54b. Maximum subsets to sort the array
    // https://leetcode.com/problems/max-chunks-to-make-sorted-ii/description/
    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------
    // PROBLEM 55. Word ladder - Transform from one word to another
    // https://www.geeksforgeeks.org/word-ladder-length-of-shortest-chain-to-reach-a-target-word/
    // https://leetcode.com/problems/word-ladder/description/
    //
    // Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
    // 
    // Only one letter can be changed at a time.
    // Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
    // For example, // Given:
    // beginWord = "hit"
    // endWord = "cog"
    // wordList = ["hot","dot","dog","lot","log","cog"]
    // As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
    // return its length 5.
    // -------------------------------------------------------------------------
    static class WordDetails {
        String word;
        int currentCount;
        
        public WordDetails(String word, int currentCount) {
            this.word = word;
            this.currentCount = currentCount;
        }
    }
    
    static boolean areWordsOneStepAway(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
            }
            if (diffCount > 1) {
                return false;
            }
        }
        return diffCount == 1;
    }
    
    static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Can have a base condition
        //  - False if either words is not in the list
        //  - False if the words are of different length

        Queue<WordDetails> wordsQueue = new ArrayDeque<>();
        // We don't need Visited set
        // Instead we can delete entries from the dictionary (Set)
        Set<String> visitedWords = new HashSet<>();
        int minCount = Integer.MAX_VALUE;
        wordsQueue.add(new WordDetails(beginWord, 1));
        // Should mark visited once we add
        // Eg: hot -> lot -> pot
        // Say you add 'lot' and 'pot'. Later when you look at 'lot' you should not
        // add 'pot' again
        visitedWords.add(beginWord);        
        while (!wordsQueue.isEmpty()) {
            WordDetails currentWord = wordsQueue.remove();
            if (currentWord.word.equals(endWord)) {
                minCount = Math.min(minCount, currentWord.currentCount);
            }    
            // Check if there is a word in the dictionary which is one step away
            for (String dictWord : wordList) {
                if (!visitedWords.contains(dictWord) && areWordsOneStepAway(currentWord.word, dictWord)) {
                    wordsQueue.add(new WordDetails(dictWord, currentWord.currentCount + 1));
                    visitedWords.add(dictWord);
                }
            }
        }
        return minCount == Integer.MAX_VALUE ? 0 : minCount;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 56. Merge two sorted arrays into end of first array
    // -------------------------------------------------------------------------
    static void mergeSortedArrays(int[] arr1, int[] arr2) {
        int endIndex = arr1.length - 1;
        int i = arr1.length - arr2.length - 1;
        int j = arr2.length - 1;
        while (i >= 0 && j >= 0) {
            if (arr1[i] > arr2[j]) {
                arr1[endIndex--] = arr1[i];
                i--;
            } else {
                arr1[endIndex--] = arr2[j];
                j--;
            }
        }

        while (i >= 0) {
            arr1[endIndex--] = arr1[i];
            i--;
        }

        while (j >= 0) {
            arr1[endIndex--] = arr2[j];
            j--;
        }
    }


    // -------------------------------------------------------------------------
    // PROBLEM 57. Merge 'k' sorted arrays
    // -------------------------------------------------------------------------
    static class ArrayDetails {
        int value;
        int arrNumber;
        int nextIndex;

        public ArrayDetails(int value, int arrNumber, int nextIndex) {
            this.value = value;
            this.arrNumber = arrNumber;
            this.nextIndex = nextIndex;
        }
    }

    static int[] mergeKSortedArrays(List<int[]> kArrays) {
        int totalSize = 0;
        PriorityQueue<ArrayDetails> kEntries = new PriorityQueue<>(kArrays.size(),
                new Comparator<ArrayDetails>() {
                    public int compare(ArrayDetails a, ArrayDetails b) {
                        return a.value - b.value;
                    }
                });
        for (int i = 0; i < kArrays.size(); i++) {
            totalSize += kArrays.get(i).length;
            kEntries.add(new ArrayDetails(kArrays.get(i)[0], i, 1));
        }

        int[] merge = new int[totalSize];
        int mergeIndex = 0;
        while (!kEntries.isEmpty()) {
            ArrayDetails entry = kEntries.remove();
            merge[mergeIndex++] = entry.value;
            if (kArrays.get(entry.arrNumber).length != entry.nextIndex) {
                kEntries.add(new ArrayDetails(
                             kArrays.get(entry.arrNumber)[entry.nextIndex],
                             entry.arrNumber,
                             entry.nextIndex + 1));
            }
        }
        return merge;
    }


    // -------------------------------------------------------------------------
    // PROBLEM 58. H-tree construction
    // From Pramp
    // -------------------------------------------------------------------------
    void drawLine(double x1, double y1, double x2, double y2) {
    }

    void drawHTree(double x, double y, int length, int depth) {
        if (depth == 0) {
            return;
        }

        double x1 = x - length / 2.0;
        double y1 = y;
        double x2 = x + length / 2.0;
        double y2 = y;
        drawLine(x1, y1, x2, y2);
        drawLine(x1, y - (length / 2.0), x1, y + (length / 2.0));
        drawLine(x2, y - (length / 2.0), x2, y + (length / 2.0));
        drawHTree(x1 - length / 2.0, y1 - length / 2.0, (int) (length/Math.sqrt(2.0)), depth - 1);
        drawHTree(x1 - length / 2.0, y1 + length / 2.0, (int) (length/Math.sqrt(2.0)), depth - 1);
        drawHTree(x1 + length / 2.0, y1 + length / 2.0, (int) (length/Math.sqrt(2.0)), depth - 1);
        drawHTree(x1 + length / 2.0, y1 - length / 2.0, (int) (length/Math.sqrt(2.0)), depth - 1);
    }

    // -------------------------------------------------------------------------
    // PROBLEM 59. Find all elements present more than n/3 times
    // Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space
    // https://www.geeksforgeeks.org/given-an-array-of-of-size-n-finds-all-the-elements-that-appear-more-than-nk-times/
    // https://stackoverflow.com/questions/14955634/number-which-appears-more-than-n-3-times-in-an-array
    // https://stackoverflow.com/questions/3001181/find-if-there-is-an-element-repeating-itself-n-k-times
    // -------------------------------------------------------------------------
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();

        int numA = 0;
        int numB = 0;
        int numACount = 0;
        int numBCount = 0;
        // [1,2,2,3,2,1,1,3]
        for (int i = 0; i < nums.length; i++) {
            // VERY IMP: First check "nums[i] == numA" and then check "numAcount == 0"
            // System.out.println("A: " + numA + "; NA: " + numACount + "; B: " + numB + "; NB: " + numBCount);
            if (nums[i] == numA) {
                numACount++;
            } else if (nums[i] == numB) {
                numBCount++;
            } else if (numACount == 0) {
                numA = nums[i];  
                numACount++;
            } else if (numBCount == 0) {
                numB = nums[i];    
                numBCount++;
            } else {
                numACount--;
                numBCount--;
            }
        }
        
        // System.out.println("A2: " + numA + "; B2: " + numB);

        // Check if each of the elements are present more than n/3 times
        numACount = 0;
        numBCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == numA) {
                numACount++;
            } else if (nums[i] == numB) {
                numBCount++;
            }
        }
        //System.out.println("NA: " + numACount + "; NB: " + numBCount);
        if (numACount > nums.length / 3) {
            result.add(numA);
        }
        if (numBCount > nums.length / 3) {
            result.add(numB);
        }
        return result;
    }

    public boolean backspaceCompare(String S, String T) {
        Stack<Character> firstString = new Stack<>();
        Stack<Character> secondString = new Stack<>();
        
        populateStack(S, firstString);
        populateStack(T, secondString);
        
        //System.out.println(firstString.toString());
        return true;
        // return compareStack(firstString, secondString);
    }
    
    private void populateStack(String s, Stack<Character> remainChars) {
         for (Character c : s.toCharArray()) {
            if (c == '#') {
                remainChars.pop();
            } else {
                remainChars.push(c);
            }
        }
    }

    // -------------------------------------------------------------------------
    // PROBLEM 59. Return all combinations of k numbers out of 1 to n
    // Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
    //
    // If n = 4 and k = 2, a solution is:
    // [[2,4],
    //  [3,4],
    //  [2,3],
    //  [1,2],
    //  [1,3],
    //  [1,4]]
    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------
    // Main Function
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("All Java Algo Problems");

        // Problem 1. Print all steps down a ladder
        {
            System.out.println("\nProblem 1. Print All Steps down a ladder");
            String str = "";
            StringBuilder sb = new StringBuilder();
            printLadderPaths(4, str);
            printLadderPaths(4, sb);
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
            int[][] twoD = {{1, 2, 3, 4},
                            {5, 6, 7, 8},
                            {9, 10, 11, 12},
                            {13, 14, 15, 16},
                            {17, 18, 19, 20}};
            printMatrixDiagonally(twoD);
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
            System.out.println(s.charAt(firstUniqueCharacter2(s)));
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
            //System.out.println(twoSumAllPairsMultiMap(arr2, 8));
        }

        // PROBLEM 14. Count sub arrays that will add to a sum
        {
            System.out.println("\nPROBLEM 14. Count sub arrays that will add to a sum");
            int[] arr = {4, 1, -3, 2,  6, -5, 4, 1 , -3, 2, 6 , -5};
            System.out.println(countSubarraySum(arr, 5));
        }

        // PROBLEM 15. Find Shortest Unsorted Continuous Subarray
        {
            System.out.println("\nPROBLEM 15. Find Shortest Unsorted Continuous Subarray");
            int[] arr = {2, 6, 4, 8, 10, 9, 15};
            System.out.println(findUnsortedSubarray(arr));
        }

        // PROBLEM 16. Finding greatest sum of elements of array which is divisible by a given number
        {
            System.out.println("\nPROBLEM 16. Finding greatest sum of elements of array which is divisible by a given number");
            int[] nums = {1, 6, 2, 9};
            int k = 4;
            System.out.println(greatestSumOfSubarrayDivisibleByK(nums, k));
        }

        // PROBLEM 17. Find Hamilton distance between two numbers
        {
            System.out.println("\nPROBLEM 17. Find Hamilton distance between two numbers");
            System.out.println(hammingDistance(1, 4));
        }

        // PROBLEM 18. Print Matrix Spirally
        {
            //System.out.println("\nPROBLEM 18. Print Matrix Spirally");
            int[][] twoD = {{1, 2, 3, 4},
                            {5, 6, 7, 8},
                            {9, 10, 11, 12},
                            {13, 14, 15, 16},
                            {17, 18, 19, 20}};
        }

        // PROBLEM 19. Given a string of 1s and 0s with '?' in between. A '?' can be either one or zero.
        {
            //System.out.println("\nPROBLEM 19. Given a string of 1s and 0s with '?' in between. A '?' can be either one or zero.");
            String s = "10?0?1";
        }

        // PROBLEM 20. Given an integer x, find ONE digit who is equal to its adjacent digits,
        {
            System.out.println("\nPROBLEM 20. Given an integer x, find ONE digit who is equal to its adjacent digits,");
            int num1 = 12553664;
            int num2 = 122334;
            int num3 = 433221;
            int num4 = 4333222;
            int num5 = 332211;
            System.out.println(largestNumByRemovingDup(num1));
            System.out.println(largestNumByRemovingDup(num2));
            System.out.println(largestNumByRemovingDup(num3));
            System.out.println(largestNumByRemovingDup(num4));
            System.out.println(largestNumByRemovingDup(num5));
        }

        // PROBLEM 21. Find Equilibrium Index of an array. Sum of left elements = Sum of Right elements
        {
            System.out.println("\nPROBLEM 21. Find Equilibrium Index of an array. Sum of left elements = Sum of Right elements");
            int[] nums = {-7, 1, 5, 2, -4, 3, 0};
            System.out.println(equilibriumIndex(nums));
        }

        // PROBLEM 22. Find if two rectangles overlap
        {
            System.out.println("\nPROBLEM 22. Find if two rectangles overlap");
            Point a1 = new Point(0, 10);
            Point a2 = new Point(10, 0);
            Point b1 = new Point(15, 5);
            Point b2 = new Point(25, 0);
            System.out.println(isRectanglesOverlap(a1, a2, b1, b2));
        }

        // PROBLEM 23. Find Largest Subarray with equal number of 0s and 1s
        {
            System.out.println("\nPROBLEM 23. Find Largest Subarray with equal number of 0s and 1s");
            int[] arr = {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0};
            printLargestSubArrayZeroOne(arr);
        }

        // PROBLEM 24. 3 number sum closest
        {
            System.out.println("\nPROBLEM 24. 3 number sum closest");
            int[] arr = {1, 4, 6, 8, 10, 45};
            printThreeNumSum(arr, 22);
            printThreeNumSumClosest(arr, 21);
        }

        // PROBLEM 25. 1 Missing and 1 Duplicate
        {
            System.out.println("\nPROBLEM 25. 1 Missing and 1 Duplicate");
            int[] nums = {1, 2, 2, 3};
            findMissingAndDuplicate(nums);
        }

        // PROBLEM 26. Maximize stock profit
        {
            System.out.println("\nPROBLEM 26. Maximize stock profit");
            int[] nums1 = {10, 7, 5, 8, 11, 9};
            int[] nums2 = {7, 1, 5, 3, 6, 4};
            int[] nums3 = {7, 6, 4, 3, 1};
            int[] nums4 = {100, 180, 260, 310, 40, 535, 695};
            System.out.println(maximizeProfit(nums1));
            System.out.println(maximizeProfit(nums2));
            System.out.println(maximizeProfit(nums3));
            System.out.println(maximizeProfit(nums4));
        }

        // PROBLEM 27. Round off seconds to minutes, hours, day, months, years
        {
            System.out.println("\nPROBLEM 27. Round off seconds to minutes, hours, day, months, years");
            roundOffSeconds(50391);
        }

        // PROBLEM 28. Product of all other numbers
        {
            System.out.println("\nPROBLEM 28. Product of all other numbers");
            int[] nums1 = {1,0,0,4};
            int[] nums2 = {1,2,3,4};
            int[] nums3 = {0,2,3,4};
            int[] nums4 = {1,2,0,4};
            int[] nums5 = {2,3,4,5};
            int[] nums6 = {2};
            int[] nums7 = {0};
            int[] nums8 = {2,0};
            int[] nums9 = {2,3};
            System.out.println(Arrays.toString(productExceptSelf(nums1)));
            System.out.println(Arrays.toString(productExceptSelfWithoutDiv(nums1)));
            System.out.println(Arrays.toString(productExceptSelf(nums2)));
            System.out.println(Arrays.toString(productExceptSelfWithoutDiv(nums2)));
            System.out.println(Arrays.toString(productExceptSelf(nums3)));
            System.out.println(Arrays.toString(productExceptSelfWithoutDiv(nums3)));
            System.out.println(Arrays.toString(productExceptSelf(nums4)));
            System.out.println(Arrays.toString(productExceptSelfWithoutDiv(nums4)));
            System.out.println(Arrays.toString(productExceptSelf(nums5)));
            System.out.println(Arrays.toString(productExceptSelfWithoutDiv(nums5)));
            System.out.println(Arrays.toString(productExceptSelf(nums6)));
            System.out.println(Arrays.toString(productExceptSelfWithoutDiv(nums6)));
            System.out.println(Arrays.toString(productExceptSelf(nums7)));
            System.out.println(Arrays.toString(productExceptSelfWithoutDiv(nums7)));
            System.out.println(Arrays.toString(productExceptSelf(nums8)));
            System.out.println(Arrays.toString(productExceptSelfWithoutDiv(nums8)));
            System.out.println(Arrays.toString(productExceptSelf(nums9)));
            System.out.println(Arrays.toString(productExceptSelfWithoutDiv(nums9)));
        }

        // PROBLEM 29. Highest product of three numbers
        {
            System.out.println("\nPROBLEM 29. Highest product of three numbers");
            int[] nums1 = {10, 3, 5, 6, 20};
            int[] nums2 = {-10, -3, -5, -6, -20};
            int[] nums3 = {1, -4, 3, -6, 7, 0};
            int[] nums4 = {-5, -1, 1, 2, 3};
            int[] nums5 = {5, 4, 5, 10, -6};
            System.out.println(highestProductOfThreeNumbers(nums1));
            System.out.println(highestProductOfThreeNumbers(nums2));
            System.out.println(highestProductOfThreeNumbers(nums3));
            System.out.println(highestProductOfThreeNumbers(nums4));
            System.out.println(highestProductOfThreeNumbers(nums5));
        }

        // PROBLEM 34. Sorting almost sorted array misplaced by k elements
        {
            System.out.println("\nPROBLEM 34. Sorting almost sorted array misplaced by k elements");
            int[] nums1 = {1, 4, 5, 2, 3, 7, 8, 6, 10, 9};
            System.out.println(Arrays.toString(sortKMessedArray(nums1, 2)));
        }

        // PROBLEM 35. Merge all overlapping intervals
        {
            System.out.println("\nPROBLEM 35. Merge all overlapping intervals");
            List<Interval> intervals = new ArrayList<>();
            intervals.add(new Interval(0, 1));
            intervals.add(new Interval(3, 5));
            intervals.add(new Interval(4, 8));
            intervals.add(new Interval(10, 12));
            intervals.add(new Interval(9, 10));
            mergeOverlappingIntervals(intervals);
        }

        // PROBLEM 36. Convert number to words
        {
            System.out.println("\nPROBLEM 36. Convert number to words");
            int num = 12345678;
            System.out.println(convertNumToWords(num));
        }

        // PROBLEM 37. Phone number permutations
        {
            System.out.println("\nPROBLEM 37. Phone number permutations");
            int[] nums1 = {0, 2, 1};
            int[] nums2 = {3, 2, 4};
            printPhoneNumberPermutations(nums1);
        }
        
        // PROBLEM 38. Reverse words in a sentence
        {
            System.out.println("\nPROBLEM 38. Reverse words in a sentence");
            String s = "  hello how    are  , you! !!  ";
            System.out.println(s);
            System.out.println(reverseWords(s));
        }

        // PROBLEM 39. Love rectangle - Overlapping rectanlge
        {
            System.out.println("\nPROBLEM 39. Love rectangle - Overlapping rectanlge");
            Rectangle A1 = new Rectangle(4, 4, 6, 4);
            Rectangle B1 = new Rectangle(6, 7, 6, 3);
            Rectangle B2 = new Rectangle(2, 7, 6, 3);
            Rectangle B3 = new Rectangle(2, 7, 12, 3);
            Rectangle B4 = new Rectangle(2, 2, 6, 4);
            Rectangle B5 = new Rectangle(5, 5, 2, 2);
            Rectangle r1 = findOverlappingRectangle(A1, B1);
            System.out.println("X: " +r1.getLeftX() + "; Y: " +r1.getBottomY() + "; W: " + r1.getWidth() + "; H: " + r1.getHeight());
            /*
            Rectangle r2 = findOverlappingRectangle(A1, B2);
            System.out.println("X: " +r2.getLeftX() + "; Y: " +r2.getBottomY() + "; W: " + r2.getWidth() + "; H: " + r2.getHeight());
            Rectangle r3 = findOverlappingRectangle(A1, B3);
            System.out.println("X: " +r3.getLeftX() + "; Y: " +r3.getBottomY() + "; W: " + r3.getWidth() + "; H: " + r3.getHeight());
            Rectangle r4 = findOverlappingRectangle(A1, B4);
            System.out.println("X: " +r4.getLeftX() + "; Y: " +r4.getBottomY() + "; W: " + r4.getWidth() + "; H: " + r4.getHeight());
            Rectangle r5 = findOverlappingRectangle(A1, B5);
            System.out.println("X: " +r5.getLeftX() + "; Y: " +r5.getBottomY() + "; W: " + r5.getWidth() + "; H: " + r5.getHeight());
            */
        }

        // PROBLEM 40. Binary to Decimal and Decimal to binary
        {
            System.out.println("\nPROBLEM 40. Binary to Decimal and Decimal to binary");
            System.out.println(binaryToDecimal("101"));
            System.out.println(decimalToBinary(7));
        }

        // PROBLEM 41. Roman to decimal and decimal to roman
        {
            System.out.println("\nPROBLEM 41. Roman to decimal and decimal to roman");
            System.out.println(decimalToRoman(14));
            System.out.println(decimalToRoman(2900));
            System.out.println(decimalToRoman(3549));
            System.out.println(romanToDecimal("MMMDXLIX"));
            System.out.println(romanToDecimal("MMCM"));
            System.out.println(romanToDecimal("XIV"));
        }

        // PROBLEM 42. Find smallest range containing elements from k lists
        {
            System.out.println("\nPROBLEM 42. Find smallest range containing elements from k lists");

            List<Integer> l1 = new ArrayList<>(Arrays.asList(4, 7, 9, 12, 15));
            List<Integer> l2 = new ArrayList<>(Arrays.asList(0, 8, 10, 14, 20));
            List<Integer> l3 = new ArrayList<>(Arrays.asList(6, 12, 16, 30, 50));
            List<List<Integer>> lists = new ArrayList<List<Integer>>();
            lists.add(l1);
            lists.add(l2);
            lists.add(l3);
            System.out.println(findSmallestRange(lists));
        }

        // PROBLEM 43. Rotate a matrix clockwise by 90degrees 
        {
            System.out.println("\nPROBLEM 43. Rotate a matrix clockwise by 90degrees ");
            int[][] matrix = {
                              { 5, 1, 9,11},
                              { 2, 4, 8,10},
                              {13, 3, 6, 7},
                              {15,14,12,16}
                             };
            printMatrix(matrix);
            rotateMatrix(matrix);
            printMatrix(matrix);
        }

        // PROBLEM 44. Top K Frequent Elements
        {
            System.out.println("\nPROBLEM 44. Top K Frequent Elements");
            int[] nums = {1,1,1,2,2,3};
            int k = 2;
            System.out.println(topKFrequent(nums, k));
        }

        // PROBLEM 45. Convert any base to any base
        {
            System.out.println("\nPROBLEM 45. Convert any base to any base");
            int num = 56789;
            int base = 62;
            String result1 = convertDecimalToAnyBase(num, base);
            int result2 = convertAnyBaseToDecimal(result1, base);
            //String str = "zzzzzz";
            //int result2 = convertAnyBaseToDecimal(str, base);
            System.out.println("Res1: " + result1 + ", Res2: " + result2);
        }

        // PROBLEM 46. ZigZag conversion
        {
            System.out.println("\nPROBLEM 46. ZigZag conversion");
            String s = "PAYPALISHIRING";
            System.out.println(zigzagConversion(s, 4));
        }

        // PROBLEM 47. Minimun length between numbers with max frequency
        {
            System.out.println("\nPROBLEM 47. Minimun length between numbers with max frequency");
            int[] nums1 = {1, 2, 3, 4, 2, 2, 3};
            int[] nums2 = {1, 2, 2, 3, 1};
            System.out.println(minLengthMaxFrequencyNumbers(nums2));
        }

        // PROBLEM 48. Pancake Sort - Sort just by using flip
        {
            System.out.println("\nPROBLEM 48. Pancake Sort - Sort just by using flip");
            int[] nums1 = {1,5,4,3,2};
            System.out.println(Arrays.toString(nums1));
            System.out.println(Arrays.toString(pancakeSort(nums1)));
        }

        // PROBLEM 49. Isomorphic Strings
        {
            System.out.println("\nPROBLEM 49. Isomorphic Strings");
        }

        // PROBLEM 50. Find k length combinations
        {
            System.out.println("\nPROBLEM 50. Find k length combinations");
        }

        // PROBLEM 51. Word compress
        {
            System.out.println("\nPROBLEM 51. Word compress");
        }

        // PROBLEM 52. Minimum flips to make 1s in the left and 0s in the right
        {
            System.out.println("\nPROBLEM 52. Minimum flips to make 1s in the left and 0s in the right");
            System.out.println(minFlipsToMovesOnesToLeftAndZerosToRight("10110000"));
        }

        // PROBLEM 53. Arrange list of numbers such that they form the largest number
        {
            System.out.println("\nPROBLEM 53. Arrange list of numbers such that they form the largest number");
        }

        // PROBLEM 54. Maximum subsets to sort the array
        {
            System.out.println("\nPROBLEM 54. Maximum subsets to sort the array");
        }

        // PROBLEM 55. Word ladder - Transform from one word to another
        {
            System.out.println("\nPROBLEM 55. Word ladder - Transform from one word to another");
        }

        // PROBLEM 56. Merge two sorted arrays into end of first array
        {
            System.out.println("\nPROBLEM 56. Merge two sorted arrays into end of first array");
            int[] arr1 = {1, 3, 5, 7, -1, -1, -1, -1};
            int[] arr2 = {2, 3, 6, 10};
            System.out.println(Arrays.toString(arr1));
            System.out.println(Arrays.toString(arr2));
            mergeSortedArrays(arr1, arr2);
            System.out.println(Arrays.toString(arr1));
        }

        // PROBLEM 57. Merge k sorted arrays
        {
            System.out.println("\nPROBLEM 57. Merge k sorted arrays");
            int[] arr1 = {1, 3, 5, 7};
            int[] arr2 = {2, 3, 6, 10, 16};
            int[] arr3 = {4, 7, 9, 10, 12, 15};
            int[] arr4 = {0, 2, 4};
            List<int[]> entries = new ArrayList<>();
            entries.add(arr1);
            entries.add(arr2);
            entries.add(arr3);
            entries.add(arr4);
            System.out.println(Arrays.toString(mergeKSortedArrays(entries)));
        }

        // PROBLEM 58. H-tree construction
        {
            System.out.println("\nPROBLEM 58. H-tree construction");
        }

        // PROBLEM 59. Find all elements present more than n/3 times
        {
            System.out.println("\nPROBLEM 59. Find all elements present more than n/3 times");
        }

    }
}

