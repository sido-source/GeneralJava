import java.util.*;

public class CustomList {
    // Node structure for doubly linked list
    private static class Node {
        int value;
        Node prev, next;

        Node(int value) {
            this.value = value;
        }
    }

    private Map<Integer, Node> indexMap;  // To quickly access the n-th element
    private Node head, tail;  // Head and tail of the doubly linked list
    private int size;  // Keeps track of the size of the list

    public CustomList() {
        this.indexMap = new HashMap<>();
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    // Add negative number at the end
    public void addLast(int value) {
        Node newNode = new Node(value);
        if (size == 0) {
            head = tail = newNode;  // First element
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;  // Move tail to the new last node
        }
        indexMap.put(size, newNode);  // Store in the index map
        size++;
    }

    // Remove the n-th element (1-based index), if valid
    public void removeAt(int index) {
        int zeroBasedIndex = index - 1;  // Convert 1-based to 0-based index
        if (zeroBasedIndex >= 0 && zeroBasedIndex < size) {
            Node nodeToRemove = indexMap.get(zeroBasedIndex);
            removeNode(nodeToRemove);
            reindex(zeroBasedIndex);  // Rebuild index map from the removal point onward
        }
    }

    // Remove a specific node from the doubly linked list
    private void removeNode(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;  // Update head if removing the first element
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;  // Update tail if removing the last element
        }
        size--;
    }

    // Rebuild the index map starting from the specified index
    private void reindex(int fromIndex) {
        Node current = indexMap.get(fromIndex).next;
        indexMap.remove(fromIndex);
        int index = fromIndex;
        while (current != null) {
            indexMap.put(index, current);
            current = current.next;
            index++;
        }
    }

    // Convert the custom list to a standard list
    public List<Integer> toList() {
        List<Integer> result = new ArrayList<>();
        Node current = head;
        while (current != null) {
            result.add(current.value);
            current = current.next;
        }
        return result;
    }

    public static List<Integer> processNumbers(int[] numbers) {
        CustomList customList = new CustomList();

        for (int number : numbers) {
            if (number < 0) {
                customList.addLast(number);  // Append negative number
            } else if (number > 0) {
                customList.removeAt(number);  // Remove n-th element
            }
            // Do nothing if the number is 0
        }
        //System.out.println(customList.toList());
        return customList.toList();
    }

}
