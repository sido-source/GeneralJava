package singlyLinkedList;

public class SlowFastPointers {

    public static void main(String[] args) {

        // how to find the middle of the linkedList ?

        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(5);

        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(4);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(5);
        head2.next.next.next.next = new ListNode(8);


        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        System.out.println("Slow elem value: " + slow.val);
        try {
            System.out.println("Fast pointer: " + fast.val);
        } catch (Exception e) {
            System.out.println("Fast pointer reached the end of the list and it is NULL.");
        }

        System.out.println("The second part of the array begins with : " + slow.next.val);


         slow = head;
         fast = head;

        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        System.out.println("Last elem: " + slow.val);
        try {
            System.out.println("Fast pointer: " + fast.val);
        } catch (Exception e) {
            System.out.println("Fast pointer reached the end of the list and it is NULL.");
        }

    }
}
