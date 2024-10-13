import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AVLTree {

    public class Node {
        int key; // value stored in the node
        int index; // index of the node in the sequence
        int height;
        int size; // to track the size of the subtree for rank-based operations
        Node left;
        Node right;

        Node(int key, int index) {
            this.key = key;
            this.index = index;
            this.height = 1;
            this.size = 1;
        }
    }

    private Node root;
    private int currentIndex = 1; // to track the current index to be assigned

    public void insert(int key) {
        root = insert(root, key, currentIndex);
        currentIndex++;
    }

    public void deleteNth(int n) {
        root = deleteNth(root, n);
        currentIndex--;
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    // Updated: Level-order traversal (BFS)
    public List<Integer> levelOrderTraversal() {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            result.add(current.key);

            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return result;
    }

    private Node insert(Node node, int key, int index) {
        if (node == null) {
            return new Node(key, index); // Insert node with the given key and index
        }
        if (index < node.index) {
            node.left = insert(node.left, key, index);
        } else if (index > node.index) {
            node.right = insert(node.right, key, index);
        } else {
            throw new RuntimeException("Duplicate key or index!");
        }

        return rebalance(node);
    }

    private Node deleteNth(Node node, int n) {
        if (node == null) {
            return null;
        }

        int leftSize = size(node.left);

        if (n < leftSize) {
            node.left = deleteNth(node.left, n);
        } else if (n > leftSize) {
            node.right = deleteNth(node.right, n - leftSize - 1);
        } else {
            // Node to delete is found
            node = delete(node, node.key);
        }

        return rebalance(node);
    }

    private Node delete(Node node, int key) {
        if (node == null) {
            return node;
        }

        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {
            // Node to be deleted found
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                Node mostLeftChild = mostLeftChild(node.right);
                node.key = mostLeftChild.key;
                node.index = mostLeftChild.index; // Copy the index
                node.right = delete(node.right, node.key);
            }
        }

        return rebalance(node);
    }

    private Node mostLeftChild(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private Node rebalance(Node node) {
        updateHeightAndSize(node);
        int balance = getBalance(node);

        if (balance > 1) {
            if (height(node.right.right) > height(node.right.left)) {
                node = rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }
        } else if (balance < -1) {
            if (height(node.left.left) > height(node.left.right)) {
                node = rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }
        }
        return node;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node z = x.right;
        x.right = y;
        y.left = z;
        updateHeightAndSize(y);
        updateHeightAndSize(x);
        return x;
    }

    private Node rotateLeft(Node y) {
        Node x = y.right;
        Node z = x.left;
        x.left = y;
        y.right = z;
        updateHeightAndSize(y);
        updateHeightAndSize(x);
        return x;
    }

    private void updateHeightAndSize(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
        node.size = 1 + size(node.left) + size(node.right);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private int size(Node node) {
        return node == null ? 0 : node.size;
    }

    private int getBalance(Node node) {
        return node == null ? 0 : height(node.right) - height(node.left);
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();

    }
}
