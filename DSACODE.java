import java.util.*;

// HumanBook class
class HumanBook {
    String name;
    String role;
    String chapterTitle;
    int cost;

    HumanBook(String name, String role, String chapterTitle, int cost) {
        this.name = name;
        this.role = role;
        this.chapterTitle = chapterTitle;
        this.cost = cost;
    }

    public String toString() {
        return name + " (" + role + ") - " + chapterTitle + " [" + cost + " Coins]";
    }
}

// Linked List Node (CO2)
class LegacyNode {
    String menteeName;
    LegacyNode next;

    LegacyNode(String name) {
        menteeName = name;
        next = null;
    }
}

public class DSACODE {

    // CO4 HashMap
    static HashMap<String, HumanBook> catalog = new HashMap<>();

    // CO3 Stack
    static Stack<HumanBook> history = new Stack<>();

    // CO3 Queue
    static Queue<String> waitlist = new LinkedList<>();

    // CO2 Linked List
    static LegacyNode head = null;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        System.out.println("===== MARKETPLACE OF HUMAN WISDOM =====");

        do {

            System.out.println("\n------ MENU ------");
            System.out.println("1. Add Human Book");
            System.out.println("2. Display Books (Sorted by Cost)");
            System.out.println("3. Search Author");
            System.out.println("4. View Profile");
            System.out.println("5. Show Viewing History");
            System.out.println("6. Join Waitlist");
            System.out.println("7. Process Waitlist");
            System.out.println("8. Add Mentee to Legacy Tree");
            System.out.println("9. Display Legacy Tree");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addBook();
                    break;

                case 2:
                    displaySortedBooks();
                    break;

                case 3:
                    searchAuthor();
                    break;

                case 4:
                    viewProfile();
                    break;

                case 5:
                    showHistory();
                    break;

                case 6:
                    joinWaitlist();
                    break;

                case 7:
                    processWaitlist();
                    break;

                case 8:
                    addMentee();
                    break;

                case 9:
                    displayLegacy();
                    break;

                case 0:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

    }

    // Add Book (HashMap)
    static void addBook() {

        System.out.print("Enter Author Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Role: ");
        String role = sc.nextLine();

        System.out.print("Enter Chapter Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Cost: ");
        int cost = sc.nextInt();
        sc.nextLine();

        HumanBook book = new HumanBook(name, role, title, cost);
        catalog.put(name, book);

        System.out.println("Book added successfully.");
    }

    // Bubble Sort (CO1)
    static void displaySortedBooks() {

        if (catalog.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        List<HumanBook> list = new ArrayList<>(catalog.values());

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {

                if (list.get(j).cost > list.get(j + 1).cost) {

                    HumanBook temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);

                }
            }
        }

        System.out.println("\nBooks Sorted by Cost:");
        for (HumanBook b : list) {
            System.out.println(b);
        }
    }

    // Search Author
    static void searchAuthor() {

        System.out.print("Enter Author Name: ");
        String name = sc.nextLine();

        if (catalog.containsKey(name)) {
            System.out.println("Author Found:");
            System.out.println(catalog.get(name));
        } else {
            System.out.println("Author not found.");
        }
    }

    // Stack
    static void viewProfile() {

        System.out.print("Enter Author Name: ");
        String name = sc.nextLine();

        if (catalog.containsKey(name)) {

            HumanBook book = catalog.get(name);
            history.push(book);

            System.out.println("Viewing Profile:");
            System.out.println(book);

        } else {
            System.out.println("Author not found.");
        }
    }

    // Show Stack History
    static void showHistory() {

        if (history.isEmpty()) {
            System.out.println("No viewing history.");
            return;
        }

        System.out.println("Viewing History:");

        for (int i = history.size() - 1; i >= 0; i--) {
            System.out.println(history.get(i).name);
        }
    }

    // Queue
    static void joinWaitlist() {

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        waitlist.add(name);

        System.out.println(name + " added to waitlist.");
    }

    static void processWaitlist() {

        if (waitlist.isEmpty()) {
            System.out.println("Waitlist is empty.");
            return;
        }

        String next = waitlist.poll();

        System.out.println("Connecting mentor with: " + next);
    }

    // Linked List
    static void addMentee() {

        System.out.print("Enter mentee name: ");
        String name = sc.nextLine();

        LegacyNode newNode = new LegacyNode(name);

        if (head == null) {
            head = newNode;
        } else {

            LegacyNode temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = newNode;
        }

        System.out.println("Mentee added.");
    }

    static void displayLegacy() {

        if (head == null) {
            System.out.println("Legacy tree is empty.");
            return;
        }

        LegacyNode temp = head;

        System.out.print("You");

        while (temp != null) {
            System.out.print(" -> " + temp.menteeName);
            temp = temp.next;
        }

        System.out.println();
    }
}
