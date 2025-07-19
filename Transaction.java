//Name: Gregory mitchell 
//Date: 07/17/25
// Transaction.java
// Represents a single financial transaction (deposit, withdrawal, transfer).
// Stores the type, amount, and date of the transaction for tracking and history.


// Represents a single transaction (Deposit, Withdraw, Transfer)
public class Transaction {
    // Transaction type (e.g., "Deposit", "Withdraw", "Transfer")
    private String type;

    // Amount involved in the transaction
    private double amount;

    // Date of the transaction as a String
    private String date;

    // Constructor to initialize a new transaction
    public Transaction(String type, double amount, String date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    // Getter for transaction type
    public String getType() {
        return type;
    }

    // Setter for transaction type
    public void setType(String type) {
        this.type = type;
    }

    // Getter for transaction amount
    public double getAmount() {
        return amount;
    }

    // Setter for transaction amount
    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Getter for transaction date
    public String getDate() {
        return date;
    }

    // Setter for transaction date
    public void setDate(String date) {
        this.date = date;
    }

    // Override to print transaction info clearly
    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}
