package tree.binary;

public class InvertBinaryTree {

    public static TreeNode invertTree(TreeNode root) {

        if (root == null) return root;
        // detach
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.left);

        // change left to right
        // change rigt to left

        root.left = right;
        root.right = left;

        return root;

    }

    public static void main(String[] args) {

        TreeNode root3 = new TreeNode(
                new TreeNode(new TreeNode(1), new TreeNode(3), 2),
                new TreeNode(new TreeNode(6), new TreeNode(9), 7),
                4
        );

        invertTree(root3);


    }
}
