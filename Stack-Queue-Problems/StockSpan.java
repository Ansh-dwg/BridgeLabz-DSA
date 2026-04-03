import java.util.Arrays;
import java.util.Stack;

public class StockSpan {
    static int[] stockSpan(int[] prices) {
        int n = prices.length;
        int[] span = new int[n];
        Stack<Integer> stack = new Stack<>(); // stores indices

        for (int i = 0; i < n; i++) {
            // Pop elements smaller than or equal to current price
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[i])
                stack.pop();

            // If stack empty, all prev elements are smaller
            span[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }
        return span;
    }

    public static void main(String[] args) {
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        System.out.println("Prices: " + Arrays.toString(prices));
        System.out.println("Spans:  " + Arrays.toString(stockSpan(prices)));
        // Output: [1, 1, 1, 2, 1, 4, 6]
    }
}