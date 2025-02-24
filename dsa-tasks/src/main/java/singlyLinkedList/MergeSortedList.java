package singlyLinkedList;

public class MergeSortedList {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(4);

        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(3);
        node1.next.next = new ListNode(4);

        ListNode listNode = mergeTwoLists(node, node1);
    }


    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode res = new ListNode(0);
        ListNode curr = res;


        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        while(list1!=null && list2!=null) {
            if (list1.val < list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }

            curr = curr.next;
        }

        if (list1 != null) curr.next = list1;
        if (list2 != null) curr.next = list2;

        return res.next;
    }
}
