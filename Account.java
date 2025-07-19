//Name: Gregory mitchell 
//Date: 07/16/25
// Account.java
// Represents a user bank account. Stores user info, balance,
// and a linked list of transactions. Contains methods to deposit,
// withdraw, and log transactions.



import java.util.LinkedList;

// Represents a bank account with basic info and transaction history
public class Account {
    // Account properties
    private String accountId;
    private String name;
    private double balance;
    private LinkedList<Transaction> transactionHistory; // Linked list of all transactions

    // Constructor to initialize the account
    public Account(String accountId, String name, double initialBalance) {
        this.accountId = accountId;
        this.name = name;
        this.balance = initialBalance;
        this.transactionHistory = new LinkedList<>();
    }

    // Getters (used to retrieve private variable values)
    public String getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public LinkedList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    // Setters (used to change variable values — optional)
    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;  // Not normally recommended for security purposes, but here  to show functionality
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    // Adds funds to the account and records the transaction
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addTransaction("Deposit", amount);
        }
    }

    // Withdraws funds if enough balance is available, then records it
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            addTransaction("Withdraw", amount);
            return true;
        }
        return false;
    }

    // Adds a new transaction to the transaction history
    public void addTransaction(String type, double amount) {
        Transaction t = new Transaction(type, amount, java.time.LocalDateTime.now().toString());
        transactionHistory.add(t);
    }

    // Prints all transaction history for this account
    public void printTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (Transaction t : transactionHistory) {
                System.out.println(t.toString());
            }
        }
    }
}
