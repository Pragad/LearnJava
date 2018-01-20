import java.util.*;
import java.util.stream.*;

/*
 * PROBLEM 1. Minimum jump to reach end
 * int minJumpToReachEnd(int[] nums)
 *
 * Problem 2. Ladder Problem
 * void printPathsUpLadder(int n)
 *
 * Problem 3. Longest Increasing Subsequence
 * uint32_t longestIncreasingSubSequenceVec(vector<int>& nums)
 * uint32_t longestIncreasingSubseq(vector<int> nums)
 *
 * Problem 4. Longest Common Subsequence
 * uint32_t longestCommonSubSequenceRec(string str1, string str2)
 * uint32_t longestCommonSubSequenceDP(string str1, string str2)
 *
 * PROBLEM 5. Coin change problem
 * uint32_t minCoinChangeRec(uint32_t coins[], uint32_t num, uint32_t val)
 * uint32_t minCoinChangeDP(uint32_t coins[], uint32_t num, uint32_t val)
 *
 */ 

public class DynamicProgramming  {

    // -------------------------------------------------------------------------
    // PROBLEM 1. Minimum jump to reach end
    // http://www.geeksforgeeks.org/minimum-number-jumps-reach-endset-2on-solution/
    // O(n^2) solution
    // -------------------------------------------------------------------------
    static int minJumpToReachEnd(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int[] minJumps = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] >= i) {
                    minJumps[i] = minJumps[j] + 1;
                    break;
                }
            }
        }
        return minJumps[nums.length - 1];
    }

    // -------------------------------------------------------------------------
    // Recursive solution
    // -------------------------------------------------------------------------
    static int minJumpToReachEndRecursive(int[] nums, int curPos) {
        if (curPos == nums.length - 1) {
            return 0;
        }
        if (nums[curPos] == 0) {
            return Integer.MAX_VALUE;
        }
        int minJumps = Integer.MAX_VALUE;
        for (int i = curPos + 1; i < nums.length; i++) {
            if (i <= curPos + nums[curPos]) {
                int tmp  = minJumpToReachEndRecursive(nums, i);
                if (tmp != Integer.MAX_VALUE) {
                    minJumps = Math.min(minJumps, tmp + 1);
                }
            }
        }
        return minJumps;
    }

    // -------------------------------------------------------------------------
    // http://www.geeksforgeeks.org/minimum-number-jumps-reach-endset-2on-solution/
    // O(n) solution
    // 2, 3, 1, 1, 1
    // -------------------------------------------------------------------------
    static int minJumpToReachEndOpt(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int maxPos = nums[0];
        int numSteps = nums[0];
        int minJumps = 1; // This will start from 1
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1) {
                return minJumps;
            }
            maxPos = Math.max(maxPos, i + nums[i]);
            numSteps--;
            if (numSteps == 0) {
                // numSteps will be maxPos - i. So if maxPos == i, then we will have 0 steps
                // So if i > or >= maxPos we can't reach
                if (i >= maxPos) {
                    // We don't have any more steps to take and we reached i that is more than maxPos
                    // So we can never reach this i
                    return -1;
                }
                // Say we are at index 2, numSteps = 0; maxPos = 4; i = 2
                // So we have 4 - 2 = 2 more steps left to be consumed from 3
                numSteps = maxPos - i;
                minJumps++;
            }
        }
        return -1;
    }

    // -------------------------------------------------------------------------
    // Problem 2. Ladder Problem
    // -------------------------------------------------------------------------
    static void printPathsUpLadderRec(int n, List<Integer> paths) {
        if (n == 0) {
            System.out.println(paths);
            return;
        }
        if (n >= 1) {
            paths.add(1);
            printPathsUpLadderRec(n - 1, paths);
            paths.remove(paths.size() - 1);
        }
        if (n >= 2) {
            // Remove the previously added 1
            paths.add(2);
            printPathsUpLadderRec(n - 2, paths);
            paths.remove(paths.size() - 1);
        }
    }

    static void printPathsUpLadder(int n) {
        List<Integer> paths = new ArrayList<>();
        printPathsUpLadderRec(n, paths);
    }

    // ---------------------------------------------------------------------------------------
    // Problem 3: Longest Increasing Subsequence
    // http://stackoverflow.com/questions/2631726/how-to-determine-the-longest-increasing-subsequence-using-dynamic-programming
    //
    //      Let arr[0..n-1] be the input array and
    //      L(i) be the length of the LIS till index i such that arr[i] is part of LIS and arr[i] is the last element in LIS, then L(i) can be recursively written as.
    //      
    //      L(i) = { 1 + Max ( L(j) ) }
    //      where j < i and arr[j] < arr[i] and if there is no such j then L(i) = 1
    //
    //      Complexity Recursion: O(n!)
    //      Complexity DP: O(n log(n))
    //
    //               lis(4)
    //        /        |       \
    //      lis(3)    lis(2)   lis(1)
    //     /   \        /
    //   lis(2) lis(1) lis(1)
    //   /
    // lis(1)
    // ---------------------------------------------------------------------------------------
    // VERY IMP: Return Greater or Equal
    static int findFirstLargerElement(List<Integer> al, int k) {
        int low = 0;
        int high = al.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (al.get(mid) >= k) {
                if (mid == 0 || al.get(mid) == k) {
                    return mid;
                }
                if ((mid - 1 >=0) && (al.get(mid - 1) < k)) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }
        return al.size();
    }

    static int longestIncSubSeqDP(int[] arr) {
        List<Integer> l = new ArrayList<>();
        if (arr.length <= 1) {
            return arr.length;
        }
        l.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (l.get(0)  >= arr[i]) {
                l.set(0, arr[i]);
            } else if (l.get(l.size() - 1) < arr[i]) {
                l.add(arr[i]);
            } else {
                // Find first element larger than arr[i]
                int t = findFirstLargerElement(l, arr[i]);
                if (t >= l.size()) {
                    l.add(arr[i]);
                } else {
                    l.set(t, arr[i]);
                }
            }
        }
        return l.size();
    }

    // ---------------------------------------------------------------------------------------
    // Problem 4: Longest Common Subsequence
    //            Using Recursion
    //            Complexity: 2^n
    // ---------------------------------------------------------------------------------------
    static int longestCommonSubSequenceRec(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        if (s1.charAt(s1.length() - 1) == s2.charAt(s2.length() - 1)) {
            return 1 + longestCommonSubSequenceRec(s1.substring(0, s1.length() - 1), s2.substring(0, s2.length() - 1));
        } else {
            return Math.max(longestCommonSubSequenceRec(s1.substring(0, s1.length()), s2.substring(0, s2.length() - 1)), longestCommonSubSequenceRec(s1.substring(0, s1.length() - 1), s2.substring(0, s2.length())));
        }
    }

    // ---------------------------------------------------------------------------------------
    // Problem 4: Longest Common Subsequence
    //            Using DP
    //
    // Logic: 
    //      1. If str[i] == str[j] then
    //         DP[i][j] = 1 + DP[i-1][j-1]
    //      2. Else
    //         DP[i][j] = max(DP[i-1][j], DP[i][j-1])
    //
    //         X M J Y A U Z
    //       0 0 0 0 0 0 0 0
    //     M 0 0 1 1 1 1 1 1
    //     Z 0 0 1 1 1 1 1 2
    //     J 0 0 1 2 2 2 2 2
    //     A 0 0 1 2 2 3 3 3
    //     W 0 0 1 2 2 3 3 3
    //     X 0 1 1 2 2 3 3 3
    //     U 0 1 1 2 2 3 4 4
    // ---------------------------------------------------------------------------------------
        static int longestCommonSubSequenceDP(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return 0;
        }

        int[][] twoD = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    twoD[i][j] = 1 + twoD[i-1][j-1];
                } else {
                    twoD[i][j] = Math.max(twoD[i-1][j], twoD[i][j-1]);
                }
            }
        }

        return twoD[s1.length()][s2.length()];
    }

    // ---------------------------------------------------------------------------------------
    // PROBLEM 5. Coin change problem
    // http://algorithms.tutorialhorizon.com/dynamic-programming-coin-change-problem/
    //            coins[] = {25, 10, 5}, V = 30
    //            Ans: 2
    // EVERY COIN EITHER GETS INCLUDED or EXCLUDED
    // Time Complexity 2^n
    // we have infinite supply of each of C = { C1, C2, .. , Cm} valued coins
    // ---------------------------------------------------------------------------------------
    static int minCoinChangeRec(int[] coins, int val) {
        if (val == 0) {
            return 0;
        }
        int minVal = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= val) {
                int minCoins = minCoinChangeRec(coins, val - coins[i]);
                if (minCoins != Integer.MAX_VALUE) {
                    minVal = Math.min(1 + minCoins, minVal);
                }
            }
        }
        return minVal;
    }

    static int minCoinChangeDP(int[] coins, int val) {
        int[] dp = new int[val + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= val; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    // Can't use the below line. It is different from IF block
                    //dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                    if (dp[i - coins[j]] != Integer.MAX_VALUE && 1 + dp[i - coins[j]] < dp[i]) {
                        dp[i] = 1 + dp[i - coins[j]];
                    }
                }
            }
        }
        return dp[val];
    }

    // -------------------------------------------------------------------------
    // PROBLEM 6. All possible ways to make coin denominations
    // -------------------------------------------------------------------------
    static class Result {
        int val;
    }

    static void getCoinChangeWaysRec(long n, long[] c, Result res, List<Long> l, List<List<Long>> ll) {
        if (n == 0) {
            for (List list1 : ll) {
                // Can't do containsAll as it avoids duplicates
                // i.e. l1 = [2, 2]; l2 = [1, 2, 1]
                // l1.containsAll(l2) will return true
                Collections.sort(l);
                if (list1.equals(l)) {
                    return;
                }
            }
            // Now the list is not present
            Collections.sort(l);
            ll.add(new ArrayList<Long>(l));
            res.val++;
            return;
        }
        for (int i = 0; i < c.length; i++) {
            if (n >= c[i]) {
                l.add(c[i]);
                getCoinChangeWaysRec(n - c[i], c, res, l, ll);
                l.remove(c[i]);
            }
        }
    }

    static long getCoinChangeWays(long n, long[] c) {
        Result res = new Result();
        List<List<Long>> ll = new ArrayList<List<Long>>();
        List<Long> l = new ArrayList<>();
        getCoinChangeWaysRec(n, c, res, l, ll);
        return res.val;
    }

    static long getCoinChangeWaysDP(long n, long[] c) {
        long[] dp = new long[(int)n + (int)1];
        dp[0] = 1;
        for (int i = 0; i < c.length; i++) {
            for (int j = 1; j <= n; j++) {
                if (j >= c[i]) {
                    dp[j] += dp[(int)j - (int)c[i]];
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[dp.length - 1];
    }

    // -------------------------------------------------------------------------
    // Main Function
    // -------------------------------------------------------------------------
    public static void main(String [] args) {
        // PROBLEM 1. Minimum jump to reach end
        {
            System.out.println("\nPROBLEM 1. Minimum jump to reach end");
            int[] nums1 = {2, 3, 1, 1, 2, 4, 2, 0, 1, 1};
            int[] nums2 = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
            int[] nums3 = {2, 3, 1, 1, 1};
            minJumpToReachEnd(nums1);
        }

        // Problem 2. Ladder Problem
        {
            System.out.println("\nProblem 2. Ladder Problem");
            printPathsUpLadder(6);
        }

        // Problem 3. Longest Increasing Subsequence
        {
            System.out.println("\nProblem 3. Longest Increasing Subsequence");
            int[] arr = {10,9,2,5,3,7,101,18};
            int[] arr2 = {3,5,6,2,5,4,19,5,6,7,12};
            List<Integer> al = Arrays.asList(2, 5, 7, 10, 11);
            /*
            System.out.println(al.get(findFirstLargerElement(al, 1)));
            System.out.println(al.get(findFirstLargerElement(al, 2)));
            System.out.println(al.get(findFirstLargerElement(al, 5)));
            System.out.println(al.get(findFirstLargerElement(al, 7)));
            System.out.println(al.get(findFirstLargerElement(al, 10)));
            */
            //int[] arr = {10,9,2,5,3,7,101,18};
            System.out.println(longestIncSubSeqDP(arr));
        }

        // Problem 4. Longest Common Subsequence
        {
            System.out.println("\nProblem 4. Longest Common Subsequence");
            String str1 = "ABCDGH";
            String str2 = "AEDFHR";
            String str3 = "MZJAWXU";
            String str4 = "XMJYAUZ";
            System.out.println(longestCommonSubSequenceRec(str1, str2));
            System.out.println(longestCommonSubSequenceDP(str1, str2));
            System.out.println(longestCommonSubSequenceRec(str3, str4));
            System.out.println(longestCommonSubSequenceDP(str3, str4));
        }

        // Problem 5: Coin Change Problem
        {
            System.out.println("\nProblem 5: Coin Change Problem");
            int[] coins = {1, 8, 6, 5};
            System.out.println(minCoinChangeRec(coins, 12));
            System.out.println(minCoinChangeDP(coins, 12));
        }

        // PROBLEM 6. All possible ways to make coin denominations
        {
            System.out.println("\nPROBLEM 36. All possible ways to make coin denominations");
            long[] c = {1, 2, 3};
            long n = 4;
            System.out.println(getCoinChangeWays(n, c));
            System.out.println(getCoinChangeWaysDP(n, c));
        }
    }
}

