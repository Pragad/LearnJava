import java.io.*;
import java.util.*;

/**
 * Graph contains a list of nodes
 * A map to quickly tell if a node is present in the graph
 */
public class Graph<T> {

    // Initialize the Collections here
    List<Node<T>> nodes = new ArrayList<>();
    Map<Integer, Node<T>> nodesMap = new HashMap<>();

    public static class Node<T> {
        int id;
        T data;
        List<Node<T>> neighbors;
        Set<Integer> uniqueNeighbors;

        Node(int id, T data) {
            this.id = id;
            this.data = data;
            neighbors = new ArrayList<>();
            uniqueNeighbors = new HashSet<>();
        }

        void addNeighbor(Node<T> node) {
            // A node can't have the same node as it neighbor
            if (id == node.id) {
                System.out.println("Node '" + node.id + "' can't have the same node as its neighbor");
                return;
            }

            if (!uniqueNeighbors.contains(node.id)) {
                uniqueNeighbors.add(node.id);
                neighbors.add(node);
            } else {
                System.out.println("Neighbor '" + node.id + "' is already present for node: " + this.id);
                return;
            }
        }

        void printNeighbors() {
            System.out.println(this.id + "-" + this.data + " Neighbors:");
            for (Node<T> node : neighbors) {
                System.out.print(node.id + "-" + node.data + ", ");
            }
            System.out.println();
        }
    }

    Node<T> getNode(int id) {
        if (!nodesMap.containsKey(id)) {
            System.out.println("Node '" + id + "' is not present");
            return null;
        } else {
            return nodesMap.get(id);
        }
    }

    void createNode(int id, T data) {
        if (!nodesMap.containsKey(id)) {
            Node<T> node = new Node<>(id, data);
            nodes.add(node);
            nodesMap.put(id, node);
        } else {
            System.out.println("Node '" + id + "' is already present");
            return;
        }
    }

    void insertNode(int parentId, int childId) {
        Node<T> parent = getNode(parentId);
        Node<T> child = getNode(childId);
        if (parent != null && child != null) {
            parent.addNeighbor(child);
        } else {
            System.out.println("Node is not present for parent/child");
        }
    }

    void printGraph() {
        for (Node<T> node : nodes) {
            node.printNeighbors();
            System.out.println();
        }
    }

    // -------------------------------------------------------------------------
    // BFS Traversal
    // Very Important: Should make the node visited
    // -------------------------------------------------------------------------
    void printGraphBfs(int startNodeId) {
        System.out.println("\nBFS of Graph");
        Node<T> startNode = getNode(startNodeId);
        Queue<Node<T>> bfsQueue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        bfsQueue.add(startNode);
        visited.add(startNode.id);
        while (!bfsQueue.isEmpty()) {
            Node<T> tmp = bfsQueue.remove();
            System.out.print(tmp.id  + "-" + tmp.data + ", ");
            for (Node<T> neighbor : tmp.neighbors) {
                if (!visited.contains(neighbor.id)) {
                    bfsQueue.add(neighbor);
                    visited.add(neighbor.id);
                }
            }
        }
        System.out.println();
    }

    // -------------------------------------------------------------------------
    // DFS Traversal
    // -------------------------------------------------------------------------
    void printGraphDfs(int startNodeId) {
        System.out.println("\nDFS of Graph");    
        Node<T> startNode = getNode(startNodeId);
        Stack<Node<T>> dfsStack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        dfsStack.push(startNode);
        visited.add(startNode.id);
        while (!dfsStack.isEmpty()) {
            Node<T> tmp = dfsStack.pop();
            System.out.print(tmp.id  + "-" + tmp.data + ", ");
            for (Node<T> neighbor : tmp.neighbors) {
                if (!visited.contains(neighbor.id)) {
                    dfsStack.push(neighbor);
                    visited.add(neighbor.id);
                }
            }
        }
        System.out.println();
    }

    // -------------------------------------------------------------------------
    // Print Graph
    // -------------------------------------------------------------------------
    void printGraphDfsRecursiveUtil(Node<T> node, Set<Integer> visited) {
        if (node.neighbors.isEmpty()) {
            return;
        }
        visited.add(node.id);
        System.out.print(node.id  + "-" + node.data + ", ");
        for (Node<T> n : node.neighbors) {
            if (!visited.contains(n.id)) {
                printGraphDfsRecursiveUtil(n, visited);
            }
        }
    }

    void printGraphDfsRecursive(int startNodeId) {
        System.out.println("\nDFS of Graph Recursively");    
        Node<T> startNode = getNode(startNodeId);
        Set<Integer> visited = new HashSet<>();
        printGraphDfsRecursiveUtil(startNode, visited);
        System.out.println();
    }

    // -------------------------------------------------------------------------
    // Clone Graph
    // -------------------------------------------------------------------------
    Map<Integer, UndirectedGraphNode> nodeSet = new HashMap<>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return node;
        }
        if (nodeSet.containsKey(node.label)) {
            return nodeSet.get(node.label);
        }
        
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        nodeSet.put(node.label, newNode);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            newNode.neighbors.add(cloneGraph(neighbor));
        }
        return newNode;
    }

    // -------------------------------------------------------------------------
    // Main Function
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        /*
         * Imagine a directed version of the below graph
         *
         *      2 -----. 5 
         *     .        /\
         *    /       .   .
         *  1 ----. 4 ----.6
         *    \   /        .   
         *     . .         |
         *      3 ---------
         */

        Graph<String> g = new Graph<>();
        g.createNode(1, "one");
        g.createNode(2, "two");
        g.createNode(3, "three");
        g.createNode(4, "four");
        g.createNode(5, "five");
        g.createNode(6, "six");

        g.insertNode(1, 2);
        g.insertNode(1, 3);
        g.insertNode(1, 4);
        g.insertNode(1, 4);
        g.insertNode(1, 1);

        g.insertNode(2, 5);

        g.insertNode(3, 6);

        g.insertNode(4, 3);
        g.insertNode(4, 6);

        g.insertNode(5, 6);

        g.insertNode(6, 1);
        g.insertNode(6, 2);

        // Print Graph
        //g.printGraph();
        g.printGraphBfs(1);
        g.printGraphDfs(1);
        g.printGraphDfsRecursive(1);

        Graph<String> newGraph = cloneGraph(g);
    }
}
