package twoPointers;



    // container - array
    // used, capacity
    // two indexes front and back
    // pop - front has to be updated
    // add - back has to be updated
    // circular movement (back/front + 1) % capacity = > frontend 0 - > 1 -> 2 -> 0
    //
    // front = 1 , back = 2, capacity = 2
    // we pop so => front = 2, back = 2
    // we push so => back = (2 + 1) % 2

    public class FifoUsingArray<E> {

        private E[] container;
        private int capacity;
        private int used = 0;
        private int frontIdx = 0;
        private int endIdx = -1;


        @SuppressWarnings("unchecked")
        public FifoUsingArray(int capacity) {
            this.capacity = capacity;
            container = (E[]) new Object[capacity]; // Generic array creation workaround
        }

        public boolean enqueue(E element) {
            if (used >= capacity) {
                return false; // Queue is full
            }
            endIdx = (endIdx + 1) % capacity; // Update end index
            container[endIdx] = element;
            used++; // Increment the count of used slots
            return true;
        }

        public E dequeue() {
            if (used == 0) {
                throw new IllegalStateException("Queue is empty"); // Handle empty queue
            }
            E element = container[frontIdx];
            frontIdx = (frontIdx + 1) % capacity; // Update front index
            used--; // Decrement the count of used slots
            return element;
        }

        public void main(String[] args) {
            FifoUsingArray<Integer> queue = new FifoUsingArray<>(2);
            queue.enqueue(2);
            queue.enqueue(3);
            System.out.println(queue.dequeue());
            queue.enqueue(4);
            System.out.println(queue.dequeue());
            System.out.println(queue.dequeue());
        }
    }
