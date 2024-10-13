package com.monolith.java;

import java.util.ArrayList;
import java.util.List;

public class AVLTreeNodeStep2 {
    class AVLTreeNode {
        int value;
        int index;
        AVLTreeNode left, right;
        int height;

        AVLTreeNode(int value, int index) {
            this.value = value;
            this.index = index;
            this.height = 1;
        }
    }

    private AVLTreeNode root;

    // Utility function to get the height of a node
    int height(AVLTreeNode node) {
        if (node == null) return 0;
        return node.height;
    }

    // Utility function to get balance factor of a node
    int getBalance(AVLTreeNode node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }

    // Right rotate subtree rooted with y
    AVLTreeNode rightRotate(AVLTreeNode y) {
        AVLTreeNode x = y.left;
        AVLTreeNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Left rotate subtree rooted with x
    AVLTreeNode leftRotate(AVLTreeNode x) {
        AVLTreeNode y = x.right;
        AVLTreeNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Insert a new node
    AVLTreeNode insert(AVLTreeNode node, int value, int index) {
        if (node == null) return new AVLTreeNode(value, index);

        // Standard BST insertion logic
        if (index < node.index)
            node.left = insert(node.left, value, index);
        else if (index > node.index)
            node.right = insert(node.right, value, index);
        else
            return node; // Duplicate indices not allowed

        // Update the height of the node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Check balance factor and perform rotations if needed
        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && index < node.left.index)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && index > node.right.index)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && index > node.left.index) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && index < node.right.index) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Delete a node
    AVLTreeNode deleteNode(AVLTreeNode root, int index) {
        if (root == null) return root;

        // Standard BST deletion logic
        if (index < root.index)
            root.left = deleteNode(root.left, index);
        else if (index > root.index)
            root.right = deleteNode(root.right, index);
        else {
            // Node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                AVLTreeNode temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                if (temp == null) {
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                AVLTreeNode temp = minValueNode(root.right);
                root.index = temp.index;
                root.value = temp.value;
                root.right = deleteNode(root.right, temp.index);
            }
        }

        if (root == null) return root;

        // Update height and rebalance
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // Helper function to find the node with the smallest value
    AVLTreeNode minValueNode(AVLTreeNode node) {
        AVLTreeNode current = node;
        while (current.left != null) current = current.left;
        return current;
    }

    // In-order traversal to collect elements in sorted order by index
    void inOrderTraversal(AVLTreeNode node, List<Integer> result) {
        if (node != null) {
            inOrderTraversal(node.left, result);
            result.add(node.value);
            inOrderTraversal(node.right, result);
        }
    }

    // Insert a value with index
    public void insert(int value, int index) {
        root = insert(root, value, index);
    }

    // Delete a value at a specific index
    public void delete(int index) {
        root = deleteNode(root, index);
    }

    // Collect the tree's elements in order
    public List<Integer> getInOrder() {
        List<Integer> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    public static void main(String[] args) {
        AVLTreeNodeStep2 tree = new AVLTreeNodeStep2();
        int[] array = {12, 3, 4, 5, 1, 4, 5, 3, 5, 6};
        int[] deleteIndices = {0, 3, 5}; // List of indices to delete

        // Insert all elements with their respective indices
        for (int i = 0; i < array.length; i++) {
            tree.insert(array[i], i);
        }

        // Process the list of indices for deletion (no need to adjust them manually)
        for (int indexToDelete : deleteIndices) {
            tree.delete(indexToDelete);
        }

        // Get the resulting array in the correct order
        List<Integer> result = tree.getInOrder();

        System.out.println(result);
    }
}
