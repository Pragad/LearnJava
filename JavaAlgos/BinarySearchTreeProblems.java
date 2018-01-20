import java.util.*;
// Amazing logic
// https://leetcode.com/problems/binary-search-tree-iterator/discuss/52525
/*
public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<TreeNode>();
    
    public BSTIterator(TreeNode root) {
        pushAll(root);
    }

    // @return whether we have a next smallest number
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    //* @return the next smallest number
    public int next() {
        TreeNode tmpNode = stack.pop();
        pushAll(tmpNode.right);
        return tmpNode.val;
    }
    
    private void pushAll(TreeNode node) {
        for (; node != null; stack.push(node), node = node.left);
    }
}
*/

// -----------------------------------------------------------------------------
// Class Node
// -----------------------------------------------------------------------------
class Node {
    private int data;
    private Node right;
    private Node left;

    public Node(int data) {
        this.data = data;
        right = null;
        left = null;
    }

    public int getData() {
        return data;
    }

    public Node getRight() {
        return right;
    }

    public Node getLeft() {
        return left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }
}

// -----------------------------------------------------------------------------
// Class BST Iterator
// -----------------------------------------------------------------------------
class BSTIterator {
    private Node root;
    private Node current;
    Map<Node, Node> bstMap = new HashMap<>();

    public BSTIterator(Node node) {
        this.root = node;
        this.current = node;
        buildMap();
    }

    public Node getRoot() {
        return root;
    }

    public Node getCurrent() {
        return current;
    }

    // https://www.geeksforgeeks.org/populate-inorder-successor-for-all-nodes/
    private void buildMap() {
        Node next = null;
        Node tmp = root;
        buildMapRec(tmp, next);
        //printMap();
    }

    private Node buildMapRec(Node n, Node next) {
        if (n == null) {
            return null;
        }
        Node tn = buildMapRec(n.getRight(), next);
        if (tn != null) {
            bstMap.put(n, tn);
        } else {
            bstMap.put(n, next);
        }
        next = n;
        buildMapRec(n.getLeft(), next);
        return next;
    }

    private void printMap() {
        for (Map.Entry<Node, Node> e : bstMap.entrySet()) {
            if (e.getValue() != null) {
                System.out.println(e.getKey().getData() + " : " + e.getValue().getData());
            } else {
                System.out.println(e.getKey().getData() + " : null");
            }
        }
    }

    private Node findLefMostNodeInRight(Node n) {
        while (n.getLeft() != null) {
            n = n.getLeft();
        }
        return n;
    }

    private Node getInorderSuccessor(Node n) {
        Node tmp = root;
        Node res = null;
        if (n == null) {
            return null;
        }
        // If node has right child
        /*
        if (n.getRight() != null) {
            res = n.getRight();
            n = n.getRight();
            // Return left most node of the node
            return findLeftMostNodeInRight(n);
        }
        */
        while (tmp != null) {
            if (tmp.getData() > n.getData()) {
                res = tmp;
                tmp = tmp.getLeft();
            } else {
                tmp = tmp.getRight();
            }
        }
        return res;
    }

    public boolean hasNext() {
        return bstMap.get(current) != null;
    }

    public int next() {
        current = bstMap.get(current);
        return current.getData();
    }
}

// -----------------------------------------------------------------------------
// Class BST
// -----------------------------------------------------------------------------
class BinarySearchTree {
    private Node root;
    
    public BinarySearchTree() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void insert(int data) {
        if (root == null) {
            root = new Node(data);
            return;
        }

        Node tmp = root;
        while (tmp != null) {
            // Check if leaf node
            if (tmp.getLeft() == null && tmp.getRight() == null) {
                if (data > tmp.getData()) {
                    tmp.setRight(new Node(data));
                } else {
                    tmp.setLeft(new Node(data));
                }
                return;
            }

            if (data > tmp.getData()) {
                if (tmp.getRight() == null) {
                    tmp.setRight(new Node(data));
                    return;
                }
                tmp = tmp.getRight();
            } else {
                if (tmp.getLeft() == null) {
                    tmp.setLeft(new Node(data));
                    return;
                }
                tmp = tmp.getLeft();
            }
        }
    }
}

// -----------------------------------------------------------------------------
// Main Class BST Problems
// -----------------------------------------------------------------------------
public class BinarySearchTreeProblems {

    static void printBST(BinarySearchTree bst) {
        printBSTRec(bst.getRoot());
    }

    static void printBSTRec(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.getData());
        printBSTRec(root.getLeft());
        printBSTRec(root.getRight());
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(10);

        // Level 1
        bst.insert(5);
        bst.insert(15);

        // Level 2
        bst.insert(2);
        bst.insert(7);
        bst.insert(12);
        bst.insert(17);

        //printBST(bst);

        BSTIterator bsi = new BSTIterator(bst.getRoot().getLeft().getLeft());
        while (bsi.hasNext())
        {
            System.out.println(bsi.next() + " ");
        }
        System.out.println();
    }
}
