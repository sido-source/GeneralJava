package sort.mergeSort.node;


// tags: #NODE, #SORT, LINKED LIST, MERGE SORT, FAST AND SLOW POINTER, CONQUEROR, RECURSION
// tags: repeat, important
// made by Logan138
//148. Sort List

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(){}
}

public class SortList {

    // [2,1,3,6,5]
    // [4,2,3,1]
    public ListNode sortList(ListNode head) {

        // Base case: if the list is empty or has only one node, it's already sorted
        if (head == null || head.next == null) {
            return head;
        }

        // Step 1: Split the linked list into two halves using the fast-slow pointer technique
        ListNode fast = head;
        ListNode slow = head;

        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // Step 1.1: set the left and right start nodes
        ListNode left = head;
        ListNode right = slow.next;
        slow.next = null;                // Disconnect the left half from the right half !!!!


        // Step 2: sort
        ListNode leftSorted = sortList(left);
        ListNode rightSorted = sortList(right);

        // merge
        return mergeListNodes(leftSorted, rightSorted);

    }

    public ListNode mergeListNodes(ListNode left, ListNode right) {

        // prepare response
        ListNode head = new ListNode(0);
        // Create a dummy node to act as the head of the merged list
        ListNode curr = head;  // Start curr as head, not head.next

        // Merge all elements till right or left list is null
        while (right != null && left != null) {
            if (right.val > left.val) {
                curr.next = left;  // Set curr.next, not curr
                left = left.next;
            } else {
                curr.next = right;  // Set curr.next, not curr
                right = right.next;
            }
            curr = curr.next;  // Move curr to the next node
        }

        // At this point, one of the lists is null
        // Append the remaining nodes from the non-null list
        if (left != null) {
            curr.next = left;
        } else {
            curr.next = right;
        }

        return head.next;
    }
}
