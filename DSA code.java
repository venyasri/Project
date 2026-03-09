import java.util.*;

// 1. Basic Entity matching your HTML Cards
class HumanBook {
    String name;
    String role;
    String chapterTitle;
    int cost;

    public HumanBook(String name, String role, String chapterTitle, int cost) {
        this.name = name;
        this.role = role;
        this.chapterTitle = chapterTitle;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return name + " (" + role + ") - " + chapterTitle + " [" + cost + " Coins]";
    }
}

// 2. Node for Linked List (Topic 5: Singly Linked List)
class LegacyNode {
    String menteeName;
    LegacyNode next;

    public LegacyNode(String menteeName) {
        this.menteeName = menteeName;
        this.next = null;
    }
}

public class WisdomMarketplace {

    // Topic 11 & 12: Hashing Techniques
    private HashMap<String, HumanBook> bookCatalog = new HashMap<>();

    // Topic 8: Stack Implementation
    private Stack<HumanBook> viewingHistory = new Stack<>();

    // Topic 9: Queue Implementation
    private Queue<String> robertVanceWaitlist = new LinkedList<>();

    // Topic 5: Singly Linked List
    private LegacyNode legacyTreeHead;

    public static void main(String[] args) {
        WisdomMarketplace market = new WisdomMarketplace();
        market.initializeBooks();
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- WELCOME TO THE MARKETPLACE OF HUMAN WISDOM ---");

        boolean running = true;
        while (running) {
            System.out.println("\n================================================");
            System.out.println("                     MENU                       ");
            System.out.println("================================================");
            System.out.println("1. View Available Books (Sorted by Cost)");
            System.out.println("2. View a Book Profile");
            System.out.println("3. Show Viewing History");
            System.out.println("4. Join Waitlist for Robert Vance");
            System.out.println("5. Process Waitlist");
            System.out.println("6. Add Mentee to Legacy Tree");
            System.out.println("7. Display Legacy Tree");
            System.out.println("8. Exit");
            System.out.print(">> Choose an option (1-8): ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    market.displaySortedBooks();
                    break;
                case 2:
                    System.out.print("Enter Author's Exact Name (e.g., 'Robert Vance', 'Sarah Jenkins', 'Elena Rodriguez'): ");
                    String authorName = scanner.nextLine();
                    market.viewBookProfile(authorName);
                    break;
                case 3:
                    market.showViewingHistory();
                    break;
                case 4:
                    System.out.print("Enter your name to join the waitlist: ");
                    String waitlistName = scanner.nextLine();
                    market.joinWaitlist(waitlistName);
                    break;
                case 5:
                    market.processWaitlist();
                    break;
                case 6:
                    System.out.print("Enter mentee's name to add to your legacy tree: ");
                    String menteeName = scanner.nextLine();
                    market.addToLegacyTree(menteeName);
                    break;
                case 7:
                    market.displayLegacyTree();
                    break;
                case 8:
                    System.out.println("Leaving the Marketplace. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose a number between 1 and 8.");
            }
        }
        scanner.close();
    }

    public void initializeBooks() {
        bookCatalog.put("Robert Vance", new HumanBook("Robert Vance", "Retired Architect", "Designing for Longevity", 2));
        bookCatalog.put("Sarah Jenkins", new HumanBook("Sarah Jenkins", "Comp Sci Student", "React JS for Beginners", 1));
        bookCatalog.put("Elena Rodriguez", new HumanBook("Elena Rodriguez", "Digital Nomad", "Quitting Corporate Life", 1));
    }

    public void displaySortedBooks() {
        System.out.println("\n>> Available Books (Sorted by Cost using Bubble Sort logic):");
        List<HumanBook> books = new ArrayList<>(bookCatalog.values());
        
        for (int i = 0; i < books.size() - 1; i++) {
            for (int j = 0; j < books.size() - i - 1; j++) {
                if (books.get(j).cost > books.get(j + 1).cost) {
                    HumanBook temp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, temp);
                }
            }
        }
        
        for (HumanBook book : books) {
            System.out.println("- " + book.toString());
        }
    }

    public void viewBookProfile(String authorName) {
        if (bookCatalog.containsKey(authorName)) {
            HumanBook book = bookCatalog.get(authorName);
            System.out.println("\n>> Viewing Profile: " + book.name);
            viewingHistory.push(book); 
        } else {
            System.out.println("\n>> Book not found. Make sure to type the name exactly as it appears.");
        }
    }

    public void showViewingHistory() {
        System.out.println("\n>> Your Recent Browsing History (Stack):");
        if (viewingHistory.isEmpty()) {
            System.out.println("No history yet.");
            return;
        }
        // Reading stack from top to bottom
        for (int i = viewingHistory.size() - 1; i >= 0; i--) {
            System.out.println("- " + viewingHistory.get(i).name);
        }
    }

    public void joinWaitlist(String userName) {
        System.out.println("\n>> " + userName + " joined the waitlist for Robert Vance.");
        robertVanceWaitlist.add(userName); 
    }

    public void processWaitlist() {
        System.out.println("\n>> Processing Waitlist (Queue FIFO):");
        if(robertVanceWaitlist.isEmpty()) {
            System.out.println("The waitlist is currently empty.");
        }
        while (!robertVanceWaitlist.isEmpty()) {
            String nextInLine = robertVanceWaitlist.poll(); 
            System.out.println("- Connecting Robert Vance with: " + nextInLine);
        }
    }

    public void addToLegacyTree(String menteeName) {
        LegacyNode newNode = new LegacyNode(menteeName);
        if (legacyTreeHead == null) {
            legacyTreeHead = newNode;
        } else {
            LegacyNode current = legacyTreeHead;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("\n>> Added " + menteeName + " to your legacy tree.");
    }

    public void displayLegacyTree() {
        System.out.println("\n>> Your Legacy Tree Lineage (Singly Linked List):");
        LegacyNode current = legacyTreeHead;
        System.out.print("You");
        while (current != null) {
            System.out.print(" -> Taught: " + current.menteeName);
            current = current.next;
        }
        System.out.println();
    }
}