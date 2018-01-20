import java.util.*;

// -----------------------------------------------------------------------------
// Graph class
// -----------------------------------------------------------------------------
class Graph {
    // Faster way is to use a map to quickly lookup a vertex
    Map<String, Vertex> verticesMap = new HashMap<>();

    // -------------------------------------------------------------------------
    // Edge class
    // -------------------------------------------------------------------------
    static class Edge {
        int weight;
        Vertex startNode;
        Vertex endNode;

        Edge(Vertex a, Vertex b, int weight) {
            this.startNode = a;
            this.endNode = b;
            this.weight = weight;
        }

        Edge(Vertex a, Vertex b) {
            this.startNode = a;
            this.endNode = b;
            this.weight = 0;
        }

        void printEdge() {
            System.out.println("S: " + startNode.id + "; E: " + endNode.id + "; W: " + weight);
        }
    }

    // -------------------------------------------------------------------------
    // Vertex class
    // Make this as generic
    // -------------------------------------------------------------------------
    static class Vertex {
        String id;
        List<Edge> edges = new ArrayList<>();

        Vertex(String id) {
            this.id = id;
        }

        void addEdge(Edge e) {
            edges.add(e);
        }
    }

    public void addVertex(String id) {
        Vertex v = new Vertex(id);
        verticesMap.put(id, v);
    }

    // This is a directed edge from id1 to id2
    public void addEdge(String id1, String id2, int weight) {
        if (!verticesMap.containsKey(id1) || !verticesMap.containsKey(id2)) {
            System.out.println("Vertices not present");
            return;
        }
        Vertex a = verticesMap.get(id1);
        Vertex b = verticesMap.get(id2);
        Edge e = new Edge(a, b, weight);
        a.addEdge(e);
    }

    public void printGraph() {
        for(Map.Entry<String, Vertex> e : verticesMap.entrySet()) {
            System.out.println("ID: " + e.getKey());
            for (Edge edge : e.getValue().edges) {
                edge.printEdge();
            }
        }
        System.out.println();
    }

    public Double getPercentage(String id1, String id2) {
        Vertex a = verticesMap.get(id1);
        Vertex b = verticesMap.get(id2);
        if (a == null || b == null) {
            return 0.0;
        }
        return getPercentage(a, b);
    }

    public Double getPercentage(Vertex a, Vertex b) {
        Double result = 0.0;
        if (a.id.equals(b.id)) {
            return 100.0;
        }
        for (int i = 0; i < a.edges.size(); i++) {
            if (a.edges.get(i).endNode.id.equals(b.id)) {
                result += a.edges.get(i).weight;
                continue;
            }
            Double tmp = getPercentage(a.edges.get(i).endNode, b);
            result += a.edges.get(i).weight * tmp / 100;
        }
        return result;
    }

    public void printBFS(String stId) {
        if (!verticesMap.containsKey(stId)) {
            return;
        }
        Queue<Vertex> bfsQ = new LinkedList<>();
        Set<Vertex> visited = new HashSet<>();

        Vertex start = verticesMap.get(stId);
        bfsQ.add(start);
        visited.add(start);

        while (!bfsQ.isEmpty()) {
            Vertex tmp = bfsQ.remove();
            System.out.println(tmp.id);
            for (Edge e : tmp.edges) {
                if (!visited.contains(e.endNode)) {
                    bfsQ.add(e.endNode);
                    visited.add(e.endNode);
                }
            }
        }
        System.out.println();
    }

    public void printDFS(String stId) {
        if (!verticesMap.containsKey(stId)) {
            return;
        }
        Stack<Vertex> dfsS = new Stack<>();
        Set<Vertex> visited = new HashSet<>();

        Vertex start = verticesMap.get(stId);
        dfsS.push(start);
        visited.add(start);

        while (!dfsS.empty()) {
            Vertex tmp = dfsS.pop();
            System.out.println(tmp.id);
            for (Edge e : tmp.edges) {
                if (!visited.contains(e.endNode)) {
                    dfsS.push(e.endNode);
                    visited.add(e.endNode);
                }
            }
        }
        System.out.println();

    }
}

// -----------------------------------------------------------------------------
// Main class
// -----------------------------------------------------------------------------
public class DirectedGraphProblems {
    public static void main(String[] args) {
        Graph g = new Graph();
        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("D");
        g.addVertex("E");
        g.addVertex("F");

        // Get percentage problem
        {
            /*
            g.addEdge("A", "B", 5);
            g.addEdge("A", "D", 50);
            g.addEdge("D", "E", 40);
            g.addEdge("E", "B", 5);
            g.addEdge("A", "E", 60);
            */
            System.out.println(g.getPercentage("A", "B"));
        }

        g.addEdge("A", "B", 0);
        g.addEdge("A", "C", 0);
        g.addEdge("B", "D", 0);
        g.addEdge("C", "A", 0);
        g.addEdge("C", "D", 0);
        g.addEdge("D", "E", 0);
        g.addEdge("D", "F", 0);
        g.addEdge("E", "F", 0);
        g.printGraph();
        g.printBFS("A");
        g.printDFS("A");
    }
}
