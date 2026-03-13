public class TicketReservationSystem {

    static class Ticket {
        int ticketId, seatNumber;
        String customerName, movieName, bookingTime;
        Ticket next;

        Ticket(int ticketId, String customerName, String movieName, int seatNumber, String bookingTime) {
            this.ticketId = ticketId;
            this.customerName = customerName;
            this.movieName = movieName;
            this.seatNumber = seatNumber;
            this.bookingTime = bookingTime;
        }
    }

    static class TicketSystem {
        Ticket head;
        int count;

        void addTicket(int id, String customer, String movie, int seat, String time) {
            Ticket t = new Ticket(id, customer, movie, seat, time);
            if (head == null) {
                head = t;
                t.next = head;
                count++;
                System.out.println("Ticket " + id + " added.");
                return;
            }
            Ticket tail = head;
            while (tail.next != head) tail = tail.next;
            tail.next = t;
            t.next = head;
            count++;
            System.out.println("Ticket " + id + " added.");
        }

        void removeTicket(int id) {
            if (head == null) { System.out.println("No tickets."); return; }
            if (head.ticketId == id && count == 1) { head = null; count--; System.out.println("Ticket " + id + " removed."); return; }
            Ticket tail = head;
            while (tail.next != head) tail = tail.next;
            if (head.ticketId == id) {
                tail.next = head.next;
                head = head.next;
                count--;
                System.out.println("Ticket " + id + " removed.");
                return;
            }
            Ticket curr = head;
            while (curr.next != head && curr.next.ticketId != id) curr = curr.next;
            if (curr.next == head) { System.out.println("Ticket not found."); return; }
            curr.next = curr.next.next;
            count--;
            System.out.println("Ticket " + id + " removed.");
        }

        void display() {
            if (head == null) { System.out.println("No tickets."); return; }
            Ticket curr = head;
            System.out.println("ID | Customer | Movie | Seat | Booked At");
            do {
                System.out.println(curr.ticketId + " | " + curr.customerName + " | " + curr.movieName + " | " + curr.seatNumber + " | " + curr.bookingTime);
                curr = curr.next;
            } while (curr != head);
        }

        void searchByCustomer(String name) {
            if (head == null) { System.out.println("No tickets."); return; }
            Ticket curr = head;
            boolean found = false;
            do {
                if (curr.customerName.equalsIgnoreCase(name)) {
                    System.out.println("Found: Ticket " + curr.ticketId + " | Movie: " + curr.movieName + " | Seat: " + curr.seatNumber);
                    found = true;
                }
                curr = curr.next;
            } while (curr != head);
            if (!found) System.out.println("No ticket for customer: " + name);
        }

        void searchByMovie(String movie) {
            if (head == null) { System.out.println("No tickets."); return; }
            Ticket curr = head;
            boolean found = false;
            do {
                if (curr.movieName.equalsIgnoreCase(movie)) {
                    System.out.println("Found: Ticket " + curr.ticketId + " | Customer: " + curr.customerName + " | Seat: " + curr.seatNumber);
                    found = true;
                }
                curr = curr.next;
            } while (curr != head);
            if (!found) System.out.println("No tickets for movie: " + movie);
        }

        void totalTickets() { System.out.println("Total Booked Tickets: " + count); }
    }

    public static void main(String[] args) {
        TicketSystem system = new TicketSystem();

        system.addTicket(101, "Alice", "Inception", 12, "10:00 AM");
        system.addTicket(102, "Bob", "Interstellar", 7, "10:15 AM");
        system.addTicket(103, "Charlie", "Inception", 15, "10:30 AM");
        system.addTicket(104, "Diana", "The Matrix", 9, "10:45 AM");
        system.addTicket(105, "Eve", "Interstellar", 22, "11:00 AM");

        System.out.println("\n=== All Tickets ===");
        system.display();

        System.out.println("\n=== Search by Customer: Alice ===");
        system.searchByCustomer("Alice");

        System.out.println("\n=== Search by Movie: Interstellar ===");
        system.searchByMovie("Interstellar");

        System.out.println("\n=== Total Tickets ===");
        system.totalTickets();

        System.out.println("\n=== Remove Ticket 103 ===");
        system.removeTicket(103);

        System.out.println("\n=== Final Tickets ===");
        system.display();
        system.totalTickets();
    }
}