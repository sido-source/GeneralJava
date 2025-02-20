package dataStructureFactory.fifoByArray;

import java.util.Deque;
import java.util.Queue;

class MyFifo {

    // tags: circular array using (idx + 1 ) % capacity

    private int[] arr;       // Array to store elements
    private int capacity;    // Capacity of the queue
    public int frontIdx;    // Index of the front element
    public int backIdx;     // Index of the back element
    private int count;       // Current size of the queue

    // Constructor
    public MyFifo(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity];
        this.frontIdx = 0;
        this.backIdx = -1;
        this.count = 0;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return count == 0;
    }

    // Get the size of the queue
    public int size() {
        return count;
    }

    // Get the front element
    public int front() {
        if (isEmpty()) {
            return -1;
            //throw new RuntimeException("Queue is empty");
        }
        return arr[frontIdx];
    }

    // Get the back element
    public int back() {
        if (isEmpty()) {
            return -1;
            //throw new RuntimeException("Queue is empty");
        }
        return arr[backIdx];
    }

    // Push an element into the queue
    public void push(int element) {
        if (count == capacity) {
            throw new RuntimeException("Queue is full");
        }
        System.out.println("update backIdx");
        backIdx = (backIdx + 1) % capacity; // Circular increment
        arr[backIdx] = element;
        count++;
    }

    // Pop an element from the queue
    public void pop() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        System.out.println("update frontIdx");
        frontIdx = (frontIdx + 1) % capacity; // Circular increment
        count--;

    }



    // Main method to demonstrate functionality
    public static void main(String[] args) {
        MyFifo myFifo = new MyFifo(3);

        System.out.println("In FIFO the first pushed element is front and back is the last added");
        System.out.println("Queue created with capacity 10");
        printQueue(myFifo);

        myFifo.push(5);
        System.out.println("Pushed: 5");
        printQueue(myFifo);

        myFifo.push(6);
        System.out.println("Pushed: 6");
        printQueue(myFifo);

        myFifo.push(7);
        System.out.println("Pushed: 7");
        printQueue(myFifo);

        myFifo.pop();
        System.out.println("Popped");
        printQueue(myFifo);

        myFifo.push(5);
        System.out.println("Pushed: 5");
        printQueue(myFifo);

        myFifo.pop();
        System.out.println("Popped");
        printQueue(myFifo);

        myFifo.push(6);
        System.out.println("Pushed: 6");
        printQueue(myFifo);

        myFifo.pop();
        System.out.println("Popped");
        printQueue(myFifo);

        myFifo.pop();
        System.out.println("Popped");
        printQueue(myFifo);
    }

    // Helper method to print queue state
    private static void printQueue(MyFifo myFifo) {
        System.out.println("Front: " + myFifo.front());
        System.out.println("Back: " + myFifo.back());
        System.out.println("Is Empty: " + myFifo.isEmpty());
        System.out.println("Size: " + myFifo.size());
        System.out.println("F-index: " + myFifo.frontIdx + " B-index: " + myFifo.backIdx);
        System.out.println("");
    }
}

