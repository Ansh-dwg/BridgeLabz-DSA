public class StudentRecordManagement {

    static class Student {
        int rollNumber;
        String name;
        int age;
        String grade;
        Student next;

        Student(int rollNumber, String name, int age, String grade) {
            this.rollNumber = rollNumber;
            this.name = name;
            this.age = age;
            this.grade = grade;
            this.next = null;
        }
    }

    static class StudentLinkedList {
        Student head;

        void addAtBeginning(int roll, String name, int age, String grade) {
            Student newStudent = new Student(roll, name, age, grade);
            newStudent.next = head;
            head = newStudent;
        }

        void addAtEnd(int roll, String name, int age, String grade) {
            Student newStudent = new Student(roll, name, age, grade);
            if (head == null) {
                head = newStudent;
                return;
            }
            Student curr = head;
            while (curr.next != null) curr = curr.next;
            curr.next = newStudent;
        }

        void addAtPosition(int roll, String name, int age, String grade, int pos) {
            if (pos <= 1) {
                addAtBeginning(roll, name, age, grade);
                return;
            }
            Student newStudent = new Student(roll, name, age, grade);
            Student curr = head;
            for (int i = 1; i < pos - 1 && curr != null; i++) curr = curr.next;
            if (curr == null) {
                addAtEnd(roll, name, age, grade);
                return;
            }
            newStudent.next = curr.next;
            curr.next = newStudent;
        }

        void deleteByRoll(int roll) {
            if (head == null) { System.out.println("List is empty."); return; }
            if (head.rollNumber == roll) { head = head.next; System.out.println("Deleted roll " + roll); return; }
            Student curr = head;
            while (curr.next != null && curr.next.rollNumber != roll) curr = curr.next;
            if (curr.next == null) { System.out.println("Student not found."); return; }
            curr.next = curr.next.next;
            System.out.println("Deleted roll " + roll);
        }

        Student searchByRoll(int roll) {
            Student curr = head;
            while (curr != null) {
                if (curr.rollNumber == roll) return curr;
                curr = curr.next;
            }
            return null;
        }

        void updateGrade(int roll, String newGrade) {
            Student s = searchByRoll(roll);
            if (s != null) { s.grade = newGrade; System.out.println("Grade updated for roll " + roll); }
            else System.out.println("Student not found.");
        }

        void display() {
            if (head == null) { System.out.println("No records."); return; }
            Student curr = head;
            System.out.println("Roll | Name | Age | Grade");
            while (curr != null) {
                System.out.println(curr.rollNumber + " | " + curr.name + " | " + curr.age + " | " + curr.grade);
                curr = curr.next;
            }
        }
    }

    public static void main(String[] args) {
        StudentLinkedList list = new StudentLinkedList();

        list.addAtEnd(101, "Alice", 20, "A");
        list.addAtEnd(102, "Bob", 21, "B");
        list.addAtEnd(103, "Charlie", 22, "C");
        list.addAtBeginning(100, "Zara", 19, "A+");
        list.addAtPosition(104, "Dave", 21, "B+", 3);

        System.out.println("=== All Students ===");
        list.display();

        System.out.println("\n=== Search Roll 102 ===");
        Student s = list.searchByRoll(102);
        if (s != null) System.out.println("Found: " + s.name + ", Grade: " + s.grade);

        System.out.println("\n=== Update Grade of Roll 102 ===");
        list.updateGrade(102, "A");

        System.out.println("\n=== Delete Roll 103 ===");
        list.deleteByRoll(103);

        System.out.println("\n=== Final List ===");
        list.display();
    }
}