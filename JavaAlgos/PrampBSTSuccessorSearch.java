import java.io.*;
import java.util.*;

/********************************************************
 * CODE INSTRUCTIONS:                                   *
 * 1) The method findInOrderSuccessor you're asked      *
 *    to implement is located at line 36.               *
 * 2) Use the helper code below to implement it.        *
 * 3) In a nutshell, the helper code allows you to      *
 *    to build a Binary Search Tree.                    *
 * 4) Jump to line 103 to see an example for how the    *
 *    helper code is used to test findInOrderSuccessor. *
 ********************************************************/

public class PrampBSTSuccessorSearch {

    static class Node {

        int key;
        Node left;
        Node right;
        Node parent;

        Node(int key) {
            this.key = key;
            left = null;
            right = null;
            parent = null;
        }
    }

    static class BinarySearchTree {

        Node root;

        Node findLeftMostNodeInRight(Node n) {
            while (n.left != null) {
                n = n.left;
            }
            return n;
        }

        Node findInOrderSuccessor(Node inputNode) {
            if (inputNode == null) {
                return null;
            }
            Node result;
            if (inputNode.right != null) {
                result = inputNode.right;
                inputNode = inputNode.right;
                return findLeftMostNodeInRight(inputNode);
            } else {
                // Find the parent of this node and 
                // keep finding a parent until the current node is left child of the parent
                while (inputNode.parent != null) {
                    if (inputNode.parent.left == inputNode) {
                        return inputNode.parent;
                    } else {
                        inputNode = inputNode.parent;
                    }
                }
            }
            return null;
        }

        //  Given a binary search tree and a number, inserts a new node
        //  with the given number in the correct place in the tree
        void insert(int key) {

            // 1. If the tree is empty, create the root
            if(this.root == null) {
                this.root = new Node(key);
                return;
            }

            // 2) Otherwise, create a node with the key
            //    and traverse down the tree to find where to
            //    to insert the new node
            Node currentNode = this.root;
            Node newNode = new Node(key); 

            while(currentNode != null) {
                if(key < currentNode.key) {
                    if(currentNode.left == null) {
                        currentNode.left = newNode;
                        newNode.parent = currentNode;
                        break;
                    } else {
                        currentNode = currentNode.left;
                    }
                } else {
                    if(currentNode.right == null) {
                        currentNode.right = newNode;
                        newNode.parent = currentNode;
                        break;
                    } else {
                        currentNode = currentNode.right;
                    }
                }
            }
        }

        // Return a reference to a node in the BST by its key.
        // Use this method when you need a node to test your 
        // findInOrderSuccessor method on
        Node getNodeByKey(int key) {
            Node currentNode = this.root;

            while(currentNode != null) {
                if(key == currentNode.key) {
                    return currentNode;
                }

                if(key < currentNode.key) {
                    currentNode = currentNode.left;
                } else {
                    currentNode = currentNode.right;
                }
            }

            return null; 
        }
    }

    /***********************************************
     * Driver program to test findInOrderSuccessor *
     ***********************************************/

    public static void main(String[] args) {

        Node test1 = null;
        Node test2 = null;
        Node test3 = null;
        Node test4 = null;
        Node test5 = null;
        Node test6 = null;
        Node test7 = null;
        Node succ = null;

        // Create a Binary Search Tree
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(20);
        tree.insert(9);
        tree.insert(25);
        tree.insert(5);
        tree.insert(12);
        tree.insert(11);
        tree.insert(14);

        // Get a reference to the node whose key is 9
        test1 = tree.getNodeByKey(5);
        test2 = tree.getNodeByKey(9);
        test3 = tree.getNodeByKey(11);
        test4 = tree.getNodeByKey(12);
        test5 = tree.getNodeByKey(14);
        test6 = tree.getNodeByKey(20);
        test7 = tree.getNodeByKey(25);

        // Find the in order successor of test
        succ = tree.findInOrderSuccessor(test1);
        // Print the key of the successor node
        if (succ != null) {
            System.out.println("Inorder successor of " + test1.key +
                    " is " + succ.key);
        } else {
            System.out.println("Inorder successor does not exist");
        }

        succ = tree.findInOrderSuccessor(test2);     
        if (succ != null) {
            System.out.println("Inorder successor of " + test2.key + " is " + succ.key);
        } else {
            System.out.println("Inorder successor does not exist");
        }

        succ = tree.findInOrderSuccessor(test3);     
        if (succ != null) {
            System.out.println("Inorder successor of " + test3.key + " is " + succ.key);
        } else {
            System.out.println("Inorder successor does not exist");
        }

        succ = tree.findInOrderSuccessor(test4);     
        if (succ != null) {
            System.out.println("Inorder successor of " + test4.key + " is " + succ.key);
        } else {
            System.out.println("Inorder successor does not exist");
        }

        succ = tree.findInOrderSuccessor(test5);     
        if (succ != null) {
            System.out.println("Inorder successor of " + test5.key + " is " + succ.key);
        } else {
            System.out.println("Inorder successor does not exist");
        }

        succ = tree.findInOrderSuccessor(test5);     
        if (succ != null) {
            System.out.println("Inorder successor of " + test5.key + " is " + succ.key);
        } else {
            System.out.println("Inorder successor does not exist");
        }

        succ = tree.findInOrderSuccessor(test6);     
        if (succ != null) {
            System.out.println("Inorder successor of " + test6.key + " is " + succ.key);
        } else {
            System.out.println("Inorder successor does not exist");
        }

        succ = tree.findInOrderSuccessor(test7);     
        if (succ != null) {
            System.out.println("Inorder successor of " + test7.key + " is " + succ.key);
        } else {
            System.out.println("Inorder successor does not exist");
        }    
    }
}
