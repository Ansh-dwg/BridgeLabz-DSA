class RotationPoint{
    public static int findRotationPoint(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        
        if (arr[left] < arr[right]) {
            return 0;
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] > arr[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    public static void main(String[] args) {
        int[] arr1 = {4, 5, 6, 7, 0, 1, 2};
        int index1 = findRotationPoint(arr1);
        System.out.println("Array: " + java.util.Arrays.toString(arr1));
        System.out.println("Rotation point index: " + index1 + ", Value: " + arr1[index1]);
        System.out.println();
    }
}