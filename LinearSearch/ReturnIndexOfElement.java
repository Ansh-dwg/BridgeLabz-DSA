public class ReturnIndexOfElement {
    // Create a method to perform linear search and return the index of the element
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Return the index if the element is found
            }
        }
        return -1; // Return -1 if the element is not found
    }

    public static void main(String[] args) {
        // Create an array to search
        int[] numbers = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };
        int target = 70;

        // Call the linearSearch method
        int resultIndex = linearSearch(numbers, target);

        // Display the result
        if (resultIndex != -1) {
            System.out.println("Element " + target + " found at index: " + resultIndex);
        } else {
            System.out.println("Element " + target + " not found in the array.");
        }
    }
}