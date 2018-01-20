class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class EquatingObjetsAndChangingValue {
    public static void main(String[] args) {
        Node a = new Node(5);
        Node b = new Node(4);
        Node c = new Node(7);
        a.left = b;
        a.right = c;

        Node res = a;
        res = res.left;

        System.out.println(res.data);
        System.out.println(a.data);
    }
}
