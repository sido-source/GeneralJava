package tree.binary;

public class IsSymmetric {
    public static boolean isSymmetric(TreeNode root) {
        // Base case for an empty tree
        if (root == null) return true;

        // Call helper with left and right subtrees
        return isSymmetricHelper(root.left, root.right);
    }

    private static boolean isSymmetricHelper(TreeNode left, TreeNode right) {
        // Both nodes are null, symmetric at this level
        if (left == null && right == null) return true;

        // One node is null and the other isn't, so not symmetric
        if (left == null || right == null) return false;

        // Values must match for symmetry
        if (left.val != right.val) return false;

        // Recursive checks for mirroring structure
        return isSymmetricHelper(left.left, right.right) &&
                isSymmetricHelper(left.right, right.left);
    }

    public static void main(String[] args) {

    }
}


/*
The time complexity for this solution is O(n), where ( n ) is the number of nodes in the tree, as each node is visited once.
The space complexity is  O(h) , where ( h ) is the height of the tree, due to the recursion stack. This is efficient for our purpose.



Step 2: Initial Thoughts on Approach and Edge Cases
Me: "Great, I'll start by outlining my thought process for this problem. I’ll look at it both recursively and iteratively. For now, I’ll plan to solve it recursively unless there’s a preference otherwise?"

Now, I'll walk through edge cases to ensure I don't miss anything unexpected:

Empty Tree: "If the tree has no nodes (root is null), we can consider it symmetric by definition. I’ll make a quick base case for that."
Single Node Tree: "A tree with just one node should also be symmetric since there’s no left or right subtree to compare."
Uneven Subtrees: "If one subtree is present on the left but not on the right, this would be asymmetric."
Symmetric Structure but Different Values: "Lastly, if the left and right subtrees have the same structure but different values, they aren’t symmetric, so I’ll need to handle that as well."


Step 3: Solution Outline
Me: "To solve this, my plan is to write a helper function that takes two nodes: one from the left subtree and one from the right. This helper will recursively check that:

If both nodes are null, they’re symmetric.
If only one of them is null, they’re not.
If both nodes are non-null, their values must be the same, and their respective child nodes should be symmetric in a mirrored fashion.
The symmetry check would look like this in code:

left.left compared to right.right
left.right compared to right.left"

 */
