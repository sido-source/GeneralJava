package com.monolith.java;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class AVLTreeWithIndexDuplicates {
    class Node {
        int value;
        int index; // Unique index to distinguish duplicates.
        int size;  // Size of the subtree rooted at this node.
        Node left, right;
        int height;

        Node(int value, int index) {
            this.value = value;
            this.index = index;
            this.size = 1;
            this.height = 1;
        }
    }

    private Node root;

    public Node getRoot() {
        return root;
    }

    // Helper method to return the size of a node (0 if node is null)
    private int size(Node node) {
        return node == null ? 0 : node.size;
    }

    // Insert a new value with its unique index into the AVL tree.
    public void insert(int value, int index) {
        root = insert(root, value, index);
    }

    private Node insert(Node node, int value, int index) {
        if (node == null) {
            return new Node(value, index);
        }

        if (index < node.index) {
            node.left = insert(node.left, value, index);
        } else if (index > node.index) {
            node.right = insert(node.right, value, index);
        }

        // Update height and size.
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        node.size = size(node.left) + size(node.right) + 1;

        return balance(node);
    }

    // Helper method to return the height of a node (0 if node is null)
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    // Delete the k-th smallest element only if k <= size of the tree
    public void deleteKthSmallest(int k) {
        if (size(root) >= k) {
            root = deleteKthSmallest(root, k - 1);
        }
    }

    // Recursive function to delete the k-th smallest element
    private Node deleteKthSmallest(Node node, int k) {
        if (node == null) {
            return null;
        }

        int leftSize = size(node.left);

        if (k < leftSize) {
            node.left = deleteKthSmallest(node.left, k);
        } else if (k > leftSize) {
            node.right = deleteKthSmallest(node.right, k - leftSize - 1);
        } else {
            // This is the node to delete
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                Node minNode = getMinValueNode(node.right);
                node.value = minNode.value;
                node.index = minNode.index;
                node.right = deleteKthSmallest(node.right, 0);
            }
        }

        if (node == null) {
            return null;
        }

        // Update height and size.
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        node.size = size(node.left) + size(node.right) + 1;

        return balance(node);
    }

    private Node getMinValueNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Perform balancing operations (same as your original implementation)
    private Node balance(Node node) {
        int balance = height(node.left) - height(node.right);

        if (balance > 1) {
            if (height(node.left.left) >= height(node.left.right)) {
                return rightRotate(node);
            } else {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }

        if (balance < -1) {
            if (height(node.right.right) >= height(node.right.left)) {
                return leftRotate(node);
            } else {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }

        return node;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights and sizes
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.size = size(y.left) + size(y.right) + 1;
        x.size = size(x.left) + size(x.right) + 1;

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights and sizes
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.size = size(x.left) + size(x.right) + 1;
        y.size = size(y.left) + size(y.right) + 1;

        return y;
    }

    // Collect and return elements via in-order traversal
    public void inOrder(Node node, List<Integer> result) {
        if (node != null) {
            inOrder(node.left, result);
            result.add(node.value);
            inOrder(node.right, result);
        }
    }

    public void inOrderPrint(Node node) {
        if (node != null) {
            inOrderPrint(node.left);
            System.out.print((node.value));
            inOrderPrint(node.right);
        }
    }

    public static List<Integer> processArrayAVL(int[] inputArray) {
        AVLTreeWithIndexDuplicates tree = new AVLTreeWithIndexDuplicates();
        int currentIndex = 0; // Track unique index for each element

        for (int value : inputArray) {
            if (value < 0) {
                // Insert negative value with unique index
                tree.insert(value, currentIndex++);

            } else if (value > 0) {
                // Delete k-th smallest element only if k <= size
                tree.deleteKthSmallest(value);
            }

            System.out.println("Curr tree (after " + value + " ) :");
            tree.inOrderPrint(tree.root);
            System.out.println("\n");
        }

        // Collect and return the remaining elements in the tree
        List<Integer> result = new ArrayList<>();
        tree.inOrder(tree.getRoot(), result);
        System.out.println("AVL, array size: " + result.size());
        return result;
    }


    public static void main(String[] args) {
        System.out.println(processArrayAVL(IntStream.of(-4, -3, 2, -3, 2, -4, -3, 2).toArray()));
    }
}
