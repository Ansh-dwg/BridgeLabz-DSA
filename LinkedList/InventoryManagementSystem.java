public class InventoryManagementSystem {

    static class Item {
        int itemId, quantity;
        String itemName;
        double price;
        Item next;

        Item(int itemId, String itemName, int quantity, double price) {
            this.itemId = itemId;
            this.itemName = itemName;
            this.quantity = quantity;
            this.price = price;
        }
    }

    static class InventoryList {
        Item head;

        void addAtBeginning(int id, String name, int qty, double price) {
            Item item = new Item(id, name, qty, price);
            item.next = head;
            head = item;
        }

        void addAtEnd(int id, String name, int qty, double price) {
            Item item = new Item(id, name, qty, price);
            if (head == null) { head = item; return; }
            Item curr = head;
            while (curr.next != null) curr = curr.next;
            curr.next = item;
        }

        void addAtPosition(int id, String name, int qty, double price, int pos) {
            if (pos <= 1) { addAtBeginning(id, name, qty, price); return; }
            Item item = new Item(id, name, qty, price);
            Item curr = head;
            for (int i = 1; i < pos - 1 && curr != null; i++) curr = curr.next;
            if (curr == null) { addAtEnd(id, name, qty, price); return; }
            item.next = curr.next;
            curr.next = item;
        }

        void removeByItemId(int id) {
            if (head == null) { System.out.println("List is empty."); return; }
            if (head.itemId == id) { head = head.next; System.out.println("Item " + id + " removed."); return; }
            Item curr = head;
            while (curr.next != null && curr.next.itemId != id) curr = curr.next;
            if (curr.next == null) { System.out.println("Item not found."); return; }
            curr.next = curr.next.next;
            System.out.println("Item " + id + " removed.");
        }

        void updateQuantity(int id, int newQty) {
            Item curr = head;
            while (curr != null) {
                if (curr.itemId == id) { curr.quantity = newQty; System.out.println("Quantity updated."); return; }
                curr = curr.next;
            }
            System.out.println("Item not found.");
        }

        void searchById(int id) {
            Item curr = head;
            while (curr != null) {
                if (curr.itemId == id) {
                    System.out.println("Found: [" + curr.itemId + "] " + curr.itemName + " Qty: " + curr.quantity + " Price: " + curr.price);
                    return;
                }
                curr = curr.next;
            }
            System.out.println("Item not found.");
        }

        void searchByName(String name) {
            Item curr = head;
            boolean found = false;
            while (curr != null) {
                if (curr.itemName.equalsIgnoreCase(name)) {
                    System.out.println("Found: [" + curr.itemId + "] " + curr.itemName + " Qty: " + curr.quantity + " Price: " + curr.price);
                    found = true;
                }
                curr = curr.next;
            }
            if (!found) System.out.println("Item not found.");
        }

        void totalInventoryValue() {
            double total = 0;
            Item curr = head;
            while (curr != null) { total += curr.price * curr.quantity; curr = curr.next; }
            System.out.printf("Total Inventory Value: %.2f%n", total);
        }

        Item mergeSort(Item head) {
            if (head == null || head.next == null) return head;
            Item mid = getMiddle(head);
            Item nextOfMid = mid.next;
            mid.next = null;
            Item left = mergeSort(head);
            Item right = mergeSort(nextOfMid);
            return merge(left, right);
        }

        Item getMiddle(Item head) {
            Item slow = head, fast = head.next;
            while (fast != null && fast.next != null) { slow = slow.next; fast = fast.next.next; }
            return slow;
        }

        Item merge(Item a, Item b) {
            if (a == null) return b;
            if (b == null) return a;
            if (a.itemName.compareToIgnoreCase(b.itemName) <= 0) { a.next = merge(a.next, b); return a; }
            else { b.next = merge(a, b.next); return b; }
        }

        void sortByName() { head = mergeSort(head); System.out.println("Sorted by Name."); }

        void sortByPrice(boolean ascending) {
            if (head == null) return;
            boolean swapped;
            do {
                swapped = false;
                Item curr = head;
                while (curr.next != null) {
                    boolean condition = ascending ? curr.price > curr.next.price : curr.price < curr.next.price;
                    if (condition) {
                        int ti = curr.itemId; String tn = curr.itemName; int tq = curr.quantity; double tp = curr.price;
                        curr.itemId = curr.next.itemId; curr.itemName = curr.next.itemName;
                        curr.quantity = curr.next.quantity; curr.price = curr.next.price;
                        curr.next.itemId = ti; curr.next.itemName = tn; curr.next.quantity = tq; curr.next.price = tp;
                        swapped = true;
                    }
                    curr = curr.next;
                }
            } while (swapped);
            System.out.println("Sorted by Price " + (ascending ? "Ascending" : "Descending") + ".");
        }

        void display() {
            if (head == null) { System.out.println("Inventory empty."); return; }
            Item curr = head;
            System.out.println("ID | Name | Qty | Price");
            while (curr != null) {
                System.out.printf("%d | %s | %d | %.2f%n", curr.itemId, curr.itemName, curr.quantity, curr.price);
                curr = curr.next;
            }
        }
    }

    public static void main(String[] args) {
        InventoryList list = new InventoryList();
        list.addAtEnd(1, "Laptop", 10, 75000);
        list.addAtEnd(2, "Mouse", 50, 500);
        list.addAtEnd(3, "Keyboard", 30, 1200);
        list.addAtBeginning(4, "Monitor", 15, 12000);
        list.addAtPosition(5, "Headset", 20, 3000, 3);

        System.out.println("=== Inventory ===");
        list.display();

        System.out.println("\n=== Total Value ===");
        list.totalInventoryValue();

        System.out.println("\n=== Search by ID 3 ===");
        list.searchById(3);

        System.out.println("\n=== Update Qty of ID 2 ===");
        list.updateQuantity(2, 100);

        System.out.println("\n=== Sort by Name ===");
        list.sortByName();
        list.display();

        System.out.println("\n=== Sort by Price Descending ===");
        list.sortByPrice(false);
        list.display();

        System.out.println("\n=== Remove Item ID 3 ===");
        list.removeByItemId(3);
        list.display();
    }
}