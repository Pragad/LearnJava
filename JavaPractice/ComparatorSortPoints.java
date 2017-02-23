import java.io.*;
import java.util.*;

class CoOrdinates {
    int rowPos;
    int colPos;

    public CoOrdinates (int rowSize, int colSize) {
        rowPos = rowSize;
        colPos = colSize;
    }
}

class PairCoOrdinates {
    CoOrdinates source;
    CoOrdinates target;

    public PairCoOrdinates(CoOrdinates src, CoOrdinates tgt) {
        source = src;
        target = tgt;
    }
}

public class ComparatorSortPoints {
    public static void main(String[] args) {

        List<PairCoOrdinates> jumpingCoOrdinates = new ArrayList<>();

        CoOrdinates jumpSrc1 = new CoOrdinates(2, 1);
        CoOrdinates jumpDest1 = new CoOrdinates(0, 3);
        PairCoOrdinates jumpCoOrd1 = new PairCoOrdinates(jumpSrc1, jumpDest1);

        CoOrdinates jumpSrc2 = new CoOrdinates(1, 1);
        CoOrdinates jumpDest2 = new CoOrdinates(4, 2);
        PairCoOrdinates jumpCoOrd2 = new PairCoOrdinates(jumpSrc2, jumpDest2);

        CoOrdinates jumpSrc3 = new CoOrdinates(1, 3);
        CoOrdinates jumpDest3 = new CoOrdinates(1, 1);
        PairCoOrdinates jumpCoOrd3 = new PairCoOrdinates(jumpSrc3, jumpDest3);

        CoOrdinates jumpSrc4 = new CoOrdinates(4, 0);
        CoOrdinates jumpDest4 = new CoOrdinates(0, 3);
        PairCoOrdinates jumpCoOrd4 = new PairCoOrdinates(jumpSrc4, jumpDest4);

        jumpingCoOrdinates.add(jumpCoOrd1);
        jumpingCoOrdinates.add(jumpCoOrd2);
        jumpingCoOrdinates.add(jumpCoOrd3);
        jumpingCoOrdinates.add(jumpCoOrd4);

        for (PairCoOrdinates entry : jumpingCoOrdinates) {
            System.out.println(entry.source.rowPos);
            System.out.println(entry.source.colPos);
            System.out.println();
        }
    }
}
