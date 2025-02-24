package singlyLinkedList;

public class Reverse1 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode reversedList = reverseList(head);

    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        ListNode prev = null;

        while(head != null) {
            // detach curr element
            ListNode next = head.next;
            head.next = prev;

            prev = head;


            head = next;
        }

        return dummy.next;
    }
}
