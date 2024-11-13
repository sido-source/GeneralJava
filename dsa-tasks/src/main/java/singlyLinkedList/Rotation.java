    package singlyLinkedList;

public class Rotation {


        public static ListNode rotateRight(ListNode head, int k) {
            if (head == null || head.next == null || k == 0) {
                return head;
            }

            int length = 1;
            ListNode temp = head;


            while (temp.next != null) {
                temp = temp.next;
                length++;
            }

            temp.next = head;
            k = k % length;
            k = length - k;

            while (k-- > 0) {
                temp = temp.next;
            }

            head = temp.next;
            temp.next = null;

            return head;
        }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        rotateRight(listNode, 3);

    }
}
