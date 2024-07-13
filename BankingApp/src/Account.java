import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
    private String accountNumber;
    private double balance;
    private AccountType accountType;
    private List<String> holders; // List of usernames of joint account holders
    private List<Transaction> transactionHistory;
    private double overdraftLimit;
    private boolean isFrozen;

    public Account(String accountNumber, double balance, AccountType accountType, List<String> holders, double overdraftLimit) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.holders = holders != null ? holders : new ArrayList<>();
        this.transactionHistory = new ArrayList<>();
        this.overdraftLimit = overdraftLimit;
        this.isFrozen = false;
    }

    public void addHolder(String username) {
        if (!holders.contains(username)) {
            holders.add(username);
        }
    }

    public boolean isHolder(String username) {
        return holders.contains(username);
    }

    public void deposit(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        balance += amount;
        logTransaction("Deposit", amount);
    }

    public void withdraw(double amount) throws IllegalArgumentException, IllegalStateException {
        if (isFrozen) {
            throw new IllegalStateException("Account is frozen. Cannot withdraw funds.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (amount > balance + overdraftLimit) {
            throw new IllegalStateException("Insufficient funds.");
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
        System.out.println(description + " of $" + amount + " completed successfully.");
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History for Account " + accountNumber + ":");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public List<String> getHolders() {
        return holders;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public void setFrozen(boolean isFrozen) {
        this.isFrozen = isFrozen;
    }
}
