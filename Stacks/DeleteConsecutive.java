// delete consecutive same words in sequence if : input 1 = ['ab','aa','aa','ab'], the output will be 0.
//example 2 : input 2 = ['ab','aa','aa','ab','ab'], the output will be 1.
import java.util.Stack;
public class DeleteConsecutive {
    public static void main(String[] args) {
        String[] input = {"ab","aa","aa","ab","ab"};
        Stack<String> stack = new Stack<>();
        for(String word : input){
            if(!stack.isEmpty() && stack.peek().equals(word)){
                stack.pop();
            } else {                
                stack.push(word); 
            }
        }
        System.out.println(stack.size());
    }
}