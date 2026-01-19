// Program to find all the occurrences of a character in a string
import java.util.Scanner;
class CharecterOccurance {
// Method to find all the index of a character in a string using charAt()
// method and return them in an array
public static int[] findAllIndexes(String text, char ch) {
// The count is used to find the number of occurrences of the character
int count = 0;
for (int i = 0; i < text.length(); i++) {
if (text.charAt(i) == ch) {
count++;
}
}
// Create an array to store the indexes of the character
int[] indexes = new int[count];
int j = 0;
for (int i = 0; i < text.length(); i++) {
if (text.charAt(i) == ch) {
indexes[j] = i;
j++;
}
}
return indexes;
}

public static void main(String[] args) {
// Take user input for Text and Character to check Occurrences
Scanner sc = new Scanner(System.in);
System.out.print("Enter a text: ");
String text = sc.nextLine();
System.out.print("Enter a character to find the occurrences: ");
char ch = sc.next().charAt(0);
// Find the occurrences of the character
int[] indexes = findAllIndexes(text, ch);
// Display the occurrences of the character
System.out.println("Indexes of the character '" + ch + "': ");
for (int i = 0; i < indexes.length; i++) {
System.out.print(indexes[i] + " ");
}
}
}