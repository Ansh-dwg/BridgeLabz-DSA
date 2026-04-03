//sorting stack in ascending order using recursion 
import java.util.Stack;
public class SortingStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(1);
        stack.push(4);
        stack.push(2);
        sortStack(stack);
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
    public static void sortStack(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int top = stack.pop();
        sortStack(stack);
        insertAtBottom(stack, top);
    }
    public static void insertAtBottom(Stack<Integer> stack ,int value ){
        if(stack.isEmpty() || stack.peek() <= value){
            stack.push(value);
            return;
        }
        int top = stack.pop();
        insertAtBottom(stack, value);
        stack.push(top);
     }
}
