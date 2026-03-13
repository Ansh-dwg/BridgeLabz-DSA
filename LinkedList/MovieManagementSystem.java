public class MovieManagementSystem {

    static class Movie {
        String title, director;
        int year;
        double rating;
        Movie next, prev;

        Movie(String title, String director, int year, double rating) {
            this.title = title;
            this.director = director;
            this.year = year;
            this.rating = rating;
        }
    }

    static class MovieList {
        Movie head, tail;

        void addAtBeginning(String title, String director, int year, double rating) {
            Movie m = new Movie(title, director, year, rating);
            if (head == null) { head = tail = m; return; }
            m.next = head;
            head.prev = m;
            head = m;
        }

        void addAtEnd(String title, String director, int year, double rating) {
            Movie m = new Movie(title, director, year, rating);
            if (tail == null) { head = tail = m; return; }
            tail.next = m;
            m.prev = tail;
            tail = m;
        }

        void addAtPosition(String title, String director, int year, double rating, int pos) {
            if (pos <= 1) { addAtBeginning(title, director, year, rating); return; }
            Movie m = new Movie(title, director, year, rating);
            Movie curr = head;
            for (int i = 1; i < pos - 1 && curr != null; i++) curr = curr.next;
            if (curr == null || curr.next == null) { addAtEnd(title, director, year, rating); return; }
            m.next = curr.next;
            m.prev = curr;
            if (curr.next != null) curr.next.prev = m;
            curr.next = m;
        }

        void removeByTitle(String title) {
            Movie curr = head;
            while (curr != null && !curr.title.equalsIgnoreCase(title)) curr = curr.next;
            if (curr == null) { System.out.println("Movie not found."); return; }
            if (curr.prev != null) curr.prev.next = curr.next; else head = curr.next;
            if (curr.next != null) curr.next.prev = curr.prev; else tail = curr.prev;
            System.out.println("Removed: " + title);
        }

        void searchByDirector(String director) {
            Movie curr = head;
            boolean found = false;
            while (curr != null) {
                if (curr.director.equalsIgnoreCase(director)) {
                    System.out.println(curr.title + " (" + curr.year + ") - Rating: " + curr.rating);
                    found = true;
                }
                curr = curr.next;
            }
            if (!found) System.out.println("No movies found for director: " + director);
        }

        void searchByRating(double rating) {
            Movie curr = head;
            boolean found = false;
            while (curr != null) {
                if (curr.rating == rating) {
                    System.out.println(curr.title + " by " + curr.director);
                    found = true;
                }
                curr = curr.next;
            }
            if (!found) System.out.println("No movies with rating: " + rating);
        }

        void updateRating(String title, double newRating) {
            Movie curr = head;
            while (curr != null) {
                if (curr.title.equalsIgnoreCase(title)) { curr.rating = newRating; System.out.println("Rating updated."); return; }
                curr = curr.next;
            }
            System.out.println("Movie not found.");
        }

        void displayForward() {
            System.out.println("Forward:");
            Movie curr = head;
            while (curr != null) {
                System.out.println(curr.title + " | " + curr.director + " | " + curr.year + " | " + curr.rating);
                curr = curr.next;
            }
        }

        void displayReverse() {
            System.out.println("Reverse:");
            Movie curr = tail;
            while (curr != null) {
                System.out.println(curr.title + " | " + curr.director + " | " + curr.year + " | " + curr.rating);
                curr = curr.prev;
            }
        }
    }

    public static void main(String[] args) {
        MovieList list = new MovieList();
        list.addAtEnd("Inception", "Nolan", 2010, 8.8);
        list.addAtEnd("Interstellar", "Nolan", 2014, 8.6);
        list.addAtEnd("The Matrix", "Wachowski", 1999, 8.7);
        list.addAtBeginning("Dunkirk", "Nolan", 2017, 7.9);
        list.addAtPosition("Parasite", "Bong", 2019, 8.5, 3);

        System.out.println("=== Forward Display ===");
        list.displayForward();

        System.out.println("\n=== Reverse Display ===");
        list.displayReverse();

        System.out.println("\n=== Search by Director: Nolan ===");
        list.searchByDirector("Nolan");

        System.out.println("\n=== Update Rating of Inception ===");
        list.updateRating("Inception", 9.0);

        System.out.println("\n=== Remove The Matrix ===");
        list.removeByTitle("The Matrix");

        System.out.println("\n=== Final Forward Display ===");
        list.displayForward();
    }
}