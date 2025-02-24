package singlyLinkedList;

public class MySinglyLinkedList {
    MySinglyLinkedList next;
    int value;

    public MySinglyLinkedList(int value) {
        this.value = value;
    }

    public MySinglyLinkedList(MySinglyLinkedList linkedList, int value) {
        this.next = linkedList;
        this.value = value;
    }

    public static void main(String[] args) {
        MySinglyLinkedList mySinglyLinkedList = new MySinglyLinkedList(2);
        mySinglyLinkedList.next = new MySinglyLinkedList(3);
        mySinglyLinkedList.next.next = new MySinglyLinkedList(4);

        displayLinkedList(mySinglyLinkedList);
        displayLinkedList(reverseLinkedList(mySinglyLinkedList));
    }


    /*
    Time Complexity: O(n), where n is the number of nodes in the linked list. We only make one pass through the list.
    Space Complexity: O(1), because we are using only a constant amount of extra space.
     */
    public static MySinglyLinkedList reverseLinkedList( MySinglyLinkedList list ) {
        MySinglyLinkedList prev = null;
        MySinglyLinkedList current = list;
        MySinglyLinkedList nextNode = null;

        while( current != null ) {
            // Store next node
            nextNode = current.next;
            // current next element
            current.next = prev;

            prev = current;
            current = nextNode;
        }

        return prev;  // return the new head of the reversed list
    }

    public static void displayLinkedList(MySinglyLinkedList linkedList) {

        System.out.print("HEAD | -> ");
        while (linkedList != null) {
            System.out.print("| " + linkedList.value + " | -> ");
            linkedList = linkedList.next;
        }
        System.out.println("| TAIL");

        System.out.println("\n");
    }
}
