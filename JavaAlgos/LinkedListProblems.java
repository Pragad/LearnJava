import java.io.*;

// ---------------------------------------------------------------------------------------------
// Node class that tell how a linked list node will look like
// ---------------------------------------------------------------------------------------------
class Node {
    int data;
    Node next;

    Node(int value) {
        data = value;
        next = null;
    }
}

// ---------------------------------------------------------------------------------------------
// Basic linked list class that has all linkedlist functions
// ---------------------------------------------------------------------------------------------
class LinkedList {
    Node head;

    LinkedList() {
        head = null;
    }

    // -----------------------------------------------------------------------------------------
    // Push
    // -----------------------------------------------------------------------------------------
    Node push(int value) {
        Node n = new Node(value);
        if (head == null) {
            head = n;
        } else {
            n.next = head;
            head = n;
        }
        return head;
    }

    // -----------------------------------------------------------------------------------------
    // Build list
    // -----------------------------------------------------------------------------------------
    Node buildList123() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        n1.next = n2;
        n2.next = n3;
        head = n1;
        return head;
    }

    // -----------------------------------------------------------------------------------------
    // Print list
    // -----------------------------------------------------------------------------------------
    void printList() {
        Node tmp = head;
        System.out.println();
        while (tmp != null) {
            System.out.print(tmp.data + " ");
            tmp = tmp.next;
        }
        System.out.println();
    }

    void printListMoveHead() {
        System.out.println();
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    // -----------------------------------------------------------------------------------------
    // Front back split
    // -----------------------------------------------------------------------------------------
    void frontBackSplit() {
    }

    // -----------------------------------------------------------------------------------------
    // Reverse a list in iterative way
    // -----------------------------------------------------------------------------------------
    void reverse() {
        Node current = head;
        Node prev = null;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    // -----------------------------------------------------------------------------------------
    // Reverse a list in recursive way
    // -----------------------------------------------------------------------------------------
    Node reverseRecUtil(Node current, Node prev, Node head) {
        if (current.next == null) {
            head = current;
            current.next = prev;
            return head;
        }
        Node next = current.next;
        current.next = prev;
        return reverseRecUtil(next, current, head);
    }

    void reverseRec() {
        if (head == null) {
            return;
        }
        head = reverseRecUtil(head, null, head);
    }
}

public class LinkedListProblems {
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.push(5);
        ll.push(4);
        ll.push(3);
        ll.printList();
        if (ll.head != null) {
            System.out.println("Head is not null");
            System.out.println(ll.head.data);
        } else {
            System.out.println("Head is null");
        }
        /*
        ll.printListMoveHead();
        if (ll.head != null) {
            System.out.println("Head is not null");
            System.out.println(ll.head.data);
        } else {
            System.out.println("Head is null");
        }
        */
        ll.reverse();
        ll.printList();
        ll.reverseRec();
        ll.printList();
    }
}
