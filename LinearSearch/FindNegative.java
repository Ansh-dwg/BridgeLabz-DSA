class FindNegative{
    public static int FindNegativeIndex(int arr[]){
        for(int i=0;i<arr.length;i++){
            if(arr[i]<0){
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int arr[] = {10,20,30, 0,50,-60,70,80,90,100}; 
        int result = FindNegativeIndex(arr);
        if(result != -1){
            System.out.println("First negative element found at index: " + result);
        } else {
            System.out.println(-1);
        }
        
    }
}