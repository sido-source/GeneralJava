package twoPointers;

public class StackUsingArray<E> {

    private int top = 0; // Tracks the next available position in the stack
    private int capacity;
    private E[] container;

    @SuppressWarnings("unchecked")
    public StackUsingArray(int capacity) {
        this.capacity = capacity;
        container = (E[]) new Object[capacity]; // Unchecked cast
    }

    public E pop() {
        if (top <= 0) {
            return null; // Stack underflow
        }
        top--;
        return container[top];
    }

    public boolean push(E element) {
        if (top >= capacity) { // Ensure top doesn't exceed capacity
            return false; // Stack overflow
        }
        container[top++] = element;
        return true;
    }

    public int size() {
        return top; // Returns the current size of the stack
    }

    public boolean isEmpty() {
        return top == 0; // Stack is empty when top is 0
    }

    public static void main(String[] args) {
        StackUsingArray<Integer> stack = new StackUsingArray<>(2);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.pop();
        stack.pop();
    }
}
