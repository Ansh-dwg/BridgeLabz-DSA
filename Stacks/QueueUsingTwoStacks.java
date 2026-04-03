/* Problem: Design a queue using two stacks such that enqueue and dequeue
operations are performed efficiently. 
Hint: Use one stack for enqueue and another stack for dequeue. Transfer
elements between stacks as needed. */
import java.util.Stack;

public class QueueUsingTwoStacks {
    
    private Stack<Integer> inStack  = new Stack<>();  // handles enqueue
    private Stack<Integer> outStack = new Stack<>();  // handles dequeue

    // O(1) always
    public void enqueue(int val) {
        inStack.push(val);
    }

    // Amortized O(1)
    public int dequeue() {
        if (outStack.isEmpty())
            transfer();
        if (outStack.isEmpty())
            throw new RuntimeException("Queue is empty");
        return outStack.pop();
    }

    // Amortized O(1)
    public int peek() {
        if (outStack.isEmpty())
            transfer();
        if (outStack.isEmpty())
            throw new RuntimeException("Queue is empty");
        return outStack.peek();
    }

    // O(1)
    public boolean isEmpty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    // O(1)
    public int size() {
        return inStack.size() + outStack.size();
    }

    // Transfer all elements from inStack → outStack (reverses order = FIFO)
    private void transfer() {
        while (!inStack.isEmpty())
            outStack.push(inStack.pop());
    }

    // ─── Demo ───────────────────────────────────────────────────────
    public static void main(String[] args) {
        QueueUsingTwoStacks q = new QueueUsingTwoStacks();

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);

        System.out.println("Peek   : " + q.peek());      // 10
        System.out.println("Dequeue: " + q.dequeue());   // 10
        System.out.println("Dequeue: " + q.dequeue());   // 20

        q.enqueue(40);
        q.enqueue(50);

        System.out.println("Size   : " + q.size());      // 3
        while (!q.isEmpty())
            System.out.println("Dequeue: " + q.dequeue()); // 30 → 40 → 50
    }
} 