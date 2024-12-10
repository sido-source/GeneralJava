package tree.binary;

public class IsBinaryBST {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

        public boolean isBST(TreeNode root) {
            return isBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        private boolean isBSTHelper(TreeNode node, long min, long max) {
            if (node == null) {
                return true;
            }
            if (node.val <= min || node.val >= max) {
                return false;
            }
            return isBSTHelper(node.left, min, node.val) && isBSTHelper(node.right, node.val, max);
        }

}
