// A,B,C... 
// A owns 5% of C
// B owns 10% of A etc...
// C owns 4% of D.
// D owns 6% of B.
// Double getPercentage(x, y);
// getPercentage(A, B)

// A owns C and C owns B
// A owns E and E owns B
class Node {
    String companyName;
    List<Node> neighbors;
    List<Integer> weights;
}


Double getPercentage(Node a, Node b) {
    // Base case.
    if (a == b) {
       return 100;
    }
    Double result = 0;    
    for (int i = 0; i < a.neighbors.size(); i++) {
        if (a.neighbors.get(i).companyName == b.companyName) {
            result += a.weights.get(i);
            continue;
        }
        Double tmp = getPercentage(a.neighbors.get(i), b);
        
        // TODO: How A makes decision to decide how much it owns of C
        // A -> B 5%
        // A 6%-> C 4%-> B --? A -0.24%> B
        result += a.weights.get(i) * tmp / 100;
    }
    return result;
}
