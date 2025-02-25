import java.util.*;

class CardCollection {
    private HashMap<String, List<String>> cards;
    private Scanner scanner;

    public CardCollection() {
        cards = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void addCard(String symbol, String card) {
        cards.putIfAbsent(symbol, new ArrayList<>());
        cards.get(symbol).add(card);
    }

    public void searchCardsBySymbol(String symbol) {
        if (cards.containsKey(symbol)) {
            System.out.println("Cards with symbol " + symbol + ": " + cards.get(symbol));
        } else {
            System.out.println("No cards found with the symbol " + symbol);
        }
    }

    public void displayAllCards() {
        if (cards.isEmpty()) {
            System.out.println("No cards in the collection.");
        } else {
            for (Map.Entry<String, List<String>> entry : cards.entrySet()) {
                System.out.println("Symbol: " + entry.getKey() + " -> Cards: " + entry.getValue());
            }
        }
    }

    public void run() {
        while (true) {
            System.out.println("\nCard Collection System");
            System.out.println("1. Add Card");
            System.out.println("2. Search Cards by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter card symbol (e.g., Hearts, Spades): ");
                    String symbol = scanner.nextLine();
                    System.out.print("Enter card name (e.g., Ace, King): ");
                    String card = scanner.nextLine();
                    addCard(symbol, card);
                    System.out.println("Card added successfully!");
                    break;
                case 2:
                    System.out.print("Enter symbol to search: ");
                    String searchSymbol = scanner.nextLine();
                    searchCardsBySymbol(searchSymbol);
                    break;
                case 3:
                    displayAllCards();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void main(String[] args) {
        CardCollection system = new CardCollection();
        system.run();
    }
}
