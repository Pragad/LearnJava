//----------------------------------------------------------------------------------------
// Author: Anitha Kandasamy
//----------------------------------------------------------------------------------------
import java.io.*;
import java.util.*;

//----------------------------------------------------------------------------------------
// This class holds Co Ordinates in the form of X, Y
//----------------------------------------------------------------------------------------
class CoOrdinates {
    int rowPos;
    int colPos;

    public CoOrdinates (int rowSize, int colSize) {
        rowPos = rowSize;
        colPos = colSize;
    }
}

//----------------------------------------------------------------------------------------
// This class holds Source and Target Co Ordinates
// This is used to Jump from Source to Target
//----------------------------------------------------------------------------------------
class PairCoOrdinates {
    CoOrdinates source;
    CoOrdinates target;

    public PairCoOrdinates(CoOrdinates src, CoOrdinates tgt) {
        source = src;
        target = tgt;
    }
}

//----------------------------------------------------------------------------------------
// This is the Main Class that does the computation
//
// Assumptions
// 1. Given CoOrdinates are valid
//----------------------------------------------------------------------------------------
public class StepsToDestination {

    //----------------------------------------------------------------------------------------
    // This function generates a unique number from two given number
    // Approach 1:
    //      Cantor Function
    //      http://stackoverflow.com/questions/919612/mapping-two-integers-to-one-in-a-unique-and-deterministic-way
    // Approach 2:
    //      Use a long and append one int to lower half and another int to upper half of the long
    // Approach 3:
    //      Convert the given 'x', 'y' into a string of form "x,y" (including ',') and hash the string
    //----------------------------------------------------------------------------------------
    public static int CantorPairingFunction(int x, int y) {
        return (int)(0.5 * (x + y) * (x + y + 1) + y);
    }

    //----------------------------------------------------------------------------------------
    // This function computes the number of steps to reach the destination
    // Logic:
    //      - Use a Result matrix to store the number of possible ways to reach 'i', 'j'
    //      - Result[i][j] = Result[i-1][j] + Result[i][j-1]
    //      - If 'i', 'j' belongs to blocked list, then set the value in Result matrix to 0
    //      - If 'i', 'j' belongs to jumped list, then set value of jump coordiante appropriately
    //        Also remove 'i','j' from jumped list, so that we don't loop over and over again
    //        Add 'i', 'j' to blocked list, so that we skip it the next time we see
    //----------------------------------------------------------------------------------------
    public static int countStepsToDestination(CoOrdinates gridSize, List<CoOrdinates> blockedCoOrdinates, List<PairCoOrdinates> jumpingCoOrdinates) {
        
        // Considering 0, 0 as starting position
        CoOrdinates startPos = new CoOrdinates(0, 0);

        // A twoD matrix to store the results
        int[][] resTwoDMat = new int[gridSize.rowPos][gridSize.colPos];

        // This HashSet stores all blocked points so that they can be looked up at constant time
        HashSet<Integer> blockedCoOrdinatesSet = new HashSet<>();
        HashMap<Integer, CoOrdinates> jumpingCoOrdinatesMap = new HashMap<>();

        // Fill First row of result matrix with all 1s
        for (int i = 0; i < resTwoDMat.length; i++) {
            resTwoDMat[i][0] = 1;
        }

        // Fill First column of result matrix with all 1s
        for (int i = 0; i < resTwoDMat[0].length; i++) {
            resTwoDMat[0][i] = 1;
        }

        // Store Cantor mapping of all blocked points in a hash set
        // This way in O(1) you can tell if a point is a blocked point or not
        for (CoOrdinates blockPoint : blockedCoOrdinates) {
            blockedCoOrdinatesSet.add(CantorPairingFunction(blockPoint.rowPos, blockPoint.colPos));
        }

        // Check if Target is in the blockedCoOrdinates. Then we can reach the final state
        if (blockedCoOrdinatesSet.contains(CantorPairingFunction(gridSize.rowPos - 1, gridSize.colPos - 1))) {
            return -1;
        }

        // Add Jumping CoOrdinates to Map
        for (PairCoOrdinates jumpPoint : jumpingCoOrdinates) {
            jumpingCoOrdinatesMap.put(CantorPairingFunction(jumpPoint.source.rowPos, jumpPoint.source.colPos), jumpPoint.target);
        }

        // Loop over the resulting matrix and fill it
        for (int i = 1; i < resTwoDMat.length; i++) {
            for (int j = 1; j < resTwoDMat[0].length; j++) {

                // In case we hit 'i' or 'j' as 0, then put the correct value in result
                if (i == 0)
                {
                    resTwoDMat[i][j] = resTwoDMat[i][j - 1];
                    continue;
                } else if (j == 0) {
                    resTwoDMat[i][j] = resTwoDMat[i - 1][j];
                    continue;
                }

                // If the point is a blocked point, set its value to 0
                int cantor = CantorPairingFunction(i, j);
                if (blockedCoOrdinatesSet.contains(cantor)) {
                    resTwoDMat[i][j] = 0;
                } else if (jumpingCoOrdinatesMap.containsKey(cantor)) {
                    int jumpI = jumpingCoOrdinatesMap.get(cantor).rowPos;
                    int jumpJ = jumpingCoOrdinatesMap.get(cantor).colPos;

                    // Remove the jumping coordinate and add it to blocked list
                    jumpingCoOrdinatesMap.remove(cantor);
                    blockedCoOrdinatesSet.add(cantor);
                    resTwoDMat[jumpI][jumpJ] += (resTwoDMat[i-1][j] + resTwoDMat[i][j-1]);
                    resTwoDMat[i][j] = 0;

                    // Recompute again by going back to jump coordinate
                    i = jumpI;
                    j = jumpJ;
                } else {
                    resTwoDMat[i][j] = resTwoDMat[i-1][j] + resTwoDMat[i][j-1];
                }
            }
        }

        return resTwoDMat[resTwoDMat.length-1][resTwoDMat[0].length-1];
    }

    //----------------------------------------------------------------------------------------
    // Main Function
    //----------------------------------------------------------------------------------------
    public static void main (String[] args) {

        CoOrdinates gridSize1 = new CoOrdinates(1, 1);
        CoOrdinates gridSize2 = new CoOrdinates(2, 2);
        CoOrdinates gridSize3 = new CoOrdinates(3, 2);
        CoOrdinates gridSize4 = new CoOrdinates(4, 3);
        CoOrdinates gridSize5 = new CoOrdinates(5, 5);
        List<CoOrdinates> blockedCoOrdinates1 = new ArrayList<>();
        List<CoOrdinates> blockedCoOrdinates2 = new ArrayList<>();
        List<CoOrdinates> blockedCoOrdinates3 = new ArrayList<>();
        List<PairCoOrdinates> jumpingCoOrdinates = new ArrayList<>();

        // Test Cases
        System.out.println(countStepsToDestination(gridSize1, blockedCoOrdinates1, jumpingCoOrdinates));
        System.out.println(countStepsToDestination(gridSize2, blockedCoOrdinates1, jumpingCoOrdinates));
        System.out.println(countStepsToDestination(gridSize3, blockedCoOrdinates1, jumpingCoOrdinates));
        System.out.println(countStepsToDestination(gridSize4, blockedCoOrdinates1, jumpingCoOrdinates));
        System.out.println(countStepsToDestination(gridSize5, blockedCoOrdinates1, jumpingCoOrdinates));

        CoOrdinates blockPoint1 = new CoOrdinates(2, 1);
        CoOrdinates blockPoint2 = new CoOrdinates(4, 4);
        blockedCoOrdinates1.add(blockPoint1);
        System.out.println(countStepsToDestination(gridSize5, blockedCoOrdinates1, jumpingCoOrdinates));

        blockedCoOrdinates2.add(blockPoint2);
        System.out.println(countStepsToDestination(gridSize5, blockedCoOrdinates2, jumpingCoOrdinates));

        CoOrdinates jumpSrc1 = new CoOrdinates(2, 1);
        CoOrdinates jumpDest1 = new CoOrdinates(0, 3);
        PairCoOrdinates jumpCoOrd = new PairCoOrdinates(jumpSrc1, jumpDest1);
        jumpingCoOrdinates.add(jumpCoOrd);
        System.out.println(countStepsToDestination(gridSize5, blockedCoOrdinates3, jumpingCoOrdinates));

    }
}
