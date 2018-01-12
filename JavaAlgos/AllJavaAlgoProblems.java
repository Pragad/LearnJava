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

class Point {
    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
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
    // PROBLEM 24. 3 number sum closest
    // -------------------------------------------------------------------------
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
    }

}

