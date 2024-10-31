package singlyLinkedList;

public class RemoveNthElemFromTheEnd {

    public static void main(String[] args) {
        ListNode head = new ListNode(8);
        head.next = new ListNode(3);
        head.next.next = new ListNode(5);
        head.next.next.next = new ListNode(10);

        ListNode listNode = removeNthFromEnd(head, 3);
    }


    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // Step 1: Create a dummy node that points to the head, if we start from dummy then we have to iterate from 0
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Step 2: Initialize two pointers, both pointing at the dummy node initially
        ListNode first = dummy;
        ListNode second = dummy;

        // Step 3: Move the first pointer n+1 steps ahead to maintain the gap
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        // Step 4: Move both pointers until the first pointer reaches the end
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // Step 5: The second pointer now points to the node before the one to be removed
        second.next = second.next.next;

        // Step 6: Return the head of the modified list
        return dummy.next;
    }
}
