/* 
 * Problem 0. Print Tree Inorder
 * void printTreeInorder(Node root)
 *
 * Problem 1. Compute the Number of nodes in the tree
 * int sizeOfTree(Node root)
 *
 * Problem 2. Compute the max depth in the tree
 * int maxDepth(Node root)
 *
 * Problem 2b. Compute the min depth in the tree
 * int minDepth(Node root)
 *
 * Problem 3. Compute the minimum value in BST
 * int minValue(Node root)
 *
 * Problem 4. Compute if there is path that matches the sum
 * boolean hasPathSum(Node root, int sum)
 *
 * Problem 5. Compute the Paths recursively
 * List<String> computePath(Node root)
 *
 * Problem 6. Compute the paths using ListOfList
 * void computePathList(Node root, List<Integer> path, List<List<Integer>> paths)
 *
 * Problem 7. Compute the mirror of a tree
 * void mirror(Node root)
 *
 * Problem 8. Compute the double of a tree
 * void doubleTree(Node root)
 *
 * Problem 9. Compute if two trees are same
 * boolean sameTree(Node a, Node b)
 *
 * Problem 10. Find if a tree is balanced
 * boolean isTreeBalanced(Node root)
 *
 * Problem 11. Find LCA in a binary tree
 * Node findLca(Node root, Node a, Node b)
 *
 * Problem 12. Find LCA in a binary search tree
 * findLcaBst(Node root, Node a, Node b) 
 *
 * Problem 13. Find Distance Between Siblings in a Binary Tree
 * int distanceBetweenKeys(Node root, int a, int b)
 *
 * Problem 14. Given a Binary Tree, find the maximum sum path from a leaf to root.
 * int maxSumLeafToRoot(Node root)
 *
 * Problem 15. Given a Binary Tree, find the maximum sum path from a leaf to leaf.
 * int maxSumLeafToLeaf(Node root)
 *
 * Problem 16. Given a Binary Tree, find the maximum sum path from a ANY NODE to ANY NODE
 * int maxSumNodeToNode(Node root)
 *
 * Problem 17. print-the-longest-leaf-to-leaf-path-in-a-binary-tree-and-its-length
 * uint32_t longPathLength(Node root)
 *
 * Problem 18. Print Tree in Level Order
 * void printTreeLevelOrder(Node root)
 *
 * Problem 19. Print Tree in Spiral Order
 * void printSpiralOrder(Node root)
 *
 * Problem 20. Print Tree in Vertical Order
 * void printVerticalOrder(Node root)
 *
 * Problem 21. Find the nearest element that is lesser than the given key
 * int nearestLesserElmt(Node root, int key, int& minElmt)
 *
 * Problem 22. Find largest element smaller than K in a BST
 * int findLargestNumSmallerThanKey(Node root, int elmt)
 *
 * Problem 23. Find next larger number than K in a BST
 * int findSmallestNumLargerThanKey(Node root, int elmt)
 *
 * Problem 23b. Find closest number to K in a BST
 * int closestNumToK(Node root, int elmnt)
 *
 * Problem 24. Nth largest element in a binary search tree
 * int NthLargestInBST(Node root, uint32_t n)
 *
 * Problem 25 In Order Successor in Binary Search Tree
 * Node InorderSuccessorWithParent(Node node)
 *
 * Problem 26. Iterative Inorder, Preorder, Postorder Traversal
 * void printTreeInorderIterative(Node root)
 *
 * Problem 27. Iterative Inorder, Preorder, Postorder Traversal
 * void printTreePreorderIterative(Node root)
 *
 * Problem 28. Iterative Inorder, Preorder, Postorder Traversal
 * void printTreePostorderIterative(Node root)
 *
 * Problem 29. Find Max Nodes in a Tree that will fall within the given range
 * int maxNodesWithinRange(int A, int B, Node T)
 *
 * Problem 30. Serialize a tree
 * string serialize(Node root)
 *
 * Problem 31. Deserialize a tree
 * TreeNode* deserialize(string data)
 *
 * Problem 32. Convert Tree into a Double Linked List
 * Node convertTreeToDoubleList(Node root)
 *
 * Problem 33. Convert Tree into a Circular Linked List
 * Node joinTwoCircularList(Node aList, Node bList)
 * Node convertTreeToCircularList(Node root)
 *
 * Problem 34. Count Leaf Nodes
 * int countLeafNodes(Node root)
 *
 * Problem 35. Find max width of tree
 */

import java.io.*;
import java.util.*;
import java.lang.StringBuilder;
import java.lang.*;

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }

    Node() {
        this.data = 0;
        left = null;
        right = null;
    }
}

public class BinaryTreeProblems {

    static Node buildTree() {
        Node root = new Node();
        root.data = 3;
        root.left = null;
        root.right = null;
        Node n2 = new Node();
        n2.data = 1;
        n2.left = null;
        n2.right = null;
        Node n3 = new Node();
        n3.data = 5;
        n3.left = null;
        n3.right = null;
        Node n4 = new Node();
        n4.data = 2;
        n4.left = null;
        n4.right = null;
        Node n5 = new Node();
        n5.data = 7;
        n5.left = null;
        n5.right = null;
        Node n6 = new Node();
        n6.data = 6;
        n6.left = null;
        n6.right = null;
        Node n7 = new Node();
        n7.data = 8;
        n7.left = null;
        n7.right = null;
        Node n8 = new Node();
        n8.data = 4;
        n8.left = null;
        n8.right = null;

        root.left = n2;
        root.right = n3;
        n2.right = n4;
        n3.left = n8;
        n3.right = n5;
        n5.left = n6;
        n5.right = n7;
        /*
                                3

                1                               5

        -               2               -               7

                                                    6       8
        */
        return root;
    }

    // -------------------------------------------------------------------------
    // Problem 0. Print Tree Inorder
    // -------------------------------------------------------------------------
    static void printTreeInorder(Node root) {
        if (root == null) {
            return;
        }
        printTreeInorder(root.left);
        System.out.println(root.data);
        printTreeInorder(root.right);
    }

    // -------------------------------------------------------------------------
    // Problem 1. Compute the Number of nodes in the tree
    // -------------------------------------------------------------------------
    static int sizeOfTree(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + sizeOfTree(root.left) + sizeOfTree(root.right);
    }

    // -------------------------------------------------------------------------
    // Problem 2. Compute the max depth in the tree
    // -------------------------------------------------------------------------
    static int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = 1 + maxDepth(root.left);
        int rightHeight = 1 + maxDepth(root.right);
        return Math.max(leftHeight, rightHeight);
    }

    // -------------------------------------------------------------------------
    // Problem 2b. Compute the min depth in the tree
    // -------------------------------------------------------------------------
    static int minDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = 1 + minDepth(root.left);
        int rightHeight = 1 + minDepth(root.right);
        if (leftHeight == 1 || rightHeight == 1) {
            return Math.max(leftHeight, rightHeight);
        }
        return Math.min(leftHeight, rightHeight);
    }

    // -------------------------------------------------------------------------
    // Problem 3. Compute the minimum value in BST
    // -------------------------------------------------------------------------
    static int minValue(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return root.data;
        }
        return minValue(root.left);
    }

    // -------------------------------------------------------------------------
    // Problem 4. Compute if there is path that matches the sum
    // -------------------------------------------------------------------------
    static boolean hasPathSum(Node root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.data == sum && root.left == null && root.right == null) {
            return true;
        }
        return hasPathSum(root.left, sum - root.data) || hasPathSum(root.right, sum - root.data);
    }

    // -------------------------------------------------------------------------
    // Problem 5. Compute the Paths recursively
    // -------------------------------------------------------------------------
    static void computePath(Node root, StringBuilder path, List<String> paths) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            path.append(root.data);
            paths.add(path.toString());
            path.setLength(path.length() - 1);
            return;
        }
        path.append(root.data);
        path.append("-");
        computePath(root.left, path, paths);
        computePath(root.right, path, paths);
        path.setLength(path.length() - 2);
    }

    static List<String> computePath(Node root) {
        List<String> paths = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        computePath(root, sb, paths);
        return paths;
    }

    // -------------------------------------------------------------------------
    // Problem 6. Compute the paths using ListOfList
    // -------------------------------------------------------------------------
    static void computePathList(Node root, List<Integer> path, List<List<Integer>> paths) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            path.add(root.data);

            // Clone the existing list and add it to list of list
            // Else removing an items from the list will remove from the listOfList
            List<Integer> cloneList = new ArrayList<>();
            for (Integer i : path) {
                cloneList.add(i);
            }
            paths.add(cloneList);
            path.remove(path.size() - 1);
            return;
        }
        path.add(root.data);
        computePathList(root.left, path, paths);
        computePathList(root.right, path, paths);
        path.remove(path.size() - 1);
    }

    static List<List<Integer>> computePathList(Node root) {
        List<List<Integer>> paths = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<>();
        computePathList(root, path, paths);
        return paths;
    }

    // -------------------------------------------------------------------------
    // Problem 7. Compute the mirror of a tree
    // -------------------------------------------------------------------------
    static void mirror(Node root) {
        if (root == null) {
            return;
        }
        Node tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        mirror(root.left);
        mirror(root.right);
    }

    // -------------------------------------------------------------------------
    // Problem 8. Compute the double of a tree
    // -------------------------------------------------------------------------
    static void doubleTree(Node root) {
        if (root == null) {
            return;
        }
        Node tmpLeft = root.left;
        Node tmpRight = root.right;
        Node tmp = new Node(root.data);
        tmp.left = root.left;

        root.left = tmp;
        root = tmpLeft;
        doubleTree(root);
        doubleTree(tmpRight);
    }

    // -------------------------------------------------------------------------
    // Problem 9. Compute if two trees are same
    // -------------------------------------------------------------------------
    static boolean areTreesSame(Node a, Node b) {
        if (a == null && b == null) {
            return true;
        } else if ((a == null && b != null) || (a != null && b == null)) {
            return false;
        }
        return a.data == b.data && areTreesSame(a.left, b.left) && areTreesSame(a.right, b.right);
    }

    // -------------------------------------------------------------------------
    // Problem 10. Find if a tree is balanced
    // https://stackoverflow.com/questions/742844/how-to-determine-if-binary-tree-is-balanced
    // -------------------------------------------------------------------------
    static int getHeight(Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        // If leftHeight is -1, then return -1 before computing the right height
        int rightHeight = getHeight(root.right);
        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return 1 + Math.max(leftHeight, rightHeight);
    }

    static boolean isTreeBalanced(Node root) {
        return(getHeight(root) != -1);
    }

    // -------------------------------------------------------------------------
    // Problem 11. Find LCA in a binary tree
    // This is with the assumption that both nodes are present. i.e. LCA of
    // null and node A will be node A.
    // -------------------------------------------------------------------------
    static Node findLca(Node root, Node a, Node b) {
        if (root == null) {
            return null;
        }
        if (root == a || root == b) {
            return root;
        }
        Node lh = findLca(root.left, a, b);
        Node rh = findLca(root.right, a, b);
        if (lh != null && rh != null) {
            return root;
        }
        return (lh != null) ? lh : rh;
    }

    // -------------------------------------------------------------------------
    // Problem 12. Find LCA in a binary search tree
    // Assumption: a and b are present in the BST
    // -------------------------------------------------------------------------
    static Node findLcaBst(Node root, Node a, Node b) {
        if (root == null) {
            return null;
        }
        if (root.data == a.data || root.data == b.data) {
            return root;
        }
        if ((root.data > a.data && root.data < b.data) ||
             root.data < a.data && root.data > b.data) {
            return root;
        }
        if (root.data > a.data && root.data > b.data) {
            return findLcaBst(root.left, a, b);
        } else if (root.data < a.data && root.data < b.data) {
            return findLcaBst(root.right, a, b);
        }
        return null;
    }

    // -------------------------------------------------------------------------
    // Problem 13. Find Distance Between Siblings in a Binary Tree
    // -------------------------------------------------------------------------
    private static int levelA = -1;
    private static int levelB = -1;
    private static int distance = -1;

    static Node findLcaWithLevel(Node root, Node a, Node b) {
        if (root == null) {
            return null;
        }
        if (root == a) {
            levelA = 1;
            return root;
        } else if (root == b) {
            levelB = 1;
            return root;
        }
        Node lh = findLcaWithLevel(root.left, a, b);
        Node rh = findLcaWithLevel(root.right, a, b);
        if (lh != null && rh != null) {
            distance = levelA + levelB;
            return root;
        }
        if (lh != null) {
            // Left child can become right child of another
            if (lh == a) {
                levelA++;
            } else if (lh == b) {
                levelB++;
            }
            return lh;
        } else {
            if (rh == a) {
                levelA++;
            } else if (rh == b) {
                levelB++;
            }
            return rh;
        }
    }

    private static int nodeLevel = 0;
    static int findLevel(Node root, Node a, int level) {
        if (root == null) {
            return 0;
        }
        if (root == a) {
            return level;
        }
        nodeLevel = findLevel(root.left, a, level + 1);
        return nodeLevel != 0 ? nodeLevel : findLevel(root.right, a, level + 1);
    }

    static int findDistanceBetweenNodes(Node root, Node a, Node b) {
        System.out.println(findLcaWithLevel(root, a, b).data);
        System.out.println("D: " + distance);
        System.out.println("A: " + levelA);
        System.out.println("B: " + levelB);
        if (distance != -1) {
            return distance;
        }
        // If 'a' is the ancestor then find distance from 'a' to 'b'
        int lvl = 0;
        if (root == a) {
            return findLevel(a, b, lvl);
        } else if (root == b) {
            return findLevel(a, b, lvl);
        }
        return -1;
    }

    // -------------------------------------------------------------------------
    // Problem 14. Given a Binary Tree, find the maximum sum path from a leaf to root.
    // -------------------------------------------------------------------------
    /*
    static int maxSumLeafToRoot(Node root, int curSum) {
        if (root == null) {
            // Can't return 0. 
            return Integer.MIN_VALUE;
        }
        if (root.left == null && root.right == null) {
            return curSum + root.data;
        }
        int leftSum = maxSumLeafToRoot(root.left, curSum);
        int rightSum = maxSumLeafToRoot(root.right, curSum);
        return Math.max(leftSum, rightSum) + root.data;
    }
    */

    static int maxSumLeafToRoot(Node root) {
        if (root == null) {
            // Can't return 0. 
            return Integer.MIN_VALUE;
        }
        if (root.left == null && root.right == null) {
            return root.data;
        }
        int leftSum = maxSumLeafToRoot(root.left);
        int rightSum = maxSumLeafToRoot(root.right);
        return Math.max(leftSum, rightSum) + root.data;
    }

    // -------------------------------------------------------------------------
    // Problem 15. Given a Binary Tree, find the maximum sum path from a leaf to root
    //
    // res : Max sum between two leaves
    // Return value: Max sum of left , right + root.data
    // -------------------------------------------------------------------------
    static class Result {
        int value;
    }

    static int maxSumLeafToLeafRec(Node root, Result res) {
        if (root == null) {
            // Can't return 0. 
            return Integer.MIN_VALUE;
        }
        if (root.left == null && root.right == null) {
            return root.data;
        }
        int leftSum = maxSumLeafToLeafRec(root.left, res);
        int rightSum = maxSumLeafToLeafRec(root.right, res);
        if (root.left != null && root.right != null) {
            res.value = Math.max(leftSum + rightSum + root.data, res.value);
        }
        return Math.max(leftSum, rightSum) + root.data;
    }

    static int maxSumLeafToLeaf(Node root) {
        if (root == null || root.left == null || root.right == null) {
            return Integer.MIN_VALUE;
        } else {
            Result res = new Result();
            maxSumLeafToLeafRec(root, res);
            return res.value;
        }
    }

    // -------------------------------------------------------------------------
    // Problem 18. Print Tree in Level Order
    // -------------------------------------------------------------------------
    static void printLevelOrderTraversal(Node root) {
        Queue<Node> nodeList = new ArrayDeque<>();
        int lvlCount = 0;
        int nextLevelCount = 0;
        if (root == null) {
            return;
        }
        nodeList.add(root);
        lvlCount++;
        while (!nodeList.isEmpty()) {
            Node tmp = nodeList.remove();
            System.out.print(tmp.data + ", ");
            lvlCount--;
            if (tmp.left != null) {
                nodeList.add(tmp.left);
                nextLevelCount++;
            }
            if (tmp.right != null) {
                nodeList.add(tmp.right);
                nextLevelCount++;
            }
            if (lvlCount == 0) {
                System.out.println();
                lvlCount = nextLevelCount;
                nextLevelCount = 0;
            }
        }
    }

    // -------------------------------------------------------------------------
    // Problem 19. Print Tree in Spiral Order
    // -------------------------------------------------------------------------
    static void printSpiralOrder(Node root) {
        Stack<Node> leftStack = new Stack<>();
        Stack<Node> rightStack = new Stack<>();
        if (root == null) {
            return;
        }
        rightStack.push(root);
        while (!leftStack.empty() || !rightStack.empty()) {
            while (!rightStack.empty()) {
                Node tmp = rightStack.pop();
                System.out.print(tmp.data + " ");
                if (tmp.right != null) {
                    leftStack.push(tmp.right);
                }
                if (tmp.left != null) {
                    leftStack.push(tmp.left);
                }
            }
            System.out.println();
            while (!leftStack.empty()) {
                Node tmp = leftStack.pop();
                System.out.print(tmp.data + " ");
                if (tmp.left != null) {
                    rightStack.push(tmp.left);
                }
                if (tmp.right != null) {
                    rightStack.push(tmp.right);
                }
            }
            System.out.println();
        }
    }

    // -------------------------------------------------------------------------
    // Problem 20. Print Tree in Vertical Order
    // -------------------------------------------------------------------------
    static void printVerticalOrderRec(Node root, Map<Integer, List<Node>> map, int count) {
        if (root == null) {
            return;
        }
        if (map.containsKey(count)) {
            map.get(count).add(root);
        } else {
            List<Node> l = new ArrayList<Node>();
            l.add(root);
            map.put(count, l);
        }
        printVerticalOrderRec(root.left, map, count - 1);
        printVerticalOrderRec(root.right, map, count + 1);
        
    }

    static void printVerticalOrder(Node root) {
        Map<Integer, List<Node>> map = new HashMap<>();
        printVerticalOrderRec(root, map, 0);
        List<Integer> sortedKeys = new ArrayList<>(map.keySet());
        Collections.sort(sortedKeys);

        for (int i : sortedKeys) {
            for (Node n : map.get(i)) {
                System.out.print(n.data + " ");
            }
            System.out.println();
        }
    }

    // -------------------------------------------------------------------------
    // Problem 22. Find largest element smaller than K in a BST
    // -------------------------------------------------------------------------
    static int findLargestNumSmallerThanKey(Node root, int k) {
        int result = Integer.MIN_VALUE;
        while (root != null) {
            if (root.data >= k) {
                root = root.left;
            } else {
                result = root.data;
                root = root.right;
            }
        }
        return result;
    }

    // -------------------------------------------------------------------------
    // Problem 24. Nth largest element in a binary search tree
    // https://www.geeksforgeeks.org/kth-largest-element-in-bst-when-modification-to-bst-is-not-allowed/
    // -------------------------------------------------------------------------
    static class Result24 {
        int nthLargest;
    }

    static int nthLargestInBST(Node root, int n) {
        Result24 res = new Result24();
        nthLargestInBST(root, n, res);
        return res.nthLargest;
    }

    static int nthLargestInBST(Node root, int n, Result24 res) {
        if (root == null) {
            return n;
        }
        n = nthLargestInBST(root.right, n, res);
        n--;
        if (n == 0) {
            res.nthLargest = root.data;
            return n;
        }
        return nthLargestInBST(root.left, n, res);
    }

    // -------------------------------------------------------------------------
    // Problem 30. Serialize a tree
    // https://leetcode.com/problems/serialize-and-deserialize-binary-tree/discuss/74253/
    // https://stackoverflow.com/questions/4611555/how-to-serialize-binary-tree
    // -------------------------------------------------------------------------
    static String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        serializeRec(root, sb);
        return sb.toString();
    }

    static void serializeRec(Node root, StringBuilder sb) {
        if (root == null) {
            sb.append("NULL")
              .append(", ");
            return;
        }
        sb.append(root.data).append(", ");
        serializeRec(root.left, sb);
        serializeRec(root.right, sb);
    }

    // -------------------------------------------------------------------------
    // Problem 31. Deserialize a tree
    // -------------------------------------------------------------------------
    static Node deserialize(String data) {
        Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(", ")));
        System.out.println(nodes);
        return deserializeRec(nodes);
    }

    static Node deserializeRec(Queue<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        String s = nodes.remove();
        if (s.equals("NULL")) {
            return null;
        }
        Node n = new Node(Integer.parseInt(s));
        n.left = deserializeRec(nodes);
        n.right = deserializeRec(nodes);
        return n;
    }

    // -------------------------------------------------------------------------
    // Problem 32. Convert Tree into a Double Linked List
    // https://www.geeksforgeeks.org/convert-a-given-binary-tree-to-doubly-linked-list-set-2/
    // -------------------------------------------------------------------------
    static Node convertTreeToDoubleList(Node root) {
        Node tmp = root;
        Node prev = null;
        fixPrevPointer(tmp, prev);
        fixNextPointer(root);

        // Print the list
        while (root != null) {
            System.out.print(root.data + " ");
            root = root.right;
        }
        System.out.println();
        return root;
    }

    static Node fixPrevPointer(Node root, Node prev) {
        if (root == null) {
            return null;
        }
        Node tmp = fixPrevPointer(root.left, prev);
        if (tmp != null) {
            root.left = tmp;
        } else {
            root.left = prev;
        }
        prev = root;
        fixPrevPointer(root.right, prev);
        return prev;
    }

    static Node fixNextPointer(Node root) {
        // Goto right most node
        if (root == null) {
            return null;
        }
        while (root.right != null) {
            root = root.right;
        }
        while (root.left != null) {
            Node prev = root.left;
            prev.right = root;
            root = prev;
        }
        return root;
    }

    // -------------------------------------------------------------------------
    // Problem 32b. Convert Tree into a Double Linked List
    // https://www.geeksforgeeks.org/convert-given-binary-tree-doubly-linked-list-set-3/
    // -------------------------------------------------------------------------
    /*
    static Node head = null;
    static Node convertTreeToDoubleList2(Node root) {
        Node prev = null;
        Node tmpHead = root;
        convertTreeToDoubleListRec2(tmpHead, prev);

        // This is to avoid using the above static head
        while (tmpHead.left != null) {
            tmpHead = tmpHead.left;
        }

        // Print the list to make sure double linked list is correct
        while (tmpHead != null) {
            System.out.print(tmpHead.data + " ");
            tmpHead = tmpHead.right;
        }
        System.out.println();

        return tmpHead;
    }
    
    static Node convertTreeToDoubleListRec2(Node root, Node prev) {
        if (root == null) {
            return null;
        }

        Node tmp = convertTreeToDoubleListRec2(root.left, prev);
        // Fix the prev pointer
        prev = tmp != null ? tmp : prev;
        // If this is the least node, the set head to this node
        if (prev == null) {
            // This will be the head of the linked list. But this is a static variable
            // Alternate way to get head will be to get the root and the traverse left to find the head
            head = root;
        } else {
            // Fix the next pointer
            prev.right = root;
        }

        root.left = prev;
        prev = root;
        tmp = convertTreeToDoubleListRec2(root.right, prev);
        prev = tmp != null ? tmp : prev;
        return prev;
    }
    */

    static class Result2 {
        Node head;
    }

    static Node convertTreeToDoubleList2(Node root) {
        Node prev = null;
        Node tmpHead = root;
        Result2 res2 = new Result2();
        convertTreeToDoubleListRec2(tmpHead, prev, res2);

        // This is to avoid using the above static head
        /*
        while (tmpHead.left != null) {
            tmpHead = tmpHead.left;
        }

        // Print the list to make sure double linked list is correct
        while (tmpHead != null) {
            System.out.print(tmpHead.data + " ");
            tmpHead = tmpHead.right;
        }
        System.out.println();
        */

        while (res2.head != null) {
            System.out.print(res2.head.data + " ");
            res2.head = res2.head.right;
        }
        System.out.println();
        return res2.head;
    }
    
    static Node convertTreeToDoubleListRec2(Node root, Node prev, Result2 res2) {
        if (root == null) {
            return null;
        }

        Node tmp = convertTreeToDoubleListRec2(root.left, prev, res2);
        // Fix the prev pointer
        prev = tmp != null ? tmp : prev;
        // If this is the left most node, set head to this node
        if (prev == null) {
            // This will be the head of the linked list. But this is a static variable
            // Alternate way to get head will be to get the root and the traverse left to find the head
            res2.head = root;
        } else {
            // Fix the next pointer
            prev.right = root;
        }

        root.left = prev;
        prev = root;
        tmp = convertTreeToDoubleListRec2(root.right, prev, res2);
        prev = tmp != null ? tmp : prev;
        return prev;
    }

    // -------------------------------------------------------------------------
    // Problem 35. Find max width of tree
    // https://www.geeksforgeeks.org/maximum-width-of-a-binary-tree/
    // -------------------------------------------------------------------------
    static int maxWidth(Node root) {
        if (root == null) {
            return 0;
        }
        Queue<Node> nodeQueue = new LinkedList<>();
        int currentLevelCount = 0;
        int nextLevelCount = 0;

        nodeQueue.add(root);
        currentLevelCount++;
        int maxWidth = currentLevelCount;

        while (!nodeQueue.isEmpty()) {
            Node curNode = nodeQueue.remove();
            currentLevelCount--;
            if (curNode.left != null) {
                nodeQueue.add(curNode.left);
                nextLevelCount++;
            }
            if (curNode.right != null) {
                nodeQueue.add(curNode.right);
                nextLevelCount++;
            }
            if (currentLevelCount == 0) {
                maxWidth = Math.max(maxWidth, nextLevelCount);                
                currentLevelCount = nextLevelCount;
                nextLevelCount = 0;
            }
        }
        return maxWidth;
    }

    // -------------------------------------------------------------------------
    // Problem 36. Check if Subtree of Another Tree
    // -------------------------------------------------------------------------
    static boolean compareIfSame(Node s, Node t) {
        if (s == null && t == null) {
            return true;
        }
        if ((s == null && t != null) ||
            (s != null && t == null) ||
            (s.data != t.data)) {
            return false;
        }
        return compareIfSame(s.left, t.left) && compareIfSame(s.right, t.right);
    }
    
    static boolean isSubtree(Node s, Node t) {
        if (s == null) {
            return false;
        }
        return compareIfSame(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    // -------------------------------------------------------------------------
    // Problem 37. Convert Sorted Array to Binary Search Tree
    // https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
    // -------------------------------------------------------------------------
    static Node sortedArrayToBST(int[] nums) {
        if (nums.length <= 0) {
            return null;
        }
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }
    
    static Node sortedArrayToBST(int[] nums, int stIdx, int endIdx) {
        if (stIdx > endIdx) {
            return null;
        }
        
        int midIdx = stIdx + (endIdx - stIdx) / 2;
        Node root = new Node(nums[midIdx]);
        root.left = sortedArrayToBST(nums, stIdx, midIdx - 1);
        root.right = sortedArrayToBST(nums, midIdx + 1, endIdx);
        return root;
    }

    // -------------------------------------------------------------------------
    // Main Function
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        Node root = buildTree();

        // Problem 0. Print Tree Inorder
        {
            printTreeInorder(root);
        }

        // Problem 1. Compute the Number of nodes in the tree
        {
            System.out.println("Problem 1. Size of Binary Tree");
            System.out.println(sizeOfTree(root));
        }

        // Problem 2. Compute the max depth in the tree
        {
            System.out.println("Problem 2. Compute the max depth in the tree");
            System.out.println(maxDepth(root));
        }

        // Problem 2b. Compute the min depth in the tree
        {
            System.out.println("Problem 2b. Compute the min depth in the tree");
            System.out.println(minDepth(root));
        }

        // Problem 3. Compute the minimum value in BST
        {
            System.out.println("Problem 3. Compute the minimum value in BST");
            System.out.println(minValue(root));
        }

        // Problem 4. Compute if there is path that matches the sum
        {
            System.out.println("Problem 4. Compute if there is path that matches the sum");
            System.out.println(hasPathSum(root, 4));
            System.out.println(hasPathSum(root, 8));
            System.out.println(hasPathSum(root, 6));
            System.out.println(hasPathSum(root, 21));
            System.out.println(hasPathSum(root, 23));
            System.out.println(hasPathSum(root, 15));
            System.out.println(hasPathSum(root, 14));
            System.out.println(hasPathSum(root, 3));
        }

        // Problem 5. Compute the Paths recursively
        {
            System.out.println("Problem 5. Compute the Paths recursively");
        }

        // Problem 6. Compute the paths using ListOfList
        {
            System.out.println("Problem 6. Compute the paths using ListOfList");
            System.out.println(computePathList(root));
        }

        // Problem 7. Compute the mirror of a tree
        {
            System.out.println("Problem 7. Compute the mirror of a tree");
            //mirror(root);
            printTreeInorder(root);            
        }

        // Problem 8. Compute the double of a tree
        {
            System.out.println("Problem 8. Compute the double of a tree");
            //doubleTree(root);
            printTreeInorder(root);            
        }

        // Problem 9. Compute if two trees are same
        {
            System.out.println("Problem 9. Compute if two trees are same");
            Node root1 = buildTree();
            System.out.println(areTreesSame(root, root1));
        }

        // Problem 10. Find if a tree is balanced
        {
            System.out.println("Problem 10. Find if a tree is balanced");
            System.out.println(isTreeBalanced(root));
        }

        // Problem 11. Find LCA in a binary tree
        {
            System.out.println("Problem 11. Find LCA in a binary tree");
            System.out.println(root.left.right.data + " : " + root.right.right.right.data);
            System.out.println(findLca(root, root.left.right, root.right.right.right).data);
        }

        // Problem 12. Find LCA in a binary search tree
        {
            System.out.println("Problem 12. Find LCA in a binary search tree");
            System.out.println(root.left.right.data + " : " + root.right.right.right.data);
            System.out.println(findLcaBst(root, root.left.right, root.right.right.right).data);
        }

        // Problem 13. Find Distance Between Siblings in a Binary Tree
        {
            System.out.println("Problem 13. Find Distance Between Siblings in a Binary Tree");
            /*
            System.out.println(root.left.right.data + " : " + root.right.right.right.data);
            findDistanceBetweenNodes(root, root.left.right, root.right.right.right);
            */
            System.out.println(root.right.right.left.data + " : " + root.right.right.right.data);
            findDistanceBetweenNodes(root, root.right.right.left, root.right.right.right);
        }

        // Problem 14. Given a Binary Tree, find the maximum sum path from a leaf to root
        {
            System.out.println("Problem 14. Given a Binary Tree, find the maximum sum path from a leaf to root");
            System.out.println(maxSumLeafToRoot(root));
        }

        // Problem 15. Given a Binary Tree, find the maximum sum path from a leaf to root
        {
            System.out.println("Problem 15. Given a Binary Tree, find the maximum sum path from a leaf to leaf");
            System.out.println(maxSumLeafToLeaf(root));
        }

        // Problem 18. Print Tree in Level Order
        {
            System.out.println("Problem 18. Print Tree in Level Order");
            printLevelOrderTraversal(root);
        }

        // Problem 19. Print Tree in Spiral Order
        {
            System.out.println("Problem 19. Print Tree in Spiral Order");
            printSpiralOrder(root);
        }

        // Problem 20. Print Tree in Vertical Order
        {
            System.out.println("Problem 20. Print Tree in Vertical Order");
            printVerticalOrder(root);
        }

        // Problem 22. Find largest element smaller than K in a BST
        {
            System.out.println("Problem 22. Find largest element smaller than K in a BST");
            System.out.println(findLargestNumSmallerThanKey(root, 6));
        }

        // Problem 24. Nth largest element in a binary search tree
        {
            System.out.println("Problem 24. Nth largest element in a binary search tree");
            System.out.println(nthLargestInBST(root, 3));
        }

        // Problem 30. Serialize a tree
        {
            System.out.println("Problem 30. Serialize a tree");
            System.out.println(serialize(root));
        }

        // Problem 31. Deserialize a tree
        {
            System.out.println("Problem 31. Deserialize a tree");
            printLevelOrderTraversal(deserialize(serialize(root)));
        }

        // Problem 32. Convert Tree into a Double Linked List
        {
            System.out.println("Problem 32. Convert Tree into a Double Linked List");
            //convertTreeToDoubleList(root);
            //printLevelOrderTraversal(root);
            //convertTreeToDoubleList2(root);
        }

        // Problem 35. Find max width of tree
        {
            System.out.println("Problem 35. Find max width of tree");
            System.out.println(maxWidth(root));
        }

        // Problem 36. Select a node at random
        // https://stackoverflow.com/questions/32011232/randomly-select-a-node-from-a-binary-tree
        {
            System.out.println("Problem 36. Select a node at random");
            //System.out.println(maxWidth(root));
        }
    }
}

