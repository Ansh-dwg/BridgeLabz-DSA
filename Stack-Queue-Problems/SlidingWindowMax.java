import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindowMax {
    static int[] slidingWindowMax(int[] arr, int k) {
        int n = arr.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>(); // stores indices

        for (int i = 0; i < n; i++) {
            // Remove elements outside the current window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1)
                deque.pollFirst();

            // Remove smaller elements from back (they're useless)
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[i])
                deque.pollLast();

            deque.offerLast(i);

            // Start recording results once first window is complete
            if (i >= k - 1)
                result[i - k + 1] = arr[deque.peekFirst()];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println("Array:  " + Arrays.toString(arr));
        System.out.println("k = " + k);
        System.out.println("Result: " + Arrays.toString(slidingWindowMax(arr, k)));
        // Output: [3, 3, 5, 5, 6, 7]
    }
}