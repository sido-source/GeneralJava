package singlyLinkedList;

public class ReverseList {

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(5);

        ListNode listNode = reverseList1(head);
    }

    public static ListNode reverseList(ListNode head) {
        // Base case: if head is null or there's only one node, return head
        if (head == null || head.next == null) {
            return head;
        }

        // Recursive case: reverse the rest of the list
        ListNode newHead = reverseList(head.next);

        // After reversing the rest, adjust the pointers
        head.next.next = head; // Make the next node point back to the current node
        head.next = null;      // Make the current node's next point to null (it becomes the new tail)

        return newHead; // Return the new head of the reversed list
    }

    public static ListNode reverseList1(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {

            ListNode next = curr.next; // Store the next node, because we will have to change it to prev

            curr.next = prev;          // Reverse the current node's pointer


            prev = curr;               // Move the `prev` pointer one step forward
            curr = next;               // Move the `curr` pointer one step forward
        }

        return prev; // `prev` will be the new head of the reversed list
    }
}
