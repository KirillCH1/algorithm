public class RedBlackTree<V extends Comparable> {
    private Node root;

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        V value;
        Node left;
        Node right;
        boolean color;

        Node(V value, boolean color) {
            this.value = value;
            this.color = color;
        }
    }

    public void add(V value) {
        root = add(root, value);
        root.color = BLACK;
    }

    private Node add(Node node, V value) {
        if (node == null) {
            return new Node(value, RED);
        }

        if (value.compareTo(node.value) < 0) {
            node.left = add(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = add(node.right, value);
        } else {
            // If the value already exists, update it
            node.value = value;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    private Node rotateLeft(Node node) {
        Node newNode = node.right;
        node.right = newNode.left;
        newNode.left = node;
        newNode.color = node.color;
        node.color = RED;
        return newNode;
    }

    private Node rotateRight(Node node) {
        Node newNode = node.left;
        node.left = newNode.right;
        newNode.right = node;
        newNode.color = node.color;
        node.color = RED;
        return newNode;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color == RED;
    }
}
