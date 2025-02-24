package singlyLinkedList;

public class RemoveDuplicatesFromSortedList {

    // two pointers
    public static ListNode deleteDuplicates(ListNode head) {
        // Edge case: if the list is empty or has only one element
        if (head == null || head.next == null) return head;

        // Dummy node to return a new List
        ListNode dummy = new ListNode(0, head);

        ListNode prev = dummy;

        while (head != null) {


            // Check if there is a sequence of duplicate nodes
            if (head.next != null && head.val == head.next.val) {

                // Skip nodes with the same value
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }

                // Connect prev node to the node after the last duplicate
                prev.next = head.next;
            } else {

                // If no duplicates, move prev
                prev = prev.next;
            }
            // Move head to the next node
            head = head.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(3);
        node.next.next.next.next = new ListNode(4);
        node.next.next.next.next.next = new ListNode(4);

        ListNode result = deleteDuplicates(node);
        while (result!= null) {
            System.out.print(result.val + " -> ");
            result = result.next;
        }
    }
}
