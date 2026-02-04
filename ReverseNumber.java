import java.util.Scanner;
class ReverseNumber{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number");
        int rev =0 ;
        int n = sc.nextInt();
        while (n > 0) {
        rev = rev * 10 + (n % 10);
        n = n / 10;
        }
        System.out.println("Reversed Number is: " + rev);


    }
}