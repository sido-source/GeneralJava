package singlyLinkedList;

public class Sorting {

    // there are 2 ways
    // brute force o(n^2) and merge sort o(n logn)

    public static void main(String[] args) {
        ListNode head = new ListNode(5);
        ListNode head1 = new ListNode(2, head);
        ListNode head2 = new ListNode(4, head1);
        ListNode head3 = new ListNode(1, head2);

    }


    // brute force
    /*
    Steps:

    Create a dummy node: This dummy node will act as the head of the new sorted list.
    Iterate through the original list: Start with the first node (head) and iterate through each node.
    For each node, detach it from the original list (by keeping track of head.next) and then insert it into its proper position in the new sorted list.
    Find the insertion point: To insert the current node into the new sorted list, traverse the new list starting from the dummy node and find the correct position where the current node should be inserted.
    Insert the node: Once you find the correct position, insert the node into the sorted list.
    Repeat: Continue this process for every node in the original list until all nodes are sorted and added to the new list.
    Return the sorted list: After all nodes are inserted, the list starting from dummy.next is the sorted list.
     */

    public static ListNode sort(ListNode head) {
        ListNode res = new ListNode(0);


        while (head != null) {

            // detach head from rest of the list (next elements)
            ListNode restList = head.next;
            head.next = null;

            insertHeadToRes(head, res);

            // continue loop
            head = restList;
        }
    }

    private static void insertHeadToRes(ListNode head, ListNode res) {

        while (res != null) {
            if (res.val > head.val) {
                // again detach to do insertion
                ListNode restSortedList = res.next;
                res.val
            }

            res = res.next;
        }
    }
}
