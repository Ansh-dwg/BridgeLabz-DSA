import java.util.Stack;

public class SortStack {
    // Insert element at correct sorted position
    static void insertSorted(Stack<Integer> stack, int element) {
        if (stack.isEmpty() || stack.peek() <= element) {
            stack.push(element);
            return;
        }
        int top = stack.pop();
        insertSorted(stack, element);
        stack.push(top);
    }

    // Recursively sort the stack
    static void sortStack(Stack<Integer> stack) {
        if (stack.isEmpty()) return;
        int top = stack.pop();
        sortStack(stack);
        insertSorted(stack, top);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6};
        for (int x : arr) stack.push(x);

        System.out.println("Before: " + stack);
        sortStack(stack);
        System.out.println("After:  " + stack); // [1, 1, 2, 3, 4, 5, 6, 9]
    }
}