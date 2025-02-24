package trees.AVL;

class AVLTreeNode {
    int index;
    int value;
    int height;
    AVLTreeNode left;
    AVLTreeNode right;

    AVLTreeNode(int index, int value) {
        this.index = index;
        this.value = value;
        this.height = 1;
    }
}

class AVLTree {
    private AVLTreeNode root;

    // Insert a node with given index and value
    public void insert(int index, int value) {
        root = insert(root, index, value);
    }

    private AVLTreeNode insert(AVLTreeNode node, int index, int value) {
        if (node == null) return new AVLTreeNode(index, value);

        if (index < node.index) {
            node.left = insert(node.left, index, value);
        } else if (index > node.index) {
            node.right = insert(node.right, index, value);
        } else {
            return node; // duplicate indexes are not allowed
        }

        updateHeight(node);
        return balance(node);
    }

    // Delete a node by index
    public void delete(int index) {
        root = delete(root, index);
    }

    private AVLTreeNode delete(AVLTreeNode node, int index) {
        if (node == null) return null;

        if (index < node.index) {
            node.left = delete(node.left, index);
        } else if (index > node.index) {
            node.right = delete(node.right, index);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                AVLTreeNode minLargerNode = getMin(node.right);
                node.index = minLargerNode.index;
                node.value = minLargerNode.value;
                node.right = delete(node.right, minLargerNode.index);
            }
        }

        if (node == null) return null;

        updateHeight(node);
        return balance(node);
    }

    private AVLTreeNode getMin(AVLTreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private void updateHeight(AVLTreeNode node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int height(AVLTreeNode node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(AVLTreeNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private AVLTreeNode balance(AVLTreeNode node) {
        int balance = getBalance(node);
        if (balance > 1) {
            if (getBalance(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        } else if (balance < -1) {
            if (getBalance(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    private AVLTreeNode rotateRight(AVLTreeNode y) {
        AVLTreeNode x = y.left;
        AVLTreeNode T2 = x.right;
        x.right = y;
        y.left = T2;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private AVLTreeNode rotateLeft(AVLTreeNode x) {
        AVLTreeNode y = x.right;
        AVLTreeNode T2 = y.left;
        y.left = x;
        x.right = T2;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    // In-order traversal for debugging purposes
    public void inOrderTraversal() {
        inOrderTraversal(root);
        System.out.println();
    }

    private void inOrderTraversal(AVLTreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print("(" + node.index + ", " + node.value + ") ");
            inOrderTraversal(node.right);
        }
    }


    public void processArray(int[] nums) {
        AVLTree tree = new AVLTree();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                tree.insert(i, nums[i]);
            } else {
                tree.delete(nums[i]);
            }
        }
        // Debug output to check the state of the AVL tree
        tree.inOrderTraversal();
    }
}


class Main {
    public static void main(String[] args) {
        int[] nums = {-1, -2, 2, -4, 2, 6, -7, -8, 2};
        AVLTree tree = new AVLTree();
        tree.processArray(nums);
    }
}

