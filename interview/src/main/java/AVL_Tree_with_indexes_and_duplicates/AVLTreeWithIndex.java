package com.monolith.java;

public class AVLTreeWithIndex {
    class Node {
        int value;
        int size;  // Size of the subtree rooted at this node.
        Node left, right;
        int height;

        Node(int value) {
            this.value = value;
            this.size = 1;
            this.height = 1;
        }
    }

    private Node root;

    public Node getRoot(){
        return root;
    }

    // Helper function to get the height of a node.
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    // Helper function to get the size of a subtree.
    private int size(Node node) {
        return node == null ? 0 : node.size;
    }

    // Right rotate the subtree rooted at y.
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation.
        x.right = y;
        y.left = T2;

        // Update heights and sizes.
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.size = size(y.left) + size(y.right) + 1;
        x.size = size(x.left) + size(x.right) + 1;

        return x;  // New root.
    }

    // Left rotate the subtree rooted at x.
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation.
        y.left = x;
        x.right = T2;

        // Update heights and sizes.
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.size = size(x.left) + size(x.right) + 1;
        y.size = size(y.left) + size(y.right) + 1;

        return y;  // New root.
    }

    // Get the balance factor of a node.
    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // Insert a new value into the AVL tree.
    public void insert(int value) {
        root = insert(root, value);
    }

    public Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }

        // Update height and size.
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        node.size = size(node.left) + size(node.right) + 1;

        // Balance the node.
        int balance = getBalance(node);

        // Left Left Case.
        if (balance > 1 && value < node.left.value) {
            return rightRotate(node);
        }

        // Right Right Case.
        if (balance < -1 && value > node.right.value) {
            return leftRotate(node);
        }

        // Left Right Case.
        if (balance > 1 && value > node.left.value) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case.
        if (balance < -1 && value < node.right.value) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Find and delete the k-th smallest element.
    public void deleteKthSmallest(int k) {
        root = deleteKthSmallest(root, k - 1);
    }

    public Node deleteKthSmallest(Node node, int k) {
        if (node == null) {
            return null;
        }

        int leftSize = size(node.left);

        if (k < leftSize) {
            node.left = deleteKthSmallest(node.left, k);
        } else if (k > leftSize) {
            node.right = deleteKthSmallest(node.right, k - leftSize - 1);
        } else {
            // This is the node to delete.
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                Node minNode = getMinValueNode(node.right);
                node.value = minNode.value;
                node.right = deleteKthSmallest(node.right, 0);
            }
        }

        if (node == null) {
            return null;
        }

        // Update height and size.
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        node.size = size(node.left) + size(node.right) + 1;

        return node;
    }

    private Node getMinValueNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    public  void inOrder(Node node) {
        if (node!=null) {
            inOrder(node.left);
            System.out.print(node.value + " ");
            inOrder(node.right);
        }
    }
    public static void main(String[] args) {
        AVLTreeWithIndex avlTreeWithIndex = new AVLTreeWithIndex();
        avlTreeWithIndex.insert(1);
        avlTreeWithIndex.insert(2);
        avlTreeWithIndex.insert(3);
        avlTreeWithIndex.insert(1);
        System.out.println("Init tree");
        avlTreeWithIndex.inOrder(avlTreeWithIndex.root); // prints: 1 1 2 3 instead of 1 2 3 1 (so I assume the above structure doesnt handle duplicates as values, but it should somehow track index and reindexing after remove)
        System.out.println("\n");

        avlTreeWithIndex.deleteKthSmallest(2);
        avlTreeWithIndex.insert(4);

        System.out.println("After changes");
        avlTreeWithIndex.inOrder(avlTreeWithIndex.root);
        System.out.println("\n");


        avlTreeWithIndex.deleteKthSmallest(1);
        avlTreeWithIndex.insert(5);
        System.out.println("Final result");
        avlTreeWithIndex.inOrder(avlTreeWithIndex.root);



        AVLTreeWithIndex root = new AVLTreeWithIndex();

//        for (int value : List.of(-1,-2,1,-2,3)) {
//            if (value < 0) {
//                // Insert negative value into the AVL tree
//                root.insert(root.getRoot(), value);
//            } else if (value > 0) {
//                // Delete the n-th smallest element, if n <= size
//                //if (value <= index) {
//                root.deleteKthSmallest(root.getRoot(),value);  // n-th smallest, so value - 1 for 0-based index
//                //}
//            }
//            // Do nothing for value == 0
//        }
//
//        // Return the remaining elements in level order (BFS)
//        root.inOrder(root.getRoot());
//        return null;
    }
}
