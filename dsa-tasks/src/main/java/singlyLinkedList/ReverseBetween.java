package singlyLinkedList;

public class ReverseBetween {

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(3);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        ListNode reversedList = reverseBetween(head, 2, 4);
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) return head;

        ListNode dummy = new ListNode(0); // Dummy node to handle edge cases
        dummy.next = head;
        ListNode pre = dummy;

        // Move `pre` to the node just before the `left` position
        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }

        // `start` will point to the first node of the sublist to reverse
        ListNode start = pre.next;
        // `then` is the node that will be reversed
        ListNode then = start.next;

        // Perform the reversal within the specified range
        for (int i = 0; i < right - left; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }

        return dummy.next;
    }
}
