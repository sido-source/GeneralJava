package singlyLinkedList;

public class AddTwoReversedNumbers {

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);

        ListNode head2 = new ListNode(5);
        head2.next = new ListNode(6);
        head2.next.next = new ListNode(4);

        ListNode res = addTwoNumbers(head2, head);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);  // Dummy head of the result list
        ListNode current = dummy;  // Pointer to build the result list
        int carry = 0;  // Carry for sums over 9

        // Traverse both lists
        while (l1 != null || l2 != null || carry != 0) {
            int val1 = (l1 != null) ? l1.val : 0;  // Value from l1 or 0 if null
            int val2 = (l2 != null) ? l2.val : 0;  // Value from l2 or 0 if null
            int sum = val1 + val2 + carry;  // Calculate sum with carry

            carry = sum / 10;  // Update carry for the next iteration
            int digit = sum % 10;  // Get the current digit

            current.next = new ListNode(digit);  // Append new node to result list
            current = current.next;  // Move the pointer forward

            // Move to the next nodes in the input lists if they exist
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummy.next;  // The result list starts from dummy.next
    }
}
