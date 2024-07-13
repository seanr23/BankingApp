import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
    private String accountNumber;
    private double balance;
    private List<Transaction> transactionHistory;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;
        }
        balance += amount;
        logTransaction("Deposit", amount);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds.");
            return;
        }
        balance -= amount;
        logTransaction("Withdrawal", amount);
    }

    public double checkBalance() {
        return balance;
    }

    private void logTransaction(String description, double amount) {
        Transaction transaction = new Transaction(new Date(), description, amount);
        transactionHistory.add(transaction);
        // Optionally, you can log the transaction to a file or database.
        System.out.println(description + " of $" + amount + " completed successfully.");
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History for Account " + accountNumber + ":");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    // Getter for account number (if needed)
    public String getAccountNumber() {
        return accountNumber;
    }
}
