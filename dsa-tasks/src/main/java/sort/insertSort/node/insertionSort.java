package sort.insertSort.node;

//record ListNode(int val, ListNode next) {}

// tags: #NODE, #SORT, LINKED LIST
// revise
//147. Insertion Sort List

// complexity o(n^2)

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
public class insertionSort {

    public static void main(String[] args) {

        ListNode node1 = new ListNode(6);
        ListNode node2 = new ListNode(5, node1);
        ListNode node3 = new ListNode(2, node2);
        ListNode node4 = new ListNode(3, node3);

        node1.next = node2;
        //insertionSortList(node1);
    }
    // problem is that we have one List and we have somehow ordered the elements
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode curr = head;

        while (curr != null) {
            // At each iteration, we insert an element into the resulting list.
            ListNode prev = dummy;

            // find the position to insert the current node
            while (prev.next != null && prev.next.val <= curr.val) {
                prev = prev.next;
            }

            ListNode next = curr.next;
            // insert the current node to the new list
            curr.next = prev.next;
            prev.next = curr;

            // moving on to the next iteration
            curr = next;
        }

        return dummy.next;
    }
}
