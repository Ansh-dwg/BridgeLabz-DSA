public class TaskScheduler {

    static class Task {
        int taskId, priority;
        String taskName, dueDate;
        Task next;

        Task(int taskId, String taskName, int priority, String dueDate) {
            this.taskId = taskId;
            this.taskName = taskName;
            this.priority = priority;
            this.dueDate = dueDate;
        }
    }

    static class CircularTaskList {
        Task head, current;
        int size;

        void addAtEnd(int id, String name, int priority, String dueDate) {
            Task t = new Task(id, name, priority, dueDate);
            if (head == null) {
                head = t;
                t.next = head;
                current = head;
            } else {
                Task tail = head;
                while (tail.next != head) tail = tail.next;
                tail.next = t;
                t.next = head;
            }
            size++;
        }

        void addAtBeginning(int id, String name, int priority, String dueDate) {
            Task t = new Task(id, name, priority, dueDate);
            if (head == null) { head = t; t.next = head; current = head; size++; return; }
            Task tail = head;
            while (tail.next != head) tail = tail.next;
            t.next = head;
            tail.next = t;
            head = t;
            size++;
        }

        void addAtPosition(int id, String name, int priority, String dueDate, int pos) {
            if (pos <= 1) { addAtBeginning(id, name, priority, dueDate); return; }
            Task t = new Task(id, name, priority, dueDate);
            Task curr = head;
            for (int i = 1; i < pos - 1 && curr.next != head; i++) curr = curr.next;
            t.next = curr.next;
            curr.next = t;
            if (t.next == head && pos > size) {}
            size++;
        }

        void removeByTaskId(int id) {
            if (head == null) { System.out.println("List is empty."); return; }
            if (head.taskId == id && size == 1) { head = null; current = null; size--; return; }
            Task tail = head;
            while (tail.next != head) tail = tail.next;
            if (head.taskId == id) {
                if (current == head) current = head.next;
                tail.next = head.next;
                head = head.next;
                size--;
                System.out.println("Task " + id + " removed.");
                return;
            }
            Task curr = head;
            while (curr.next != head && curr.next.taskId != id) curr = curr.next;
            if (curr.next == head) { System.out.println("Task not found."); return; }
            if (current == curr.next) current = curr.next.next;
            curr.next = curr.next.next;
            size--;
            System.out.println("Task " + id + " removed.");
        }

        void viewCurrentAndMoveNext() {
            if (current == null) { System.out.println("No tasks."); return; }
            System.out.println("Current: [" + current.taskId + "] " + current.taskName + " | Priority: " + current.priority);
            current = current.next;
        }

        void display() {
            if (head == null) { System.out.println("No tasks."); return; }
            Task curr = head;
            System.out.println("Tasks (circular):");
            do {
                System.out.println("[" + curr.taskId + "] " + curr.taskName + " | Priority: " + curr.priority + " | Due: " + curr.dueDate);
                curr = curr.next;
            } while (curr != head);
        }

        void searchByPriority(int priority) {
            if (head == null) { System.out.println("No tasks."); return; }
            Task curr = head;
            boolean found = false;
            do {
                if (curr.priority == priority) {
                    System.out.println("[" + curr.taskId + "] " + curr.taskName + " | Due: " + curr.dueDate);
                    found = true;
                }
                curr = curr.next;
            } while (curr != head);
            if (!found) System.out.println("No tasks with priority " + priority);
        }
    }

    public static void main(String[] args) {
        CircularTaskList list = new CircularTaskList();
        list.addAtEnd(1, "Design UI", 2, "2025-01-10");
        list.addAtEnd(2, "Write Tests", 1, "2025-01-12");
        list.addAtEnd(3, "Deploy App", 3, "2025-01-15");
        list.addAtBeginning(0, "Plan Sprint", 1, "2025-01-08");
        list.addAtPosition(4, "Code Review", 2, "2025-01-13", 3);

        System.out.println("=== All Tasks ===");
        list.display();

        System.out.println("\n=== View & Move (3 steps) ===");
        list.viewCurrentAndMoveNext();
        list.viewCurrentAndMoveNext();
        list.viewCurrentAndMoveNext();

        System.out.println("\n=== Search Priority 2 ===");
        list.searchByPriority(2);

        System.out.println("\n=== Remove Task 2 ===");
        list.removeByTaskId(2);

        System.out.println("\n=== Final Tasks ===");
        list.display();
    }
}