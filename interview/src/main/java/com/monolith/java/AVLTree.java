package com.monolith.java;

import java.util.Scanner;
class AVLTree
{
    // NODE structure
    class Node
    {
        int value;
        int height;
        Node left;
        Node right;

        public Node(int value)
        {
            this.value = value;
            this.height = 1;
            this.left = null;
            this.right = null;
        }
    }

    //returns the height of the node
    int Height(Node key)
    {
        if (key == null)
            return 0;

        else
            return key.height;
    }


    // Balance computes the balance factor of the node
    int Balance(Node key)
    {
        if (key == null)
            return 0;

        else
            return ( Height(key.right) - Height(key.left) );
    }


    // updateHeight updates the height of the node
    void updateHeight(Node key)
    {
        int l = Height(key.left);
        int r = Height(key.right);

        key.height = Math.max(l , r) + 1;
    }

    Node rotateLeft(Node x)
    {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    Node rotateRight(Node y)
    {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // balanceTree balances the tree using rotations after an insertion or deletion
    Node balanceTree(Node root)
    {
        updateHeight(root);

        int balance = Balance(root);

        if (balance > 1) //R
        {
            if (Balance(root.right) < 0)//RL
            {
                root.right = rotateRight(root.right);
                return rotateLeft(root);
            }

            else //RR
                return rotateLeft(root);
        }

        if (balance < -1)//L
        {
            if (Balance(root.left) > 0)//LR
            {
                root.left = rotateLeft(root.left);
                return rotateRight(root);
            }
            else//LL
                return rotateRight(root);
        }

        return root;
    }

    Node Root;


    Node BSTInsert(Node root, int key)
    {
        // Performs normal BST insertion
        if (root == null)
            return new Node(key);

        else if (key < root.value)
            root.left = BSTInsert(root.left, key);

        else
            root.right = BSTInsert(root.right, key);

        // Balances the tree after BST Insertion
        return balanceTree(root);
    }

    // Successor returns the next largest node
    Node Successor(Node root)
    {
        if (root.left != null)
            return Successor(root.left);

        else
            return root;
    }


    Node Remove(Node root, int key)
    {
        // Performs standard BST Deletion
        if (root == null)
            return root;

        else if (key < root.value)
            root.left = Remove(root.left, key);

        else if (key > root.value)
            root.right = Remove(root.right, key);

        else
        {
            if (root.right == null)
                root = root.left;

            else if (root.left == null)
                root = root.right;

            else
            {
                Node temp = Successor(root.right);
                root.value = temp.value;
                root.right = Remove(root.right, root.value);
            }
        }

        if (root == null)
            return root;

        else
            // Balances the tree after deletion
            return balanceTree(root);
    }

    // findNode is used to search for a particular value given the root
    Node findNode(Node root, int key)
    {
        if (root == null || key==root.value)
            return root;

        if (key < root.value)
            return findNode(root.left, key);

        else
            return findNode(root.right, key);
    }

    // Utility function for insertion of node
    void add(int key)
    {
        if (findNode(Root , key) == null)
        {
            Root = BSTInsert(Root , key);
            System.out.println("Insertion successful");
        }

        else
            System.out.println("\nKey with the entered value already exists in the tree");
    }

    int search(int key)
    {
        if(findNode(Root, key) == null)
            return 0;
        else
            return 1;
    }

    // Utility function for deletion of node
    void delete(int key)
    {
        if (findNode(Root , key) != null)
        {
            Root = Remove(Root , key);
            System.out.println("\nDeletion successful ");
        }

        else
            System.out.println("\nNo node with entered value found in tree");
    }

    void InOrder(Node root)
    {
        if(root == null)
        {
            System.out.println("\nNo nodes in the tree");
            return;
        }

        if(root.left != null)
            InOrder(root.left);
        System.out.print(root.value + " ");
        if(root.right != null)
            InOrder(root.right);

    }

    void PreOrder(Node root)
    {
        if(root == null)
        {
            System.out.println("No nodes in the tree");
            return;
        }

        System.out.print(root.value + " ");
        if(root.left != null)
            PreOrder(root.left);
        if(root.right != null)
            PreOrder(root.right);

    }

    void PostOrder(Node key)
    {
        if(key == null)
        {
            System.out.println("No nodes in the tree");
            return;
        }


        if(key.left != null)
            PostOrder(key.left);
        if(key.right != null)
            PostOrder(key.right);
        System.out.print(key.value + " ");

    }

}

class AVLT
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        AVLTree tree = new AVLTree();
        tree.add(10);
        tree.add(20);
        tree.add(26);
        tree.add(40);
        tree.delete(10);
        tree.InOrder(tree.Root);
    }
}
