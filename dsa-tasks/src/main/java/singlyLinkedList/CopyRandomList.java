package singlyLinkedList;

public class CopyRandomList {
    // Definition for a Node.
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // Step 1: Create new nodes and interleave them with the original nodes.
        // Box 1 -> Copy of Box 1 -> Box 2 -> Copy of Box 2 -> Box 3 -> Copy of Box 3 -> null
        Node current = head;
        while (current != null) {
            // create a new node with pointing to the rest of the nodes
            Node newNode = new Node(current.val);
            newNode.next = current.next;

            // Insert the new node after the current node from original list
            current.next = newNode;
            // we have to get back to the original next node
            current = newNode.next;
        }

        // Step 2: Assign random pointers for the copied nodes.
        current = head;
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }

        // Step 3: Separate the copied nodes from the original nodes.
        current = head;
        Node newHead = head.next;
        Node copy = newHead;
        while (current != null) {
            current.next = current.next.next;
            if (copy.next != null) {
                copy.next = copy.next.next;
            }
            current = current.next;
            copy = copy.next;
        }

        return newHead;
    }

    // Helper function to print the list
    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            String randomVal = (temp.random != null) ? String.valueOf(temp.random.val) : "null";
            System.out.println("Node Value: " + temp.val + ", Random points to: " + randomVal);
            temp = temp.next;
        }
    }

    // Main function to set up the test data and test the solution
    public static void main(String[] args) {
        // Create a list: 7 -> 13 -> 11 -> 10 -> 1
        Node node1 = new Node(7);
        Node node2 = new Node(13);
        Node node3 = new Node(11);
        Node node4 = new Node(10);
        Node node5 = new Node(1);

        // Set up next pointers
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        // Set up random pointers
        node1.random = null;
        node2.random = node1;
        node3.random = node5;
        node4.random = node3;
        node5.random = node1;

        System.out.println("Original list:");
        printList(node1);

        CopyRandomList solution = new CopyRandomList();
        Node copiedList = solution.copyRandomList(node1);

        System.out.println("\nCopied list:");
        printList(copiedList);
    }



}
