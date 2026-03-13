import java.util.ArrayList;
import java.util.List;

public class SocialMediaFriendConnections {

    static class User {
        int userId, age;
        String name;
        List<Integer> friendIds;
        User next;

        User(int userId, String name, int age) {
            this.userId = userId;
            this.name = name;
            this.age = age;
            this.friendIds = new ArrayList<>();
        }
    }

    static class SocialNetwork {
        User head;

        void addUser(int id, String name, int age) {
            User u = new User(id, name, age);
            u.next = head;
            head = u;
        }

        User findUserById(int id) {
            User curr = head;
            while (curr != null) {
                if (curr.userId == id) return curr;
                curr = curr.next;
            }
            return null;
        }

        void addFriend(int userId1, int userId2) {
            User u1 = findUserById(userId1);
            User u2 = findUserById(userId2);
            if (u1 == null || u2 == null) { System.out.println("One or both users not found."); return; }
            if (!u1.friendIds.contains(userId2)) u1.friendIds.add(userId2);
            if (!u2.friendIds.contains(userId1)) u2.friendIds.add(userId1);
            System.out.println(u1.name + " and " + u2.name + " are now friends.");
        }

        void removeFriend(int userId1, int userId2) {
            User u1 = findUserById(userId1);
            User u2 = findUserById(userId2);
            if (u1 == null || u2 == null) { System.out.println("One or both users not found."); return; }
            u1.friendIds.remove(Integer.valueOf(userId2));
            u2.friendIds.remove(Integer.valueOf(userId1));
            System.out.println("Friendship removed between " + u1.name + " and " + u2.name);
        }

        void findMutualFriends(int userId1, int userId2) {
            User u1 = findUserById(userId1);
            User u2 = findUserById(userId2);
            if (u1 == null || u2 == null) { System.out.println("One or both users not found."); return; }
            List<Integer> mutual = new ArrayList<>(u1.friendIds);
            mutual.retainAll(u2.friendIds);
            if (mutual.isEmpty()) { System.out.println("No mutual friends."); return; }
            System.out.print("Mutual friends of " + u1.name + " and " + u2.name + ": ");
            for (int id : mutual) {
                User mu = findUserById(id);
                if (mu != null) System.out.print(mu.name + " ");
            }
            System.out.println();
        }

        void displayFriends(int userId) {
            User u = findUserById(userId);
            if (u == null) { System.out.println("User not found."); return; }
            System.out.print("Friends of " + u.name + ": ");
            for (int id : u.friendIds) {
                User f = findUserById(id);
                if (f != null) System.out.print(f.name + " ");
            }
            System.out.println();
        }

        void searchByName(String name) {
            User curr = head;
            boolean found = false;
            while (curr != null) {
                if (curr.name.equalsIgnoreCase(name)) {
                    System.out.println("Found: ID=" + curr.userId + " Name=" + curr.name + " Age=" + curr.age);
                    found = true;
                }
                curr = curr.next;
            }
            if (!found) System.out.println("User not found.");
        }

        void friendCount(int userId) {
            User u = findUserById(userId);
            if (u == null) { System.out.println("User not found."); return; }
            System.out.println(u.name + " has " + u.friendIds.size() + " friend(s).");
        }

        void displayAllUsers() {
            User curr = head;
            System.out.println("ID | Name | Age | #Friends");
            while (curr != null) {
                System.out.println(curr.userId + " | " + curr.name + " | " + curr.age + " | " + curr.friendIds.size());
                curr = curr.next;
            }
        }
    }

    public static void main(String[] args) {
        SocialNetwork sn = new SocialNetwork();
        sn.addUser(1, "Alice", 22);
        sn.addUser(2, "Bob", 25);
        sn.addUser(3, "Charlie", 23);
        sn.addUser(4, "Diana", 21);
        sn.addUser(5, "Eve", 24);

        System.out.println("=== Add Friends ===");
        sn.addFriend(1, 2);
        sn.addFriend(1, 3);
        sn.addFriend(2, 3);
        sn.addFriend(2, 4);
        sn.addFriend(3, 5);

        System.out.println("\n=== All Users ===");
        sn.displayAllUsers();

        System.out.println("\n=== Alice's Friends ===");
        sn.displayFriends(1);

        System.out.println("\n=== Mutual Friends: Alice & Bob ===");
        sn.findMutualFriends(1, 2);

        System.out.println("\n=== Search by Name: Charlie ===");
        sn.searchByName("Charlie");

        System.out.println("\n=== Friend Count: Bob ===");
        sn.friendCount(2);

        System.out.println("\n=== Remove Friendship: Alice & Bob ===");
        sn.removeFriend(1, 2);
        sn.displayFriends(1);
    }
}