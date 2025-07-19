import java.util.*;  // For Scanner, ArrayList, HashMap

// Main class for managing the banking system
public class BankSystem {

    // List to store all accounts
    private static ArrayList<Account> accounts = new ArrayList<>();

    // HashMap for fast account lookup using account ID
    private static HashMap<String, Account> accountMap = new HashMap<>();

    // Main method: runs the program and shows the menu
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Menu loop
        while (running) {
            System.out.println("\n=== Simple Banking System ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Calculate Compound Interest");
            System.out.println("6. View Transaction History");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline character

            // Handle user's menu choice
            switch (choice) {
                case 1 -> createAccount(scanner);
                case 2 -> deposit(scanner);
                case 3 -> withdraw(scanner);
                case 4 -> transfer(scanner);
                case 5 -> interestCalculator(scanner);
                case 6 -> viewHistory(scanner);
                case 7 -> {
                    running = false;
                    System.out.println("Exiting. Goodbye!");
                }
                default -> System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    // Creates a new bank account
    public static void createAccount(Scanner scanner) {
        System.out.print("Enter account ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter initial deposit: ");
        double deposit = scanner.nextDouble();
        scanner.nextLine();

        Account account = new Account(id, name, deposit);
        accounts.add(account);                 // Add to list
        accountMap.put(id, account);           // Add to map
        System.out.println("Account created!");
    }

    // Deposits money into an existing account
    public static void deposit(Scanner scanner) {
        System.out.print("Enter account ID: ");
        String id = scanner.nextLine();
        Account acc = accountMap.get(id);

        if (acc != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            acc.deposit(amount);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Account not found.");
        }
    }

    // Withdraws money from an existing account
    public static void withdraw(Scanner scanner) {
        System.out.print("Enter account ID: ");
        String id = scanner.nextLine();
        Account acc = accountMap.get(id);

        if (acc != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            boolean success = acc.withdraw(amount);
            if (success) {
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    // Transfers money between two existing accounts
    public static void transfer(Scanner scanner) {
        System.out.print("Enter your account ID: ");
        String sourceId = scanner.nextLine();
        System.out.print("Enter recipient account ID: ");
        String targetId = scanner.nextLine();

        Account from = accountMap.get(sourceId);
        Account to = accountMap.get(targetId);

        if (from == null || to == null) {
            System.out.println("One or both accounts not found.");
            return;
        }

        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (from.getBalance() >= amount) {
            from.withdraw(amount);
            to.deposit(amount);
            from.addTransaction("Transfer Out", amount);
            to.addTransaction("Transfer In", amount);
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    // Calculates future value using recursive compound interest
    public static void interestCalculator(Scanner scanner) {
        System.out.print("Enter account ID: ");
        String id = scanner.nextLine();
        Account acc = accountMap.get(id);

        if (acc != null) {
            System.out.print("Enter annual rate (e.g., 0.05 for 5%): ");
            double rate = scanner.nextDouble();
            System.out.print("Enter number of years: ");
            int years = scanner.nextInt();
            scanner.nextLine();

            double result = calculateCompoundInterest(acc.getBalance(), rate, years);
            System.out.printf("Future Value: $%.2f%n", result);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Recursive method to calculate compound interest
    public static double calculateCompoundInterest(double principal, double rate, int years) {
        if (years == 0) return principal;
        return calculateCompoundInterest(principal * (1 + rate), rate, years - 1);
    }

    // Displays all transactions for a given account
    public static void viewHistory(Scanner scanner) {
        System.out.print("Enter account ID: ");
        String id = scanner.nextLine();
        Account acc = accountMap.get(id);

        if (acc != null) {
            System.out.println("Transaction History:");
            acc.printTransactionHistory();
        } else {
            System.out.println("Account not found.");
        }
    }
}
