package com.monolith.java;

public class MyNode {
    int val;
    int key;
    MyNode left, right;
    int height;

    MyNode(int k, int val) {
        key = k;
        left = right = null;
        height = 1;
        this.val = val;
    }

    // A utility function to get the height
    // of the tree
    public int height(MyNode N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // A utility function to right rotate
    // subtree rooted with y
    public MyNode rightRotate(MyNode y) {
        MyNode x = y.left;
        MyNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left),
                height(y.right)) + 1;
        x.height = Math.max(height(x.left),
                height(x.right)) + 1;

        // Return new root
        return x;
    }

    // A utility function to left rotate
    // subtree rooted with x
    public MyNode leftRotate(MyNode x) {
        MyNode y = x.right;
        MyNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left),
                height(x.right)) + 1;
        y.height = Math.max(height(y.left),
                height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    public int getBalance(MyNode N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    public MyNode insert(MyNode myNode, int key, int val) {
        // 1. Perform the normal BST insertion
        if (myNode == null)
            return new MyNode(key, val);

        if (key < myNode.key)
            myNode.left = insert(myNode.left, key, val);
        else if (key > myNode.key)
            myNode.right = insert(myNode.right, key, val);
        else // Duplicate keys not allowed
            return myNode;

        // 2. Update height of this ancestor node
        myNode.height = Math.max(height(myNode.left),
                height(myNode.right)) + 1;

        // 3. Get the balance factor of this node
        // to check whether this node became
        // unbalanced
        int balance = getBalance(myNode);

        // If this node becomes unbalanced, then
        // there are 4 cases

        // Left Left Case
        if (balance > 1 && key < myNode.left.key)
            return rightRotate(myNode);

        // Right Right Case
        if (balance < -1 && key > myNode.right.key)
            return leftRotate(myNode);

        // Left Right Case
        if (balance > 1 && key > myNode.left.key) {
            myNode.left = leftRotate(myNode.left);
            return rightRotate(myNode);
        }

        // Right Left Case
        if (balance < -1 && key < myNode.right.key) {
            myNode.right = rightRotate(myNode.right);
            return leftRotate(myNode);
        }

        return myNode;
    }

    // Given a non-empty binary search tree,
    // return the node with minimum key value
    // found in that tree.
    public MyNode minValueNode(MyNode myNode) {
        MyNode current = myNode;

        // loop down to find the leftmost leaf
        while (current.left != null)
            current = current.left;

        return current;
    }

    // Recursive function to delete a node with
    // given key from subtree with given root.
    // It returns root of the modified subtree.
    public MyNode deleteNode(MyNode root, int key) {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null)
            return root;

        // If the key to be deleted is smaller
        // than the root's key, then it lies in
        // left subtree
        if (key < root.key)
            root.left = deleteNode(root.left, key);

            // If the key to be deleted is greater
            // than the root's key, then it lies in
            // right subtree
        else if (key > root.key)
            root.right = deleteNode(root.right, key);

            // if key is same as root's key, then
            // this is the node to be deleted
        else {
            // node with only one child or no child
            if ((root.left == null) ||
                    (root.right == null)) {
                MyNode temp = root.left != null ?
                        root.left : root.right;

                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else // One child case
                    root = temp; // Copy the contents of
                // the non-empty child
            } else {
                // node with two children: Get the
                // inorder successor (smallest in
                // the right subtree)
                MyNode temp = minValueNode(root.right);

                // Copy the inorder successor's
                // data to this node
                root.key = temp.key;

                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.key);
            }
        }

        // If the tree had only one node then return
        if (root == null)
            return root;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = Math.max(height(root.left),
                height(root.right)) + 1;

        // STEP 3: GET THE BALANCE FACTOR OF THIS
        // NODE (to check whether this node
        // became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then
        // there are 4 cases

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

    // A utility function to print preorder
    // traversal of the tree.
    public void preOrder(MyNode root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public void inOrder(MyNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.key + " ");
            inOrder(root.right);
        }
    }

    // Driver Code
    public static void main(String[] args) {
        MyNode root = null;

        // Constructing tree given in the
        // above figure
        root = root.insert(root, 1, -1);
        root = root.insert(root, 2, -2);
        root = root.insert(root, 3, -3);
        root = root.insert(root, 4, -4);
        root = root.insert(root, 5, -5);
        root = root.insert(root, 6, -6);
        root = root.insert(root, 7, -7);

        System.out.println("Preorder traversal of the "
                + "constructed AVL tree is:");
        root.preOrder(root);

        root = root.deleteNode(root, 3);

        System.out.println("\nPreorder traversal after"
                + " deletion of 10:");
        root.preOrder(root);

        System.out.println("\n");
        root.inOrder(root);
    }
}
