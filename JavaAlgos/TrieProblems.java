import java.util.*;

// https://stackoverflow.com/questions/43011837/trie-implementation-in-java
// https://www.programcreek.com/2014/05/leetcode-implement-trie-prefix-tree-java/
// -----------------------------------------------------------------------------
// Node class
// -----------------------------------------------------------------------------
class Node {
    Map<Character, Node> children;
    boolean isLeaf;

    Node() {
        children = new HashMap<>();
        isLeaf = false;
    }
}

// -----------------------------------------------------------------------------
// Trie class
// -----------------------------------------------------------------------------
class Trie {
    Node root;

    Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node tmpRoot = root;
        for (int i = 0; i < word.length(); i++) {
            if (tmpRoot.children.containsKey(word.charAt(i))) {
                tmpRoot = tmpRoot.children.get(word.charAt(i));
            } else {
                Node n = new Node();
                tmpRoot.children.put(word.charAt(i), n);
                tmpRoot = n;
            }
        }
        tmpRoot.isLeaf = true;
    }

    public boolean search(String word) {
        Node tmpRoot = root;
        for (char c : word.toCharArray()) {
            if (tmpRoot.children.containsKey(c)) {
                tmpRoot = tmpRoot.children.get(c);
            } else {
                return false;
            }
        }
        return tmpRoot.isLeaf;
    }

    public void printAllChildren(Node current) {
        if (current.isLeaf) {
            System.out.println();
        }
        for (Map.Entry<Character, Node>e : current.children.entrySet()) {
            System.out.print(e.getKey() + ", ");
            printAllChildren(e.getValue());
        }
    }

    // -------------------------------------------------------------------------
    // PROBLEM 2. Count number of words in a trie
    // https://www.geeksforgeeks.org/counting-number-words-trie/
    // -------------------------------------------------------------------------
    public int countWords(Node current) {
        int count = 0;
        if (current.isLeaf) {
            count += 1;
        }
        for (Map.Entry<Character, Node>e : current.children.entrySet()) {
            count += countWords(e.getValue());
        }
        return count;
    }

    // -------------------------------------------------------------------------
    // PROBLEM 3. Find duplicate row in matrix
    // https://www.geeksforgeeks.org/find-duplicate-rows-binary-matrix/
    // -------------------------------------------------------------------------
    public void findDuplicateRows(int[][] twoD) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < twoD.length; i++) {
            for (int j = 0; j < twoD[i].length; j++) {
                sb.append(String.valueOf(twoD[i][j]));
            }
            findDuplicateRows(sb.toString());
            sb.setLength(0);
        }
    }

    public void findDuplicateRows(String row) {
        Node current = root;
        boolean isNewNodeCreated = false;
        for (char c : row.toCharArray()) {
            if (current.children.containsKey(c)) {
                current = current.children.get(c);
            } else {
                Node n = new Node();
                current.children.put(c, n);
                current = n;
                isNewNodeCreated = true;
            }
        }
        if (!isNewNodeCreated) {
            // This is a duplicate row
            System.out.println(row);
        }
        current.isLeaf = true;
    }
}

// -----------------------------------------------------------------------------
// Main Trie class
// -----------------------------------------------------------------------------
public class TrieProblems {
    public static void main(String[] args) {
        // PROBLEM 1. Trie basics
        Trie trie = new Trie();
        {
            System.out.println("\nPROBLEM 1. Trie basics");

            /*
            trie.insert("there");
            trie.insert("answer");
            trie.insert("the");
            trie.insert("their");
            trie.insert("any");
            trie.insert("who");
            trie.insert("whom");
            trie.printAllChildren(trie.root);
            System.out.println("true: " + trie.search("there"));
            System.out.println("true: " + trie.search("the"));
            System.out.println("false: " + trie.search("thej"));
            System.out.println("true: " + trie.search("who"));
            System.out.println("true: " + trie.search("answer"));
            System.out.println("true: " + trie.search("any"));
            System.out.println("false: " + trie.search("anyone"));
            System.out.println("true: " + trie.search("their"));
            System.out.println("true: " + trie.search("there"));
            */
        }

        // PROBLEM 2. Count number of words in a trie
        {
            System.out.println("\nPROBLEM 2. Count number of words in a trie");
            System.out.println(trie.countWords(trie.root));
        }

        // PROBLEM 3. Find duplicate row in matrix
        {
            System.out.println("\nPROBLEM 3. Find duplicate row in matrix");
            int[][] twoD = {
                            {1, 1, 0, 1, 0, 1},
                            {0, 0, 1, 0, 0, 1},
                            {1, 1, 0, 1, 0, 1},
                            {1, 0, 1, 1, 0, 0},
                            {0, 0, 1, 0, 0, 1},
                            {0, 0, 1, 0, 0, 1},
                           };
            trie.findDuplicateRows(twoD);
        }
    }
}
