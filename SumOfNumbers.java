// Create SumOfDigit Class to compute the sum of all digits of a number using
// an array
import java.util.Scanner;
class SumOfNumbers {
public static void main(String[] args) {
// Create a Scanner Object
Scanner input = new Scanner(System.in);
// Take input for a number
System.out.print("Enter a number: ");
int number = input.nextInt();
// Validate the user input number, if negative state invalid and exit
if (number < 0) {
System.err.println("Invalid Number.");
System.exit(0);
}

// Find the count of digits in the number
int count = 0;
int temp = number;
while (temp > 0) {
count++;
temp /= 10;
}
// Find the digits in the number and save them in an array
int[] digits = new int[count];
for (int i = 0; i < count; i++) {
digits[i] = number % 10;
number /= 10;
}
// Find the sum of the digits of the number
int sum = 0;
for (int i = 0; i < count; i++) {
sum += digits[i];
}
// Display the sum of the digits of the number
System.out.println("\nSum of Digits: " + sum);
// Close the Scanner Object
input.close();
}
}