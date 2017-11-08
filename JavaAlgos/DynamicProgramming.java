import java.util.*;
import java.util.stream.*;

/*
 * PROBLEM 1. Minimum jump to reach end
 * int minJumpToReachEnd(int[] nums)
 *
 * PROBLEM 2: Print matrix diagonally
 * void printDiagonally(int[][] matrix)
 *
 * PROBLEM 3: Print matrix spirally
 * void printMatrixSpirally(int[][] twoDMat)
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
            if (i <= curPos + arr[curPos]) {
                int tmp  = minJumpToReachEndRecursive(nums, i, nums.length);
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

    public static void main(String [] args) {
    // PROBLEM 1. Minimum jump to reach end
        {
            System.out.println("\nPROBLEM 29. Highest product of three numbers");
            int[] nums1 = {2, 3, 1, 1, 2, 4, 2, 0, 1, 1};
            int[] nums2 = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
            int[] nums3 = {2, 3, 1, 1, 1};
            minJumpToReachEnd(nums1);
        }
    }
}

