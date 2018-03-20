public class Node {
    // optional child nodes
    public Node left, right;

    // the value of the current node
    double value;

    // the character of the current node
    String character;

    // constructor without child nodes
    public Node(double value, String character) {
        this.value = value;
        this.character = character;
        left = null;
        right = null;
    }

    // constructor with child nodes
    public Node(Node left, Node right) {
        // set the value of the value from both child nodes
        this.value = left.value + right.value;

        // set the character to be the character of the child nodes
        character = left.character + right.character;

        // determine the child directions
        if (left.value < right.value) {
            this.right = right;
            this.left = left;
        } else {
            this.right = left;
            this.left = right;
        }
    }
}
