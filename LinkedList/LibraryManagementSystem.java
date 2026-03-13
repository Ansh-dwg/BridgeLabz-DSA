public class LibraryManagementSystem {

    static class Book {
        int bookId;
        String title, author, genre;
        boolean available;
        Book next, prev;

        Book(int bookId, String title, String author, String genre, boolean available) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.available = available;
        }
    }

    static class Library {
        Book head, tail;
        int count;

        void addAtBeginning(int id, String title, String author, String genre, boolean available) {
            Book b = new Book(id, title, author, genre, available);
            if (head == null) { head = tail = b; count++; return; }
            b.next = head;
            head.prev = b;
            head = b;
            count++;
        }

        void addAtEnd(int id, String title, String author, String genre, boolean available) {
            Book b = new Book(id, title, author, genre, available);
            if (tail == null) { head = tail = b; count++; return; }
            tail.next = b;
            b.prev = tail;
            tail = b;
            count++;
        }

        void addAtPosition(int id, String title, String author, String genre, boolean available, int pos) {
            if (pos <= 1) { addAtBeginning(id, title, author, genre, available); return; }
            Book b = new Book(id, title, author, genre, available);
            Book curr = head;
            for (int i = 1; i < pos - 1 && curr != null; i++) curr = curr.next;
            if (curr == null || curr.next == null) { addAtEnd(id, title, author, genre, available); return; }
            b.next = curr.next;
            b.prev = curr;
            if (curr.next != null) curr.next.prev = b;
            curr.next = b;
            count++;
        }

        void removeByBookId(int id) {
            Book curr = head;
            while (curr != null && curr.bookId != id) curr = curr.next;
            if (curr == null) { System.out.println("Book not found."); return; }
            if (curr.prev != null) curr.prev.next = curr.next; else head = curr.next;
            if (curr.next != null) curr.next.prev = curr.prev; else tail = curr.prev;
            count--;
            System.out.println("Book " + id + " removed.");
        }

        void searchByTitle(String title) {
            Book curr = head;
            boolean found = false;
            while (curr != null) {
                if (curr.title.equalsIgnoreCase(title)) {
                    printBook(curr); found = true;
                }
                curr = curr.next;
            }
            if (!found) System.out.println("Not found.");
        }

        void searchByAuthor(String author) {
            Book curr = head;
            boolean found = false;
            while (curr != null) {
                if (curr.author.equalsIgnoreCase(author)) {
                    printBook(curr); found = true;
                }
                curr = curr.next;
            }
            if (!found) System.out.println("Not found.");
        }

        void updateAvailability(int id, boolean status) {
            Book curr = head;
            while (curr != null) {
                if (curr.bookId == id) { curr.available = status; System.out.println("Status updated."); return; }
                curr = curr.next;
            }
            System.out.println("Book not found.");
        }

        void displayForward() {
            Book curr = head;
            System.out.println("ID | Title | Author | Genre | Available");
            while (curr != null) { printBook(curr); curr = curr.next; }
        }

        void displayReverse() {
            Book curr = tail;
            System.out.println("ID | Title | Author | Genre | Available (Reverse)");
            while (curr != null) { printBook(curr); curr = curr.prev; }
        }

        void printBook(Book b) {
            System.out.println(b.bookId + " | " + b.title + " | " + b.author + " | " + b.genre + " | " + (b.available ? "Yes" : "No"));
        }

        void countBooks() { System.out.println("Total Books: " + count); }
    }

    public static void main(String[] args) {
        Library lib = new Library();
        lib.addAtEnd(1, "Clean Code", "Robert Martin", "Tech", true);
        lib.addAtEnd(2, "DDIA", "Martin Kleppmann", "Tech", true);
        lib.addAtEnd(3, "The Pragmatic Programmer", "Andrew Hunt", "Tech", false);
        lib.addAtBeginning(4, "Refactoring", "Martin Fowler", "Tech", true);
        lib.addAtPosition(5, "Design Patterns", "GoF", "Tech", true, 3);

        System.out.println("=== Forward Display ===");
        lib.displayForward();

        System.out.println("\n=== Reverse Display ===");
        lib.displayReverse();

        System.out.println("\n=== Search by Author: Martin Fowler ===");
        lib.searchByAuthor("Martin Fowler");

        System.out.println("\n=== Update Availability of Book 2 ===");
        lib.updateAvailability(2, false);

        System.out.println("\n=== Remove Book 3 ===");
        lib.removeByBookId(3);

        System.out.println("\n=== Total Count ===");
        lib.countBooks();

        System.out.println("\n=== Final Display ===");
        lib.displayForward();
    }
}